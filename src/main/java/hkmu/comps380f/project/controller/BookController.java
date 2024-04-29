package hkmu.comps380f.project.controller;

import hkmu.comps380f.project.exception.BookCommentNotFound;
import hkmu.comps380f.project.exception.BookInfoNotFound;
import hkmu.comps380f.project.exception.EmptyContentException;
import hkmu.comps380f.project.exception.UserAcctNotFound;
import hkmu.comps380f.project.model.BookComment;
import hkmu.comps380f.project.model.BookInfo;
import hkmu.comps380f.project.model.UserAcct;
import hkmu.comps380f.project.service.BookCommentService;
import hkmu.comps380f.project.service.BookInfoService;
import hkmu.comps380f.project.service.UserAcctService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    UserAcctService userAcctService;
    @Resource
    BookInfoService bookInfoService;
    @Resource
    BookCommentService bookCommentService;

    @GetMapping("")
    public String list(ModelMap model) {
        List<BookInfo> bookInfos = bookInfoService.getBookInfos();
        model.addAttribute("bookInfos", bookInfos);

        model.addAttribute("commentForm", new CommentForm());
        model.addAttribute("addToCartForm", new OrderController.AddToCartForm());

        return "bookList";
    }

    @GetMapping("/{id}")
    public String View(@PathVariable("id") long id, Principal principal, ModelMap model)
            throws BookInfoNotFound, UserAcctNotFound {
            BookInfo bookInfo = bookInfoService.getBookInfo(id);
            List<BookComment> orderedComments = bookCommentService.getBookComments(id);
            Boolean isInsideFavoriteList = false;
            if (!(principal == null)) {
                UserAcct userAcct = userAcctService.getUserAcct(principal.getName());
                if(userAcct == null){
                    throw new UserAcctNotFound(principal.getName());
                }
                isInsideFavoriteList = userAcct.isInsideFavoriteList(bookInfo.getId());
            }

            model.addAttribute("bookInfo", bookInfo);
            model.addAttribute("orderedComments", orderedComments);
            model.addAttribute("isInsideFavoriteList", isInsideFavoriteList);
            return "bookView";

    }

    @PostMapping("/{id}/add/comment")
    public String addComment(@PathVariable("id") long id, Principal principal, HttpServletRequest request, CommentForm form)
            throws BookInfoNotFound, UserAcctNotFound {
        if (principal == null || (!request.isUserInRole("ROLE_USER"))) {
            return "redirect:/book/" + id;
        }

        BookInfo bookInfo = bookInfoService.getBookInfo(id);
        if(bookInfo == null){
            throw new BookInfoNotFound(id);
        }

        UserAcct userAcct = userAcctService.getUserAcct(principal.getName());
        if(userAcct == null){
            throw new UserAcctNotFound(principal.getName());
        }

        try {
            bookCommentService.createBookComment(bookInfo, userAcct, form.getComment());
        } catch (EmptyContentException e) {
            return "redirect:/book/" + id + "?commentEmpty=true";
        }

        return "redirect:/book/" + id + "?commentCreated=true";
    }

    @GetMapping("/{id}/delete/comment/{commentId}")
    public String addComment(@PathVariable("id") long id, @PathVariable("commentId") long commentId,
                             Principal principal, HttpServletRequest request)
            throws BookCommentNotFound {
        if (principal == null || (!request.isUserInRole("ROLE_USER"))) {
            return "redirect:/book/" + id;
        }

        bookCommentService.deleteComment(commentId);

        return "redirect:/book/" + id + "?commentCreated=true";
    }

    public static class CommentForm {
        private String comment;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
