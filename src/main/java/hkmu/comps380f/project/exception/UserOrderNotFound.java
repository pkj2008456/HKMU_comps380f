package hkmu.comps380f.project.exception;

public class UserOrderNotFound extends Exception {
    public UserOrderNotFound(long orderId) {
        super("UserOrder " + orderId + " does not exist.");
    }
}
