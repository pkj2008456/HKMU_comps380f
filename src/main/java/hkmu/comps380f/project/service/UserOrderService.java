package hkmu.comps380f.project.service;

import hkmu.comps380f.project.controller.OrderController;
import hkmu.comps380f.project.dao.UserOrderRepository;
import hkmu.comps380f.project.exception.EmptyContentException;
import hkmu.comps380f.project.exception.UserOrderNotFound;
import hkmu.comps380f.project.model.UserAcct;
import hkmu.comps380f.project.model.UserOrder;
import hkmu.comps380f.project.model.UserOrderBook;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserOrderService {
    @Resource
    UserOrderRepository userOrderRepo;

    @Transactional
    public List<UserOrder> getUserOrders() {
        return userOrderRepo.findAll();
    }

    @Transactional
    public UserOrder getUserOrder(long orderId)
            throws UserOrderNotFound {
        UserOrder order = userOrderRepo.findById(orderId).orElse(null);
        if(order == null){
            throw new UserOrderNotFound(orderId);
        }
        return order;
    }

    @Transactional
    public List<UserOrder> getUserOrdersByUserId(String username) {
        List<UserOrder> order = userOrderRepo.findAllByUserIdOrderByCreateTimeDesc(username);
        return order;
    }

    @Transactional
    public UserOrder getUserOrderByUserId(String username, long orderId)
            throws UserOrderNotFound {
        UserOrder order = userOrderRepo.findByUserIdAndIdOrderByCreateTime(username, orderId);
        if(order == null){
            throw new UserOrderNotFound(orderId);
        }
        return order;
    }

    @Transactional
    public UserOrder createUserOrder(
            UserAcct user,
            OrderController.CheckoutForm checkoutForm,
            List<ShoppingCartService.ShoppingCartItem> shoppingCartItemList
    ) throws EmptyContentException {
        if (user == null || !checkoutForm.isValid() || shoppingCartItemList.isEmpty()) {
            throw new EmptyContentException();
        }
        Date now = new Date();

        UserOrder order = new UserOrder();
        order.setFullName(checkoutForm.getFullName());
        order.setEmailAddr(checkoutForm.getEmailAddr());
        order.setDeliveryAddr(checkoutForm.getDeliveryAddr());
        order.setUser(user);
        order.setCreateTime(now);
        order.setLastUpdateTime(now);
        order.setTotalPrice(ShoppingCartService.calcTotalPrice(shoppingCartItemList));

        for(ShoppingCartService.ShoppingCartItem item : shoppingCartItemList) {
            UserOrderBook orderBook = new UserOrderBook(
                    item.getQuantity(),
                    item.getName(),
                    item.getAuthor(),
                    item.getPrice(),
                    item.getDescription(),
                    order
            );
            order.getBooks().add(orderBook);
        }

        return userOrderRepo.save(order);
    }
}
