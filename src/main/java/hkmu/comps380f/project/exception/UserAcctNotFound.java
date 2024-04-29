package hkmu.comps380f.project.exception;

public class UserAcctNotFound extends Exception {
    public UserAcctNotFound(String username) {
        super("UserAcct " + username + " does not exist.");
    }
}
