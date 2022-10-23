package com.flight.search.engine.service.implementation;

import com.flight.search.engine.dao.FlightDAO;
import com.flight.search.engine.dao.UserDAO;
import com.flight.search.engine.exception.UserAlreadyExistsException;
import com.flight.search.engine.model.Authority;
import com.flight.search.engine.model.FlightMini;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.UserRepository;
import com.flight.search.engine.service.AuthorityService;
import com.flight.search.engine.service.FlightService;
import com.flight.search.engine.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityService authorityService;

    public UserServiceImpl(UserRepository userRepository, AuthorityService authorityService) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
    }

    @Override
    public void registerUser(UserDAO userDAO) {
        User user = new User();
        user.setEnabled(true);
        user.setUsername(userDAO.getUsername());
        user.setPassword(encryptPassword(userDAO.getPassword()));
        HashSet<Authority> authorities = authorityService.getAuthoritiesByName("ADMIN", "USER");
        user.setRoles(authorities);
        if(userExists(userDAO.getUsername())){
            throw new UserAlreadyExistsException("There is an account with that username: "
                    + userDAO.getUsername());
        }
        userRepository.save(user);
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


    private String encryptPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean userExists(String username){
        return userRepository.getUserByUsername(username) != null;
    }
}
