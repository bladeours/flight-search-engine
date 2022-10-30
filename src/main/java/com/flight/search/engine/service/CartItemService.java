package com.flight.search.engine.service;

import com.flight.search.engine.model.CartItem;
import com.flight.search.engine.model.User;

public interface CartItemService {
    void removeCartItem(CartItem cartItem);
    CartItem findById(long id);
    void removeCartItemById(long id);
    void save(CartItem cartItem);
    CartItem findByFlightIdAndUser(long flightId, User user);
}
