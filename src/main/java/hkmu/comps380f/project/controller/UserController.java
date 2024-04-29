package hkmu.comps380f.project.controller;

import hkmu.comps380f.project.exception.BookInfoNotFound;
import hkmu.comps380f.project.exception.UserAcctNotFound;
import hkmu.comps380f.project.exception.UserOrderNotFound;
import hkmu.comps380f.project.model.BookComment;
import hkmu.comps380f.project.model.BookInfo;
import hkmu.comps380f.project.model.UserAcct;
import hkmu.comps380f.project.model.UserOrder;
import hkmu.comps380f.project.service.BookCommentService;
import hkmu.comps380f.project.service.BookInfoService;
import hkmu.comps380f.project.service.UserAcctService;
import hkmu.comps380f.project.service.UserOrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    BookCommentService bookCommentService;
    @Resource
    BookInfoService bookInfoService;
    @Resource
    UserAcctService userAcctService;
    @Resource
    UserOrderService userOrderService;

    @GetMapping("/comment/list")
    public String commentList(ModelMap model, Principal principal) {

        List<BookInfo> bookInfos = bookInfoService.getBookInfos();

        List<BookInfo> selectedBooks = bookCommentService.getCommentsWithBookInfos(bookInfos, principal.getName());

        model.addAttribute("bookInfos", selectedBooks);

        return "userCommentList";
    }

    @GetMapping("/order/list")
    public String orderList(ModelMap model, Principal principal) {
        List<UserOrder> userOrders = userOrderService.getUserOrdersByUserId(principal.getName());

        model.addAttribute("userOrders", userOrders);

        return "userOrderList";
    }

    @GetMapping("/order/view/{orderId}")
    public String orderView(@PathVariable("orderId") long orderId, ModelMap model, Principal principal)
            throws UserOrderNotFound {
        UserOrder userOrder = userOrderService.getUserOrderByUserId(principal.getName(), orderId);

        model.addAttribute("userOrder", userOrder);

        return "userOrderView";
    }

    @GetMapping("/info")
    public ModelAndView info(Principal principal)
            throws UserAcctNotFound {
        UserAcct userAcct = userAcctService.getUserAcct(principal.getName());

        UserInfoForm userInfoForm = new UserInfoForm();

//        userInfoForm.setUsername(userAcct.getUsername());
        userInfoForm.setPassword(userAcct.getPassword());
        userInfoForm.setFullName(userAcct.getFullName());
        userInfoForm.setEmailAddr(userAcct.getEmailAddr());
        userInfoForm.setDeliveryAddr(userAcct.getDeliveryAddr());

        ModelAndView modelAndView = new ModelAndView("userInfoForm");
        modelAndView.addObject("userInfo", userAcct);
        modelAndView.addObject("userInfoForm", userInfoForm);

        return modelAndView;
    }

    @PostMapping("/info")
    public String info(Principal principal, UserInfoForm userInfoForm)
            throws UserAcctNotFound {
        userAcctService.updateUserInfo(
                principal.getName(),
                userInfoForm.getPassword(),
                userInfoForm.getFullName(),
                userInfoForm.getEmailAddr(),
                userInfoForm.getDeliveryAddr()
        );
        return "redirect:/user/info?editSuccess";
    }

    @GetMapping("/favorite")
    public String favoriteList(ModelMap model, Principal principal)
            throws UserAcctNotFound {
        UserAcct userAcct = userAcctService.getUserAcct(principal.getName());
        model.addAttribute("favoriteBookList", userAcct.getFavoriteBookList());
        return "userFavoriteList";
    }

    @GetMapping("/favorite/add/{bookId}")
    public String favoriteAdd(@PathVariable("bookId") long bookId, Principal principal)
            throws BookInfoNotFound, UserAcctNotFound {
        userAcctService.addFavoriteList(principal.getName(), bookId);
        return "redirect:/book/" + bookId + "?favoriteAdd";
    }

    @GetMapping("/favorite/delete/{bookId}")
    public String favoriteDel(@PathVariable("bookId") long bookId, Principal principal)
            throws BookInfoNotFound, UserAcctNotFound{
        userAcctService.removeFavoriteList(principal.getName(), bookId);
        return "redirect:/user/favorite?favoriteDeleted";
    }

    static public class UserInfoForm {
//        private String username;
        private String password;
        private String fullName;
        private String emailAddr;
        private String deliveryAddr;

//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }

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
    }
}
