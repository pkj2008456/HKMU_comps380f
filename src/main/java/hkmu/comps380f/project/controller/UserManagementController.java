package hkmu.comps380f.project.controller;

import hkmu.comps380f.project.exception.BookCommentNotFound;
import hkmu.comps380f.project.exception.EmptyContentException;
import hkmu.comps380f.project.exception.UserAcctNotFound;
import hkmu.comps380f.project.exception.UserOrderNotFound;
import hkmu.comps380f.project.model.*;
import hkmu.comps380f.project.service.BookCommentService;
import hkmu.comps380f.project.service.BookInfoService;
import hkmu.comps380f.project.service.UserAcctService;
import hkmu.comps380f.project.service.UserOrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserManagementController {
    @Resource
    UserAcctService userAcctService;
    @Resource
    UserOrderService userOrderService;
    @Resource
    BookInfoService bookInfoService;
    @Resource
    BookCommentService bookCommentService;

    @GetMapping({"", "/", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("userAccts", userAcctService.getUserAccts());
        return "userManagementList";
    }

    @GetMapping("/add")
    public ModelAndView create() {
        return new ModelAndView("userManagementAdd", "userAcctForm", new Form());
    }

    @PostMapping("/add")
    public View create(Form form)
            throws IOException {
        try {
            UserAcct userAcct = userAcctService.createUserAcct(form.getUsername(), form.getPassword(), form.getFullName(), form.getEmailAddr(), form.getDeliveryAddr(), form.getRoles());
            return new RedirectView("/admin/user/view/" + userAcct.getUsername() + "?addSuccess", true);
        } catch (EmptyContentException e) {
            return new RedirectView("/admin/user/add?contentEmpty", true);
        }
    }

    @GetMapping("/edit/{userAcctId}")
    public ModelAndView edit(@PathVariable("userAcctId") String userAcctId)
            throws UserAcctNotFound {
        UserAcct userAcct = userAcctService.getUserAcct(userAcctId);

        ModelAndView modelAndView = new ModelAndView("userManagementEdit");
        modelAndView.addObject("userAcct", userAcct);

        Form userAcctForm = new Form();
        userAcctForm.setUsername(userAcct.getUsername());
        userAcctForm.setPassword(userAcct.getPassword());
        userAcctForm.setFullName(userAcct.getFullName());
        userAcctForm.setEmailAddr(userAcct.getEmailAddr());
        userAcctForm.setDeliveryAddr(userAcct.getDeliveryAddr());

        ArrayList<String> rolesStr = new ArrayList<String>();
        for(UserRole role : userAcct.getRoles()) {
            rolesStr.add(role.getRole());
        }
        userAcctForm.setRoles(rolesStr.toArray(new String[rolesStr.size()]));

        modelAndView.addObject("userAcctForm", userAcctForm);

        return modelAndView;
    }

    @PostMapping("/edit/{userAcctId}")
    public String edit(@PathVariable("userAcctId") String userAcctId, Form form)
            throws UserAcctNotFound {
        userAcctService.updateUserAcct(form.getUsername(), form.getPassword(), form.getFullName(), form.getEmailAddr(), form.getDeliveryAddr(), form.getRoles());
        return "redirect:/admin/user/view/" + userAcctId + "?editSuccess";
    }

    @GetMapping("/view/{userAcctId}")
    public String view(@PathVariable("userAcctId") String userAcctId, ModelMap model)
            throws UserAcctNotFound {
        UserAcct userAcct = userAcctService.getUserAcct(userAcctId);
        model.addAttribute("userAcctId", userAcctId);
        model.addAttribute("userAcct", userAcct);
        return "userManagementView";
    }

    @GetMapping("/delete/{userAcctId}")
    public String delete(@PathVariable("userAcctId") String userAcctId, Principal principal)
            throws UserAcctNotFound {
        UserAcct userAcct = userAcctService.getUserAcct(userAcctId);
        if (userAcct == null || principal == null || principal.getName().equals(userAcct.getUsername())) {
            return "redirect:/admin/user/list?deleteSelf";
        }
        userAcctService.deleteUserAcct(userAcctId);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/comment/{userAcctId}")
    public String commentList(@PathVariable("userAcctId") String userAcctId, ModelMap model)
            throws UserAcctNotFound {
        UserAcct userAcct = userAcctService.getUserAcct(userAcctId);
        if (userAcct == null) {
            return "redirect:/admin/user/list";
        }
        List<BookInfo> bookInfos = bookInfoService.getBookInfos();

        List<BookInfo> selectedBooks = bookCommentService.getCommentsWithBookInfos(bookInfos, userAcct.getUsername());

        model.addAttribute("bookInfos", selectedBooks);
        model.addAttribute("userAcct", userAcct);

        return "userManagementCommentList";
    }

    @GetMapping("/comment/{userAcctId}/book/delete/{commentId}")
    public String commentDelete(@PathVariable("userAcctId") String userAcctId, @PathVariable("commentId") long commentId, ModelMap model)
            throws UserAcctNotFound, BookCommentNotFound {
        UserAcct userAcct = userAcctService.getUserAcct(userAcctId);
        if (userAcct == null) {
            return "redirect:/admin/user/list";
        }
        bookCommentService.deleteComment(commentId);

        return "redirect:/admin/user/comment/" + userAcctId + "?commentCreated=true";
    }

    @GetMapping("/order/{userAcctId}")
    public String orderList(@PathVariable("userAcctId") String userAcctId, ModelMap model)
            throws UserAcctNotFound {
        UserAcct userAcct = userAcctService.getUserAcct(userAcctId);
        if (userAcct == null) {
            return "redirect:/admin/user/list";
        }
        List<UserOrder> userOrders = userOrderService.getUserOrdersByUserId(userAcct.getUsername());
        model.addAttribute("userOrders", userOrders);
        model.addAttribute("userAcct", userAcct);

        return "userManagementOrderList";
    }

    @GetMapping("/order/{userAcctId}/view/{orderId}")
    public String orderView(@PathVariable("userAcctId") String userAcctId, @PathVariable("orderId") long orderId, ModelMap model)
            throws UserOrderNotFound, UserAcctNotFound {
        UserAcct userAcct = userAcctService.getUserAcct(userAcctId);
        if (userAcct == null) {
            return "redirect:/admin/user/list";
        }
        UserOrder userOrder = userOrderService.getUserOrderByUserId(userAcct.getUsername(), orderId);

        model.addAttribute("userOrder", userOrder);
        model.addAttribute("userAcct", userAcct);

        return "userManagementOrderView";
    }


    public static class Form {
        private String username;
        private String password;
        private String fullName;
        private String emailAddr;
        private String deliveryAddr;
        private String[] roles;
        // getters and setters for all properties

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmailAddr() {
            return emailAddr;
        }

        public void setEmailAddr(String emailAddr) {
            this.emailAddr = emailAddr;
        }

        public String getDeliveryAddr() {
            return deliveryAddr;
        }

        public void setDeliveryAddr(String deliveryAddr) {
            this.deliveryAddr = deliveryAddr;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }
    }
}
