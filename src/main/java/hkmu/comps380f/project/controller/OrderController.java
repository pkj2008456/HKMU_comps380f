package hkmu.comps380f.project.controller;

import hkmu.comps380f.project.exception.BookInfoNotFound;
import hkmu.comps380f.project.exception.EmptyContentException;
import hkmu.comps380f.project.exception.UserAcctNotFound;
import hkmu.comps380f.project.exception.UserOrderNotFound;
import hkmu.comps380f.project.model.BookInfo;
import hkmu.comps380f.project.model.UserAcct;
import hkmu.comps380f.project.model.UserOrder;
import hkmu.comps380f.project.service.BookInfoService;
import hkmu.comps380f.project.service.ShoppingCartService;
import hkmu.comps380f.project.service.UserAcctService;
import hkmu.comps380f.project.service.UserOrderService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopping")
public class OrderController {

    @Resource
    UserAcctService userAcctService;
    @Resource
    UserOrderService userOrderService;
    @Resource
    BookInfoService bookInfoService;
    @Resource
    ShoppingCartService shoppingCartService;

    @GetMapping("/cart")
    public ModelAndView shoppingCart(HttpSession session) {
        List<ShoppingCartService.ShoppingCartItem> cartItemList = shoppingCartService.getShoppingCartWithBookInfo(session);
        double totalPrice = shoppingCartService.calcTotalPrice(cartItemList);

        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        modelAndView.addObject("cartItemList", cartItemList);
        modelAndView.addObject("totalPrice", totalPrice);

        return modelAndView;
    }

    @GetMapping("/checkout")
    public ModelAndView shoppingCheckout(HttpSession session, Principal principal)
            throws UserAcctNotFound {
        List<ShoppingCartService.ShoppingCartItem> cartItemList = shoppingCartService.getShoppingCartWithBookInfo(session);
        double totalPrice = shoppingCartService.calcTotalPrice(cartItemList);

        if(cartItemList.isEmpty()){
            return new ModelAndView("redirect:/shopping/cart");
        }

        UserAcct user = userAcctService.getUserAcct(principal.getName());

        CheckoutForm checkoutForm = new CheckoutForm();
        checkoutForm.setFullName(user.getFullName());
        checkoutForm.setEmailAddr(user.getEmailAddr());
        checkoutForm.setDeliveryAddr(user.getDeliveryAddr());

        ModelAndView modelAndView = new ModelAndView("shoppingCheckout");
        modelAndView.addObject("cartItemList", cartItemList);
        modelAndView.addObject("checkoutForm", checkoutForm);
        modelAndView.addObject("totalPrice", totalPrice);

        return modelAndView;
    }

    @PostMapping("/checkout")
    public View shoppingCheckout(HttpSession session, CheckoutForm checkoutForm, Principal principal)
            throws UserAcctNotFound, EmptyContentException {
        List<ShoppingCartService.ShoppingCartItem> cartItemList = shoppingCartService.getShoppingCartWithBookInfo(session);

        if(cartItemList.isEmpty()){
            return new RedirectView("/shopping/cart", true);
        }

        UserAcct user = userAcctService.getUserAcct(principal.getName());

        UserOrder order = userOrderService.createUserOrder(user, checkoutForm, cartItemList);

        shoppingCartService.clearShoppingCart(session);

        return new RedirectView("/shopping/finish/" + order.getId(), true);
    }

    @GetMapping("/finish/{orderId}")
    public ModelAndView shoppingFinish(@PathVariable("orderId") long orderId)
            throws UserOrderNotFound {
        UserOrder order = userOrderService.getUserOrder(orderId);

        return new ModelAndView("shoppingFinish", "userOrder", order);
    }

    @PostMapping("/cart/add/book/{id}")
    public String addToCart(@PathVariable("id") long id, HttpSession session, AddToCartForm form)
            throws BookInfoNotFound {
        BookInfo bookInfo = bookInfoService.getBookInfo(id);
        if(bookInfo == null){
            throw new BookInfoNotFound(id);
        }

        shoppingCartService.addBook(session, id, form.quantity);

        return "redirect:/book/" + id + "?cartInserted";
    }

    @PostMapping("/cart/edit/book/{id}")
    public String editCartItem(@PathVariable("id") long id, HttpSession session, EditCartForm form)
            throws BookInfoNotFound {
        BookInfo bookInfo = bookInfoService.getBookInfo(id);
        if(bookInfo == null){
            throw new BookInfoNotFound(id);
        }

        shoppingCartService.editBookQuantity(session, id, form.quantity);

        return "redirect:/shopping/cart?cartEdit";
    }

    @GetMapping("/cart/delete/book/{id}")
    public String deleteCartItem(@PathVariable("id") long id, HttpSession session) {
        shoppingCartService.deleteBook(session, id);

        return "redirect:/shopping/cart?cartDeleted";
    }

    public static class CheckoutForm {
        private String fullName;
        private String emailAddr;
        private String deliveryAddr;

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

        public boolean isValid() {
            return (
                !this.getFullName().isEmpty() &&
                !this.getDeliveryAddr().isEmpty() &&
                !this.getEmailAddr().isEmpty()
            );
        }
    }

    public static class AddToCartForm {
        private Integer quantity;

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    public static class EditCartForm {
        private Integer quantity;

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

}
