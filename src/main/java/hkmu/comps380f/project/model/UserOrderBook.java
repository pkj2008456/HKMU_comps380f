package hkmu.comps380f.project.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
@Table(name = "user_order_book")
public class UserOrderBook {
    @Id
    @GeneratedValue
    @ColumnDefault("random_uuid()")
    private UUID id;
    private Integer quantity;
    private String name;
    private String author;
    private double price;
    @Column(columnDefinition = "TEXT")
    private String description;
//    @Column(name = "book_id", insertable=false, updatable=false)
//    private long bookId;
    @Column(name = "order_id", insertable=false, updatable=false)
    private long orderId;

//    @ManyToOne
//    @JoinColumn(name = "book_id")
//    private BookInfo book;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private UserOrder order;

    public UserOrderBook() {
    }

    public UserOrderBook(Integer quantity, String name, String author, double price, String description, UserOrder order) {
        this.quantity = quantity;
        this.name = name;
        this.author = author;
        this.price = price;
        this.description = description;
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public UserOrder getOrder() {
        return order;
    }

    public void setOrder(UserOrder order) {
        this.order = order;
    }
}
