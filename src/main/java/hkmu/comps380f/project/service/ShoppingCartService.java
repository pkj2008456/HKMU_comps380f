package hkmu.comps380f.project.service;

import hkmu.comps380f.project.controller.OrderController;
import hkmu.comps380f.project.dao.BookInfoRepository;
import hkmu.comps380f.project.model.BookInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartService {
    @Resource
    BookInfoService bookInfoService;

    public Map<Long, Integer> getShoppingCart(HttpSession session) {
        Map<Long, Integer> shoppingCartItemList = (Map<Long, Integer>)session.getAttribute("shoppingCart");
        if(shoppingCartItemList == null){
            shoppingCartItemList = new HashMap<Long, Integer>();
        }

        return shoppingCartItemList;
    }

    public void setShoppingCart(HttpSession session, Map<Long, Integer> shoppingCartItemList) {
        session.setAttribute("shoppingCart", shoppingCartItemList);
    }

    public void clearShoppingCart(HttpSession session) {
        session.setAttribute("shoppingCart", null);
    }

    public void editBookQuantity(HttpSession session, long bookId, Integer quantity) {
        Map<Long, Integer> shoppingCartItemList = this.getShoppingCart(session);

        if(!shoppingCartItemList.containsKey(bookId)) {
            shoppingCartItemList.put(bookId, 0);
        }
        shoppingCartItemList.put(bookId, quantity);

        this.setShoppingCart(session, shoppingCartItemList);
    }

    public void addBook(HttpSession session, long bookId, Integer quantity) {
        Map<Long, Integer> shoppingCartItemList = this.getShoppingCart(session);

        if(!shoppingCartItemList.containsKey(bookId)) {
            shoppingCartItemList.put(bookId, 0);
        }
        shoppingCartItemList.put(bookId, shoppingCartItemList.get(bookId) + quantity);

        this.setShoppingCart(session, shoppingCartItemList);
    }

    public void deleteBook(HttpSession session, long bookId) {
        Map<Long, Integer> shoppingCartItemList = this.getShoppingCart(session);

        shoppingCartItemList.remove(bookId);

        this.setShoppingCart(session, shoppingCartItemList);
    }

    public List<ShoppingCartItem> mapBookInfo(HttpSession session, List<BookInfo> bookInfos) {
        List<ShoppingCartItem> cartItemList = new ArrayList<ShoppingCartItem>();

        Map<Long, Integer> shoppingCartItemList = this.getShoppingCart(session);

        for(BookInfo item : bookInfos) {
            if(shoppingCartItemList.containsKey(item.getId())) {
                cartItemList.add(new ShoppingCartItem(item.getId(), item.getName(), item.getAuthor(), item.getPrice(), item.getDescription(), shoppingCartItemList.get(item.getId())));
            }
        }

        return cartItemList;
    }

    public List<ShoppingCartItem> getShoppingCartWithBookInfo(HttpSession session) {
        List<BookInfo> bookInfos = bookInfoService.getAvailableBookInfos();

        List<ShoppingCartItem> cartItemList = new ArrayList<ShoppingCartItem>();

        Map<Long, Integer> shoppingCartItemList = this.getShoppingCart(session);

        for(BookInfo item : bookInfos) {
            if(shoppingCartItemList.containsKey(item.getId())) {
                cartItemList.add(new ShoppingCartItem(item.getId(), item.getName(), item.getAuthor(), item.getPrice(), item.getDescription(), shoppingCartItemList.get(item.getId())));
            }
        }

        return cartItemList;
    }

    public static double calcTotalPrice(List<ShoppingCartItem> cartItemList) {
        double totalPrice = 0;

        for(ShoppingCartItem item : cartItemList) {
            totalPrice += (item.getPrice() * item.getQuantity());
        }

        return totalPrice;
    }

    public static class ShoppingCartItem {
        private long id;
        private String name;
        private String author;
        private double price;
        private String description;
        private Integer quantity;

        public ShoppingCartItem(long id, String name, Integer quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }

        public ShoppingCartItem(long id, String name, String author, double price, String description, Integer quantity) {
            this.id = id;
            this.name = name;
            this.author = author;
            this.price = price;
            this.description = description;
            this.quantity = quantity;
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

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }
}
