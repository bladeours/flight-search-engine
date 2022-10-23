package com.flight.search.engine.service.implementation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight.search.engine.dao.CartItemDTO;
import com.flight.search.engine.model.CartItem;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.CartItemRepository;
import com.flight.search.engine.repository.UserRepository;
import com.flight.search.engine.service.CartService;
import com.flight.search.engine.service.FlightService;
import com.flight.search.engine.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final FlightService flightService;
    private final UserService userService;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public CartServiceImpl(CartItemRepository cartItemRepository, FlightService flightService, UserService userService) {
        this.cartItemRepository = cartItemRepository;
        this.flightService = flightService;
        this.userService = userService;
    }


    @Override
    public void addToCart(Long flightId, User user) {
        Set<CartItem> cartItemSet = user.getCartItems();
        CartItem cartItem = new CartItem();
        cartItem.setIdFromApi(flightId);
        cartItemSet.add(cartItem);
        user.setCartItems(cartItemSet);
        userService.save(user);
    }

    @Override
    public List<CartItemDTO> getCart(User user) {
        List<CartItemDTO> flights = new ArrayList<>();
        for(CartItem flightMini: user.getCartItems()){

            CartItemDTO cartItem = objectMapper.
                    convertValue(flightService.getFlight(String.valueOf(flightMini.getIdFromApi())), CartItemDTO.class);
            cartItem.setIdFromCart(flightMini.getId());
            flights.add(cartItem);

        }
        return flights;
    }


    @Override
    public double sumPrices(User user) {
        List<CartItemDTO> flights = getCart(user);
        double sum = 0;
        for(CartItemDTO flight: flights){
            sum += flight.getPrice();
        }
        return sum;
    }

    @Override
    public void removeFromCart(Long id, User user) {
        CartItem cartItemToDelete = cartItemRepository.findById(id).get();
        user.removeCartItem(cartItemToDelete);
        cartItemRepository.deleteById(id);
        userService.save(user);
    }
}
