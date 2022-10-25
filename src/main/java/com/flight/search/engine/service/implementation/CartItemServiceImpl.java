package com.flight.search.engine.service.implementation;

import com.flight.search.engine.model.CartItem;
import com.flight.search.engine.repository.CartItemRepository;
import com.flight.search.engine.service.CartItemService;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItem findById(long id) {
        return cartItemRepository.findById(id).get();
    }

    @Override
    public void removeCartItemById(long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }
}
