package com.flight.search.engine.service;

import com.flight.search.engine.dao.FlightDAO;
import com.flight.search.engine.model.User;

import java.util.List;

public interface CartService {
    void addToCart(Long flightId, User user);
    List<FlightDAO> getCart(User user);
    double sumPrices(User user);
}
