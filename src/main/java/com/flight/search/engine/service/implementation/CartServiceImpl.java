package com.flight.search.engine.service.implementation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight.search.engine.dao.CartItemDTO;
import com.flight.search.engine.model.Cart;
import com.flight.search.engine.model.CartItem;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.CartItemRepository;
import com.flight.search.engine.repository.CartRepository;
import com.flight.search.engine.service.CartService;
import com.flight.search.engine.service.FlightService;
import com.flight.search.engine.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final FlightService flightService;
    private final UserService userService;
    private final CartRepository cartRepository;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public CartServiceImpl(CartItemRepository cartItemRepository, FlightService flightService, UserService userService, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.flightService = flightService;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }


    @Override
    public void addToCart(Long flightId, User user) {
        CartItem cartItem = new CartItem();
        cartItem.setIdFromApi(flightId);
        cartItem.setCart(user.getCart());
        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItemDTO> getCart(User user) {
        List<CartItemDTO> flights = new ArrayList<>();
        for(CartItem cartItem: user.getCart().getCartItems()){
            CartItemDTO cartItemDto = objectMapper.
                    convertValue(flightService.getFlight(String.valueOf(cartItem.getIdFromApi())), CartItemDTO.class);
            cartItemDto.setIdFromCart(cartItem.getId());
            flights.add(cartItemDto);
        }
        return flights;
    }


    @Override
    public double sumPrices(User user) {
        List<CartItemDTO> cartItems = getCart(user);
        double sum = 0;
        for(CartItemDTO cartItem: cartItems){
            sum += cartItem.getPrice();
        }
        return sum;
    }

    @Override
    public void removeFromCart(Long id, User user) {
        CartItem cartItemToDelete = cartItemRepository.findById(id).get();
        if(cartItemToDelete.getCart().getUser().equals(user)){
            cartItemRepository.deleteById(id);
        }
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    @Override
    public void removeCart(Cart cart) {
        for(CartItem cartItem : cart.getCartItems()){
            removeCartItem(cartItem);
        }
        cartRepository.delete(cart);
    }
}
