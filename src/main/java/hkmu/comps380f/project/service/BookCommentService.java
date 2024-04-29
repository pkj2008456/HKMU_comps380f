package hkmu.comps380f.project.service;

import hkmu.comps380f.project.dao.BookCommentRepository;
import hkmu.comps380f.project.exception.BookCommentNotFound;
import hkmu.comps380f.project.exception.BookInfoNotFound;
import hkmu.comps380f.project.exception.EmptyContentException;
import hkmu.comps380f.project.model.BookComment;
import hkmu.comps380f.project.model.BookInfo;
import hkmu.comps380f.project.model.UserAcct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookCommentService {

    @Resource
    BookCommentRepository bookCommentRepo;

    @Transactional
    public BookComment createBookComment(BookInfo bookInfo, UserAcct userAcct, String comment)
            throws EmptyContentException {
        if(comment.isEmpty() || bookInfo == null || userAcct == null){
            throw new EmptyContentException();
        }

        Date now = new Date();
        BookComment bookComment = new BookComment();
        bookComment.setContent(comment);
        bookComment.setBook(bookInfo);
        bookComment.setUser(userAcct);
        bookComment.setCreateTime(now);
        bookComment.setLastUpdateTime(now);

        return bookCommentRepo.save(bookComment);
    }

    @Transactional
    public List<BookComment> getBookComments(long id) {
        return bookCommentRepo.findAllByBookIdOrderByCreateTimeAsc(id);
    }

    @Transactional
    public List<BookComment> getUserBookComments(long id, String username) {
        return bookCommentRepo.findAllByBookIdAndUserIdOrderByCreateTimeAsc(id, username);
    }

    @Transactional(rollbackFor = BookCommentNotFound.class)
    public void deleteComment(long id) throws BookCommentNotFound {
        BookComment bookComment = bookCommentRepo.findById(id).orElse(null);
        if (bookComment == null) {
            throw new BookCommentNotFound(id);
        }

        bookComment.getBook().getComments().remove(bookComment);
        bookComment.getUser().getComments().remove(bookComment);

        bookComment.setBook(null);
        bookComment.setUser(null);

        bookCommentRepo.delete(bookComment);
    }

    @Transactional
    public void deleteCommentsByBookId(long id) {
        List<BookComment> bookComments = bookCommentRepo.findAllByBookIdOrderByCreateTimeAsc(id);
        for(BookComment item : bookComments) {
            item.getBook().getComments().remove(item);
            item.getUser().getComments().remove(item);

            item.setBook(null);
            item.setUser(null);
        }
        bookCommentRepo.deleteAll(bookComments);
    }

    @Transactional
    public void deleteCommentsByUsername(String username) {
        List<BookComment> bookComments = bookCommentRepo.findAllByUserIdOrderByCreateTimeAsc(username);
        for(BookComment item : bookComments) {
            item.getBook().getComments().remove(item);
            item.getUser().getComments().remove(item);

            item.setBook(null);
            item.setUser(null);
        }
        bookCommentRepo.deleteAll(bookComments);
    }

    @Transactional
    public List<BookInfo> getCommentsWithBookInfos(List<BookInfo> bookInfos, String username) {
        List<BookInfo> selectedBooks = new ArrayList<>();

        for(BookInfo book: bookInfos) {
            List<BookComment> bookComments = this.getUserBookComments(book.getId(), username);
            if(!bookComments.isEmpty()){
                book.setComments(bookComments);
                selectedBooks.add(book);
            }
        }
        return selectedBooks;
    }

}
