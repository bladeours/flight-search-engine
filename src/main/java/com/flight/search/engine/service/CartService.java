package com.flight.search.engine.service;

import com.flight.search.engine.dto.CartItemDTO;
import com.flight.search.engine.model.Cart;
import com.flight.search.engine.model.User;

import java.util.List;

public interface CartService {
    void addToCart(Long flightId, User user);
    List<CartItemDTO> getCart(User user);
    double sumPrices(User user);
    void removeFromCart(Long id, User user);

    void removeCart(Cart cart);
}
