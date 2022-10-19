package com.flight.search.engine.service;


import com.flight.search.engine.dao.UserDAO;
import org.springframework.stereotype.Service;


public interface UserService {
    void registerUser(UserDAO userDAO);
}
