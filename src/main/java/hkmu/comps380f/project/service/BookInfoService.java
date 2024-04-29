package hkmu.comps380f.project.service;

import hkmu.comps380f.project.dao.BookCommentRepository;
import hkmu.comps380f.project.dao.BookCoverPhotoRepository;
import hkmu.comps380f.project.dao.BookInfoRepository;
import hkmu.comps380f.project.dao.UserAcctRepository;
import hkmu.comps380f.project.exception.AttachmentNotFound;
import hkmu.comps380f.project.exception.BookInfoNotFound;
import hkmu.comps380f.project.exception.EmptyContentException;
import hkmu.comps380f.project.exception.FileNotImageException;
import hkmu.comps380f.project.model.BookComment;
import hkmu.comps380f.project.model.BookCoverPhoto;
import hkmu.comps380f.project.model.BookInfo;
import hkmu.comps380f.project.model.UserAcct;
import jakarta.annotation.Resource;
import jakarta.persistence.PreRemove;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookInfoService {
    @Resource
    private BookInfoRepository bookInfoRepo;
    @Resource
    private BookCoverPhotoRepository bookCoverPhotoRepo;
    @Resource
    BookCommentService bookCommentService;
    @Resource
    UserAcctRepository userAcctRepo;

    @Transactional
    public List<BookInfo> getBookInfos() {
        return bookInfoRepo.findAll();
    }

    public List<BookInfo> getAvailableBookInfos() {
        return bookInfoRepo.findAllByAvailability(true);
    }

    @Transactional
    public BookInfo getBookInfo(long id)
            throws BookInfoNotFound {
        BookInfo bookInfo = bookInfoRepo.findById(id).orElse(null);
        if (bookInfo == null) {
            throw new BookInfoNotFound(id);
        }
        return bookInfo;
    }
    @Transactional(rollbackFor = BookInfoNotFound.class)
    public void deleteBookInfo(long id)
            throws BookInfoNotFound {
        BookInfo bookInfo = bookInfoRepo.findById(id).orElse(null);
        if (bookInfo == null) {
            throw new BookInfoNotFound(id);
        }

        bookCommentService.deleteCommentsByBookId(id);
//        bookInfoRepo.findAllFav(id);
//        bookInfoRepo.findAllPhoto(id);
//        bookInfoRepo.findAllBook(id);

        for(UserAcct user : bookInfo.getFavoriteUserList()) {
            user.getFavoriteBookList().remove(bookInfo);
        }

        bookInfoRepo.delete(bookInfo);
    }

    @Transactional
    public BookInfo createBookInfo(String name, String author, double price, String description, Boolean availability, List<MultipartFile> coverPhotos)
            throws IOException, EmptyContentException, FileNotImageException {
        if (name.isEmpty() || author.isEmpty() || price < 0) {
            throw new EmptyContentException();
        }
        if(availability == null){
            availability = false;
        }
        BookInfo bookInfo = new BookInfo(name, author, price, description, availability);

        for (MultipartFile filePart : coverPhotos) {
            BookCoverPhoto attachment = new BookCoverPhoto();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setBook(bookInfo);
            if (
                attachment.getName() != null && attachment.getName().length() > 0
                && attachment.getContents() != null && attachment.getContents().length > 0
                && attachment.getMimeContentType() != null
            ) {
                if(!attachment.getMimeContentType().contains("image/")){
                    throw new FileNotImageException(attachment.getName(), attachment.getMimeContentType());
                }
                bookInfo.getCoverPhotos().add(attachment);
            }
        }

        return bookInfoRepo.save(bookInfo);
    }
    @Transactional
    public BookInfo updateBookInfo(long id, String name, String author, double price, String description, Boolean availability, List<MultipartFile> coverPhotos)
            throws IOException, BookInfoNotFound, FileNotImageException {
        BookInfo bookInfo = bookInfoRepo.findById(id).orElse(null);
        if (bookInfo == null) {
            throw new BookInfoNotFound(id);
        }

        bookInfo.setName(name);
        bookInfo.setAuthor(author);
        bookInfo.setPrice(price);
        bookInfo.setDescription(description);
        bookInfo.setAvailability(availability);

        for (MultipartFile filePart : coverPhotos) {
            BookCoverPhoto attachment = new BookCoverPhoto();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setBook(bookInfo);
            if (
                attachment.getName() != null && attachment.getName().length() > 0
                && attachment.getContents() != null && attachment.getContents().length > 0
                && attachment.getMimeContentType() != null
            ) {
                if(!attachment.getMimeContentType().contains("image/")){
                    throw new FileNotImageException(attachment.getName(), attachment.getMimeContentType());
                }
                bookInfo.getCoverPhotos().add(attachment);
            }
        }

        return bookInfoRepo.save(bookInfo);
    }

    @Transactional
    public BookCoverPhoto getCoverPhoto(long id, UUID attachmentId)
        throws BookInfoNotFound, AttachmentNotFound{
        BookInfo bookInfo = bookInfoRepo.findById(id).orElse(null);
        if (bookInfo == null) {
            throw new BookInfoNotFound(id);
        }
        BookCoverPhoto attachment = bookCoverPhotoRepo.findById(attachmentId).orElse(null);
        if (attachment == null) {
            throw new AttachmentNotFound(attachmentId);
        }
        return attachment;
    }

    @Transactional(rollbackFor = BookInfoNotFound.class)
    public void deleteCoverPhoto(long id, UUID attachmentId)
            throws BookInfoNotFound, AttachmentNotFound {
        BookInfo bookInfo = bookInfoRepo.findById(id).orElse(null);
        if (bookInfo == null) {
            throw new BookInfoNotFound(id);
        }
        for (BookCoverPhoto attachment : bookInfo.getCoverPhotos()) {
            if (attachment.getId().equals(attachmentId)) {
                bookInfo.deleteCoverPhoto(attachment);
                bookInfoRepo.save(bookInfo);
                return;
            }
        }
        throw new AttachmentNotFound(attachmentId);
    }
}
