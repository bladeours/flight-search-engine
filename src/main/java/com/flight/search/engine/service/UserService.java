package com.flight.search.engine.service;


import com.flight.search.engine.dto.UserDTO;
import com.flight.search.engine.model.Authority;
import com.flight.search.engine.model.User;

import java.util.List;


public interface UserService{
    User registerUser(UserDTO userDTO);
    User getUserByUsername(String username);
    void changePassword(User user, String password);
    boolean checkPassword(User user, String password);
    void save(User user);
    void removeUser(User user);
    List<User> getAllUsers();
    void removeUserById(long id);
    void changeEnabled(long id);
    void addAuthorityToUser(User user, Authority authority);
}
