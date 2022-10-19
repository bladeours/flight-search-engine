package com.flight.search.engine.controller;

import com.flight.search.engine.dao.FlightDAO;
import com.flight.search.engine.dao.UserDAO;
import com.flight.search.engine.exception.UserAlreadyExistsException;
import com.flight.search.engine.model.FlightMini;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.UserRepository;
import com.flight.search.engine.service.FlightService;
import com.flight.search.engine.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private ModelMapper modelMapper = new ModelMapper();
    public UserController(UserService userService, UserRepository userRepository, FlightService flightService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.flightService = flightService;
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
    public String showUserProfile(Principal principal){
        User user = userService.getUserByUsername(principal.getName());
        System.out.println(userService.getCart(user));
        return "userProfile";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable String id, Principal principal){
        User user = userService.getUserByUsername(principal.getName());
        userService.addToCart(Long.valueOf(id), user);
        return "redirect:/profile";
    }
}
