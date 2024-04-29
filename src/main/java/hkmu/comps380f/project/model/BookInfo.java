package hkmu.comps380f.project.model;

import jakarta.persistence.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book_info")
public class BookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String author;
    private double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Boolean availability;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookCoverPhoto> coverPhotos = new ArrayList<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookComment> comments = new ArrayList<>();

//    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<UserOrderBook> orders = new ArrayList<>();

    @ManyToMany(mappedBy = "favoriteBookList")
    private Set<UserAcct> favoriteUserList = new HashSet<>();

    public BookInfo() { }

    public BookInfo(String name, String author, double price, String description, Boolean availability) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.description = description;
        this.availability = availability;
    }

    public BookInfo(long id, String name, String author, double price, String description, Boolean availability, List<BookCoverPhoto> coverPhotos) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.description = description;
        this.availability = availability;
        this.coverPhotos = coverPhotos;
    }

    public void deleteCoverPhoto(BookCoverPhoto attachment) {
        attachment.setBook(null);
        this.coverPhotos.remove(attachment);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public List<BookCoverPhoto> getCoverPhotos() {
        return coverPhotos;
    }

    public void setCoverPhotos(List<BookCoverPhoto> coverPhotos) {
        this.coverPhotos = coverPhotos;
    }

    public List<BookComment> getComments() {
        return comments;
    }

    public void setComments(List<BookComment> comments) {
        this.comments = comments;
    }

    public Set<UserAcct> getFavoriteUserList() {
        return favoriteUserList;
    }

    public void setFavoriteUserList(Set<UserAcct> favoriteUserList) {
        this.favoriteUserList = favoriteUserList;
    }

    @PreRemove
    private void removeBookInfoAssociations() {
        for (UserAcct user : this.favoriteUserList) {
            user.getFavoriteBookList().remove(this);
        }
    }
}