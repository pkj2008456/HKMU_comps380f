package hkmu.comps380f.project.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "book_comment")
public class BookComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    @Column(updatable = false)
    private Date createTime;
    private Date lastUpdateTime;
    @Column(name = "book_id", insertable=false, updatable=false)
    private long bookId;
    @Column(name = "user_id", insertable=false, updatable=false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookInfo book;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAcct user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BookInfo getBook() {
        return book;
    }

    public void setBook(BookInfo book) {
        this.book = book;
    }

    public UserAcct getUser() {
        return user;
    }

    public void setUser(UserAcct user) {
        this.user = user;
    }
}
