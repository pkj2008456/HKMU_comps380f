package hkmu.comps380f.project.controller;

import hkmu.comps380f.project.exception.*;
import hkmu.comps380f.project.model.BookInfo;
import hkmu.comps380f.project.service.BookInfoService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/book")
public class BookManagementController {
    @Resource
    BookInfoService bookInfoService;
    @GetMapping({"", "/", "/list"})
    public String list(ModelMap model) {
        List<BookInfo> bookInfos = bookInfoService.getBookInfos();
        model.addAttribute("bookInfos", bookInfos);
        return "bookManagementList";
    }

    @GetMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("bookManagementAdd","bookInfoForm", new Form());
    }

    @PostMapping("/add")
    public View add(Form form)
            throws IOException, FileNotImageException {
        try {
            BookInfo bookInfo = bookInfoService.createBookInfo(form.getName(), form.getAuthor(), form.getPrice(), form.getDescription(), form.getAvailability(), form.getCoverPhotos());
            if (bookInfo == null) {
                return new RedirectView("/admin/book/add?contentEmpty", true);
            }
            return new RedirectView("/admin/book/view/"+bookInfo.getId() + "?addSuccess", true);
        } catch(EmptyContentException e) {
            return new RedirectView("/admin/book/add?contentEmpty", true);
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") long id)
            throws BookInfoNotFound {
        BookInfo bookInfo = bookInfoService.getBookInfo(id);

        ModelAndView modelAndView = new ModelAndView("bookManagementEdit");
        modelAndView.addObject("bookInfo", bookInfo);
        Form bookInfoForm = new Form();

        bookInfoForm.setName(bookInfo.getName());
        bookInfoForm.setAuthor(bookInfo.getAuthor());
        bookInfoForm.setPrice(bookInfo.getPrice());
        bookInfoForm.setDescription(bookInfo.getDescription());
        bookInfoForm.setAvailability(bookInfo.getAvailability());

        modelAndView.addObject("bookInfoForm", bookInfoForm);

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    private String edit(@PathVariable("id") long id, Form form)
            throws IOException, BookInfoNotFound, FileNotImageException {
        bookInfoService.updateBookInfo(id, form.getName(), form.getAuthor(), form.getPrice(), form.getDescription(), form.getAvailability(), form.getCoverPhotos());

        return "redirect:/admin/book/view/" + id + "?editSuccess";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id)
            throws BookInfoNotFound, UserAcctNotFound {
        bookInfoService.deleteBookInfo(id);
        return "redirect:/admin/book/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") long id, ModelMap model)
            throws BookInfoNotFound{
        BookInfo bookInfo = bookInfoService.getBookInfo(id);
        model.addAttribute("id", id);
        model.addAttribute("bookInfo", bookInfo);
        return "bookManagementView";
    }

    @GetMapping("/delete/{id}/coverPhoto/{attachment:.+}")
    public String deleteCoverPhotos(@PathVariable("id") long id, @PathVariable("attachment") UUID attachmentId)
            throws BookInfoNotFound, AttachmentNotFound {
        bookInfoService.deleteCoverPhoto(id, attachmentId);
        return "redirect:/admin/book/view/" + id;
    }

    public static class Form {
        private String name;
        private String author;
        private double price;
        private String description;
        private Boolean availability;
        private List<MultipartFile> coverPhotos;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Boolean getAvailability() {
            return availability;
        }

        public void setAvailability(Boolean availability) {
            this.availability = availability;
        }

        public List<MultipartFile> getCoverPhotos() {
            return coverPhotos;
        }

        public void setCoverPhotos(List<MultipartFile> coverPhotos) {
            this.coverPhotos = coverPhotos;
        }
    }
}
