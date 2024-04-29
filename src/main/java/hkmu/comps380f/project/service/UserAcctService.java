package hkmu.comps380f.project.service;

import hkmu.comps380f.project.dao.BookInfoRepository;
import hkmu.comps380f.project.dao.UserAcctRepository;
import hkmu.comps380f.project.exception.AttachmentNotFound;
import hkmu.comps380f.project.exception.BookInfoNotFound;
import hkmu.comps380f.project.exception.EmptyContentException;
import hkmu.comps380f.project.exception.UserAcctNotFound;
import hkmu.comps380f.project.model.*;
import jakarta.annotation.Resource;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAcctService implements UserDetailsService {
    @Resource
    private UserAcctRepository userAcctRepo;
    @Resource
    BookCommentService bookCommentService;
    @Resource
    BookInfoService bookInfoService;
    @Resource
    BookInfoRepository bookInfoRepo;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserAcct userAcct = userAcctRepo.findById(username).orElse(null);
        if (userAcct == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : userAcct.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new User(userAcct.getUsername(), userAcct.getPassword(), authorities);
    }
    @Transactional
    public List<UserAcct> getUserAccts() {
        return userAcctRepo.findAll();
    }
    @Transactional
    public UserAcct getUserAcct(String username)
            throws UserAcctNotFound {
        UserAcct userAcct = userAcctRepo.findById(username).orElse(null);
        if (userAcct == null) {
            throw new UserAcctNotFound(username);
        }
        return userAcct;
    }
    @Transactional(rollbackFor = UserAcctNotFound.class)
    public void deleteUserAcct(String username)
            throws UserAcctNotFound {
        UserAcct userAcct = userAcctRepo.findById(username).orElse(null);
        if (userAcct == null) {
            throw new UserAcctNotFound(username);
        }

        bookCommentService.deleteCommentsByUsername(username);
//        userAcctRepo.findAllFav(username);

        for(BookInfo book : userAcct.getFavoriteBookList()) {
            book.getFavoriteUserList().remove(userAcct);
        }

        userAcctRepo.delete(userAcct);
    }
    @Transactional
    public UserAcct createUserAcct(String username, String password, String fullName, String emailAddr, String deliveryAddr, String[] roles)
            throws EmptyContentException {
        if (username.isEmpty() || password.isEmpty()) {
            throw new EmptyContentException();
        }
        UserAcct user = new UserAcct(username, password, fullName, emailAddr, deliveryAddr, roles);
        return userAcctRepo.save(user);
    }
    @Transactional
    public UserAcct updateUserAcct(String username, String password, String fullName, String emailAddr, String deliveryAddr, String[] roles)
            throws UserAcctNotFound {
        UserAcct userAcct = userAcctRepo.findById(username).orElse(null);
        if (userAcct == null) {
            throw new UserAcctNotFound(username);
        }
        if(!password.isEmpty()) userAcct.setPassword(password);
        userAcct.setFullName(fullName);
        userAcct.setEmailAddr(emailAddr);
        userAcct.setDeliveryAddr(deliveryAddr);
        userAcct.setRoles(roles);

        return userAcctRepo.save(userAcct);
    }
    @Transactional
    public UserAcct registerUserAcct(String username, String password, String fullName, String emailAddr, String deliveryAddr)
            throws EmptyContentException {
        if (username.isEmpty() || password.isEmpty()) {
            throw new EmptyContentException();
        }
        UserAcct user = new UserAcct(username, password, fullName, emailAddr, deliveryAddr);
        return userAcctRepo.save(user);
    }
    @Transactional
    public UserAcct updateUserInfo(String username, String password, String fullName, String emailAddr, String deliveryAddr)
            throws UserAcctNotFound {
        UserAcct userAcct = userAcctRepo.findById(username).orElse(null);
        if (userAcct == null) {
            throw new UserAcctNotFound(username);
        }
        if(!password.isEmpty()) userAcct.setPassword(password);
        userAcct.setFullName(fullName);
        userAcct.setEmailAddr(emailAddr);
        userAcct.setDeliveryAddr(deliveryAddr);

        return userAcctRepo.save(userAcct);
    }
    @Transactional
    public UserAcct addFavoriteList(String username, long bookId)
            throws UserAcctNotFound, BookInfoNotFound {
        UserAcct userAcct = userAcctRepo.findById(username).orElse(null);
        if (userAcct == null) {
            throw new UserAcctNotFound(username);
        }
        BookInfo bookInfo = bookInfoService.getBookInfo(bookId);
//        for (BookInfo info : userAcct.getFavoriteBookList()) {
//            if(info.getId() == bookId) {
//                return userAcctRepo.save(userAcct);
//            }
//        }
        userAcct.getFavoriteBookList().add(bookInfo);


        return userAcctRepo.save(userAcct);
    }

    @Transactional
    public UserAcct removeFavoriteList(String username, long bookId)
            throws UserAcctNotFound {
        UserAcct userAcct = userAcctRepo.findById(username).orElse(null);
        if (userAcct == null) {
            throw new UserAcctNotFound(username);
        }

//        for(BookInfo book : userAcct.getFavoriteBookList()){
//            if(book.getId() == bookId){ userAcct.getFavoriteBookList().remove(book); break; }
//        }
//
//        return userAcctRepo.save(userAcct);
        for(BookInfo book : userAcct.getFavoriteBookList()){
            if(book.getId() == bookId){ userAcct.removeFavoriteBook(book); break; }
        }
        return userAcct;
    }
}
