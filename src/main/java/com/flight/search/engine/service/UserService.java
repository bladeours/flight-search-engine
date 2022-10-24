package com.flight.search.engine.service;


import com.flight.search.engine.dao.UserDAO;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.CartItemRepository;
import com.flight.search.engine.repository.UserRepository;


public interface UserService{
    void registerUser(UserDAO userDAO);
    User getUserByUsername(String username);
    void changePassword(User user, String password);
    boolean checkPassword(User user, String password);
    void save(User user);
    void removeUser(User user);
}
