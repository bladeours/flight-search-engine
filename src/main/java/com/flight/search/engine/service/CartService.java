package com.flight.search.engine.service;

import com.flight.search.engine.dao.CartItemDTO;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.CartItemRepository;

import java.util.List;

public interface CartService {
    void addToCart(Long flightId, User user);
    List<CartItemDTO> getCart(User user);
    double sumPrices(User user);
    void removeFromCart(Long id, User user);
}
