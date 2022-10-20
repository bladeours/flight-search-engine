package com.flight.search.engine.controller;

import com.flight.search.engine.dao.UserDAO;
import com.flight.search.engine.exception.UserAlreadyExistsException;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.UserRepository;
import com.flight.search.engine.service.CartService;
import com.flight.search.engine.service.FlightService;
import com.flight.search.engine.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final FlightService flightService;
    private final CartService cartService;

    private ModelMapper modelMapper = new ModelMapper();
    public UserController(UserService userService, UserRepository userRepository, FlightService flightService,
                          @Lazy CartService cartService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.flightService = flightService;
        this.cartService = cartService;
    }


    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        UserDAO userDAO = new UserDAO();
        model.addAttribute("user", userDAO);
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid UserDAO userDAO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "registration";
        }
        try{
            userService.registerUser(userDAO);

        }catch (UserAlreadyExistsException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "registration";
        }

        return "redirect:/";
    }

    @RequestMapping("/profile")
    public String showUserProfile(Principal principal, Model model){
        User user = userService.getUserByUsername(principal.getName());
        System.out.println(cartService.getCart(user));
        model.addAttribute("flights",cartService.getCart(user));
        model.addAttribute("sum",cartService.sumPrices(user));
        return "userProfile";
    }


}
