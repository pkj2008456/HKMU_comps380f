package hkmu.comps380f.project.model;

import jakarta.persistence.*;

import java.awt.print.Book;
import java.util.*;

@Entity
@Table(name = "user_acct")
public class UserAcct {
    @Id
    private String username;
    private String password;
    private String fullName;
    private String emailAddr;
    private String deliveryAddr;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserOrder> orders = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name="UserFavRef", joinColumns = {@JoinColumn(name="user_id")}, inverseJoinColumns = {@JoinColumn(name="book_id")})
    Set<BookInfo> favoriteBookList = new HashSet<>();

    public UserAcct() {
        this.setRoles(new String[0]);
    }

    public UserAcct(String username, String password, String fullName, String emailAddr, String deliveryAddr) {
        this.setUsername(username);
        this.setPassword(password);
        this.setFullName(fullName);
        this.setEmailAddr(emailAddr);
        this.setDeliveryAddr(deliveryAddr);
        this.setRoles(new String[0]);
    }

    public UserAcct(String username, String password, String fullName, String emailAddr, String deliveryAddr, String[] roles) {
        this.setUsername(username);
        this.setPassword(password);
        this.setFullName(fullName);
        this.setEmailAddr(emailAddr);
        this.setDeliveryAddr(deliveryAddr);
        this.setRoles(roles);
    }

    public UserAcct(String username, String password, String fullName, String emailAddr, String deliveryAddr, List<UserRole> roles, List<BookComment> comments, List<UserOrder> orders) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.emailAddr = emailAddr;
        this.deliveryAddr = deliveryAddr;
        this.roles = roles;
        this.comments = comments;
        this.orders = orders;
    }

    public void setRoles(String[] roles) {
        this.roles.clear();
        this.roles.add(new UserRole(this, "ROLE_USER"));
        for (String role : roles) {
            this.roles.add(new UserRole(this, role));
        }
    }

    // getters and setters of all properties

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
        this.password = "{noop}" + password;
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

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public List<BookComment> getComments() {
        return comments;
    }

    public void setComments(List<BookComment> comments) {
        this.comments = comments;
    }

    public List<UserOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<UserOrder> orders) {
        this.orders = orders;
    }

    public Set<BookInfo> getFavoriteBookList() {
        return favoriteBookList;
    }

    public void setFavoriteBookList(Set<BookInfo> favoriteBookList) {
        this.favoriteBookList = favoriteBookList;
    }

    public void removeFavoriteBook(BookInfo book){
        this.favoriteBookList.remove(book);
        book.getFavoriteUserList().remove(this);
    }

    public boolean isInsideFavoriteList(long bookId) {
        for(BookInfo book : this.getFavoriteBookList()){
            if(book.getId() == bookId){ return true; }
        }

        return false;
    }

    @PreRemove
    private void removeUserAcctAssociations() {
        for (BookInfo book: this.favoriteBookList) {
            book.getFavoriteUserList().remove(this);
        }
    }
}
