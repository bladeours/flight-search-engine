package com.flight.search.engine.controller;

import com.flight.search.engine.model.User;
import com.flight.search.engine.service.CartService;
import com.flight.search.engine.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable String id, Principal principal){
        User user = userService.getUserByUsername(principal.getName());
        cartService.addToCart(Long.valueOf(id), user);
        return "redirect:/profile";
    }

    @GetMapping("/removeFromCart/{id}")
    public String removeFromCart(@PathVariable String id, Principal principal){
        User user = userService.getUserByUsername(principal.getName());
        cartService.removeFromCart(Long.valueOf(id), user);
        return "redirect:/profile";
    }

}
