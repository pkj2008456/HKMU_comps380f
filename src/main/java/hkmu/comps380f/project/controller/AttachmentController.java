package hkmu.comps380f.project.controller;

import hkmu.comps380f.project.exception.AttachmentNotFound;
import hkmu.comps380f.project.exception.BookInfoNotFound;
import hkmu.comps380f.project.model.BookCoverPhoto;
import hkmu.comps380f.project.service.BookInfoService;
import hkmu.comps380f.project.view.DownloadingView;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import java.util.UUID;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {

    @Resource
    BookInfoService bookInfoService;
    @GetMapping("/book/{id}/coverPhoto/{attachment:.+}")
    public View download(@PathVariable("id") long id, @PathVariable("attachment") UUID attachmentId)
            throws BookInfoNotFound, AttachmentNotFound {
        BookCoverPhoto attachment = bookInfoService.getCoverPhoto(id, attachmentId);
        return new DownloadingView(attachment.getName(), attachment.getMimeContentType(), attachment.getContents());
    }
}
