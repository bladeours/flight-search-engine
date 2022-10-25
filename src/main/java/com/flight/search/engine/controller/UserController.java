package com.flight.search.engine.controller;

import com.flight.search.engine.dao.PasswordDTO;
import com.flight.search.engine.dao.UserDAO;
import com.flight.search.engine.exception.PasswordValidationException;
import com.flight.search.engine.exception.UserAlreadyExistsException;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.UserRepository;
import com.flight.search.engine.service.CartService;
import com.flight.search.engine.service.FlightService;
import com.flight.search.engine.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/login")
    public String login(Principal principal){
        if(principal == null) return "login";
        System.out.println();
        return "redirect:/";
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
    public String showUserProfile(Principal principal, Model model, Authentication authentication){
        System.out.println(authentication.getAuthorities());

        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("flights",cartService.getCart(user));
        model.addAttribute("sum",cartService.sumPrices(user));
        model.addAttribute("password", new PasswordDTO());
        return "userProfile";
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute("password") @Valid PasswordDTO passwordDTO,
                                 BindingResult bindingResult, Model model, Principal principal ){

        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("flights",cartService.getCart(user));
        model.addAttribute("sum",cartService.sumPrices(user));
        if(!bindingResult.hasErrors()){
            if(userService.checkPassword(user,passwordDTO.getOldPassword())){
                userService.changePassword(user, passwordDTO.getNewPassword());
                model.addAttribute("successMessage", "password changed");
                return "userProfile";
            }
            model.addAttribute("errorMessage", "incorrect current password");
        }
        return "userProfile";
    }

    @GetMapping("/removeUser")
    public String removeUser(Principal principal, HttpServletRequest httpServletRequest) throws ServletException {
        User user = userService.getUserByUsername(principal.getName());
        userService.removeUser(user);
        httpServletRequest.logout();
        return "redirect:/";
    }
}
