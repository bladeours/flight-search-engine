package com.flight.search.engine.service.implementation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight.search.engine.dao.CartItemDTO;
import com.flight.search.engine.model.Cart;
import com.flight.search.engine.model.CartItem;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.CartItemRepository;
import com.flight.search.engine.repository.CartRepository;
import com.flight.search.engine.service.CartItemService;
import com.flight.search.engine.service.CartService;
import com.flight.search.engine.service.FlightService;
import com.flight.search.engine.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartItemService cartItemService;
    private final FlightService flightService;
    private final CartRepository cartRepository;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public CartServiceImpl(CartItemService cartItemService, FlightService flightService, CartRepository cartRepository) {
        this.cartItemService = cartItemService;
        this.flightService = flightService;
        this.cartRepository = cartRepository;
    }


    @Override
    public void addToCart(Long flightId, User user) {
        CartItem cartItem = cartItemService.findByFlightIdAndUser(flightId, user);
        Cart cart = user.getCart();

        if(cartItem == null){
            cartItem = new CartItem();
            cartItem.setIdFromApi(flightId);
            cartItem.setCart(cart);
            cartItem.setAmount(1);
            System.out.println("nie ma takiego lotu");
        }else{
            cartItem.setAmount(cartItem.getAmount() + 1);
        }
        cartItemService.save(cartItem);

    }

    @Override
    public List<CartItemDTO> getCart(User user) {
        List<CartItemDTO> flights = new ArrayList<>();
        for(CartItem cartItem: user.getCart().getCartItems()){
            CartItemDTO cartItemDto = objectMapper.
                    convertValue(flightService.getFlight(String.valueOf(cartItem.getIdFromApi())), CartItemDTO.class);
            cartItemDto.setIdFromCart(cartItem.getId());
            cartItemDto.setAmount(cartItem.getAmount());
            flights.add(cartItemDto);
        }
        return flights;
    }


    @Override
    public double sumPrices(User user) {
        List<CartItemDTO> cartItems = getCart(user);
        double sum = 0;
        for(CartItemDTO cartItem: cartItems){
            sum += cartItem.getPrice() * cartItem.getAmount();
        }
        return sum;
    }

    @Override
    public void removeFromCart(Long id, User user) {
        CartItem cartItemToDelete = cartItemService.findById(id);
        System.out.println(cartItemToDelete.getAmount());
        if(cartItemToDelete.getAmount() > 1){
            cartItemToDelete.setAmount(cartItemToDelete.getAmount() - 1);
            cartItemService.save(cartItemToDelete);
        }else{
            if(cartItemToDelete.getCart().getUser().equals(user)){
                cartItemService.removeCartItemById(id);
            }
        }
    }


    @Override
    public void removeCart(Cart cart) {
        for(CartItem cartItem : cart.getCartItems()){
            cartItemService.removeCartItem(cartItem);
        }
        cartRepository.delete(cart);
    }
}
