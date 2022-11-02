package com.flight.search.engine.service.implementation;

import com.flight.search.engine.dto.CartItemDTO;
import com.flight.search.engine.model.Cart;
import com.flight.search.engine.model.CartItem;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.CartRepository;
import com.flight.search.engine.service.CartItemService;
import com.flight.search.engine.service.CartService;
import com.flight.search.engine.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private CartItemService cartItemService;
    @Mock
    private FlightService flightService;
    @Mock
    private CartRepository cartRepository;

    private CartServiceImpl cartService;
    private CartService spyCartService;
    @BeforeEach
    void setUp() {
        cartService = new CartServiceImpl(cartItemService, flightService,cartRepository );
        spyCartService = Mockito.spy(cartService);
    }

    @Test
    void ShouldAddCartItemToCart_EvenIfCartItemIsNull() {
        User testUser = new User();
        Mockito.when(cartItemService.findByFlightIdAndUser(1L, testUser))
                        .thenReturn(null);
        cartService.addToCart(1L, testUser);
        verify(cartItemService).save(any(CartItem.class));
    }

    @Test
    void ShouldAddOneToAmount_WhenCartItemExists(){
        User testUser = new User();
        CartItem cartItem = new CartItem(
                1L,
                new Cart(),
                1
        );
        Mockito.when(cartItemService.findByFlightIdAndUser(1L, testUser))
                .thenReturn(cartItem);
        int oldValue = cartItem.getAmount();
        cartService.addToCart(1L, testUser);
        verify(cartItemService).save(cartItem);
        assertThat(cartItem.getAmount()).isEqualTo(oldValue + 1);
    }

    @Test
    void shouldSumPrices(){
        CartItemDTO cartItem1 = new CartItemDTO();
        cartItem1.setAmount(2);
        cartItem1.setPrice(100.50);

        CartItemDTO cartItem2 = new CartItemDTO();
        cartItem2.setAmount(1);
        cartItem2.setPrice(200);
        List<CartItemDTO> cartItems = new ArrayList<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        doReturn(cartItems).when(spyCartService).getCart(any());
        assertThat(spyCartService.sumPrices(new User())).isEqualTo(401);
    }

    @Test
    void shouldDecreaseAmount_InsteadOfDeleting_IfAmountIsBiggerThanOne(){
        CartItem cartItem = new CartItem();
        cartItem.setAmount(2);
        int oldValue = cartItem.getAmount();
        when(cartItemService.findById(1L)).thenReturn(cartItem);
        cartService.removeFromCart(1L, new User());
        assertThat(cartItem.getAmount()).isEqualTo(oldValue - 1);
        verify(cartItemService).save(any());
    }

    @Test
    void shouldRemove_IfAmountIsOne(){
        User testUser = new User();

        CartItem cartItem = new CartItem();
        cartItem.setAmount(1);
        cartItem.setCart(new Cart());
        cartItem.getCart().setUser(testUser);
        when(cartItemService.findById(1L)).thenReturn(cartItem);
        cartService.removeFromCart(1L, testUser);
        verify(cartItemService).removeCartItemById(1L);
    }




}