package com.flight.search.engine.service.implementation;

import com.flight.search.engine.dto.UserDTO;
import com.flight.search.engine.exception.UserAlreadyExistsException;
import com.flight.search.engine.model.Authority;
import com.flight.search.engine.model.Cart;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.UserRepository;
import com.flight.search.engine.service.AuthorityService;
import com.flight.search.engine.service.CartService;
import com.flight.search.engine.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private final CartService cartService;

    public UserServiceImpl(UserRepository userRepository, AuthorityService authorityService,
                           @Lazy CartService cartService) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
        this.cartService = cartService;
    }

    @Override
    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setEnabled(true);
        user.setUsername(userDTO.getUsername());
        user.setPassword(encryptPassword(userDTO.getPassword()));
        HashSet<Authority> authorities = authorityService.getAuthoritiesByName( "ADMIN1");
        user.setAuthorities(authorities);
        user.setCart(new Cart());
        if(userExists(userDTO.getUsername())){
            throw new UserAlreadyExistsException("There is an account with that username: "
                    + userDTO.getUsername());
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void changePassword(User user, String password) {
        user.setPassword(encryptPassword(password));
        userRepository.save(user);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return BCrypt.checkpw(password,user.getPassword());
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUser(User user) {
        cartService.removeCart(user.getCart());
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void removeUserById(long id) {
        User user = userRepository.findById(id).get();
        removeUser(user);
    }

    @Override
    public void changeEnabled(long id) {
        User user = userRepository.findById(id).get();
        user.setEnabled(!user.isEnabled());
        save(user);
    }

    @Override
    public void addAuthorityToUser(User user, Authority authority) {
        Set<Authority> authorities = user.getAuthorities();
        authorities.add(authority);
        user.setAuthorities(authorities);
        userRepository.save(user);
    }


    private String encryptPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean userExists(String username){
        return userRepository.getUserByUsername(username) != null;
    }
}
