package hkmu.comps380f.project.controller;

import hkmu.comps380f.project.exception.AttachmentNotFound;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "redirect:/book";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ExceptionHandler
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }
}
