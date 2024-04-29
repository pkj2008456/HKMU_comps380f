package hkmu.comps380f.project.controller;

import hkmu.comps380f.project.exception.EmptyContentException;
import hkmu.comps380f.project.exception.UserAcctNotFound;
import hkmu.comps380f.project.model.UserAcct;
import hkmu.comps380f.project.service.UserAcctService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Resource
    UserAcctService userAcctService;

    @GetMapping("")
    public ModelAndView register() {
        return new ModelAndView("registerForm", "registerForm", new RegisterForm());
    }

    @PostMapping("")
    public View register(RegisterForm registerForm)
            throws UserAcctNotFound, EmptyContentException {
        try {
            UserAcct userAcct = userAcctService.getUserAcct(registerForm.getUsername());
            if(userAcct != null){
                return new RedirectView("/register?userExist", true);
            }
        } catch (UserAcctNotFound e) {
            UserAcct regResult = userAcctService.registerUserAcct(
                    registerForm.getUsername(),
                    registerForm.getPassword(),
                    registerForm.getFullName(),
                    registerForm.getEmailAddr(),
                    registerForm.getDeliveryAddr()
            );
        }

        return new RedirectView("/login", true);
    }

    public static class RegisterForm {
        private String username;
        private String password;
        private String fullName;
        private String emailAddr;
        private String deliveryAddr;

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
    }
}
