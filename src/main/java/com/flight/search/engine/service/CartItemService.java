package com.flight.search.engine.service;

import com.flight.search.engine.model.CartItem;

public interface CartItemService {
    void removeCartItem(CartItem cartItem);
    CartItem findById(long id);
    void removeCartItemById(long id);
    void save(CartItem cartItem);
}
