package com.flight.search.engine.repository;

import com.flight.search.engine.model.Cart;
import com.flight.search.engine.model.CartItem;
import com.flight.search.engine.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartItemRepositoryTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    void itShouldReturnCartItemByFlightIdAndCart() {
        Cart cart = new Cart(new HashSet<>(), new User());

        CartItem cartItem = new CartItem(
                1L,
                cart,
                2
        );

        cartItemRepository.save(cartItem);
        assertThat(cartItemRepository.findByFlightIdAndCart(1L, cart)).isEqualTo(cartItem);

    }
}