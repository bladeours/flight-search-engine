package com.flight.search.engine.service;


import com.flight.search.engine.dao.FlightDAO;
import com.flight.search.engine.dao.UserDAO;
import com.flight.search.engine.model.FlightMini;
import com.flight.search.engine.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    void registerUser(UserDAO userDAO);
    User getUserByUsername(String username);
    void changePassword(User user, String password);
    boolean checkPassword(User user, String password);
}
