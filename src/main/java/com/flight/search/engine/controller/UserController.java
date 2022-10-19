package com.flight.search.engine.controller;

import com.flight.search.engine.dao.UserDAO;
import com.flight.search.engine.exception.UserAlreadyExistsException;
import com.flight.search.engine.model.Authority;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.AuthorityRepository;
import com.flight.search.engine.repository.UserRepository;
import com.flight.search.engine.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private ModelMapper modelMapper = new ModelMapper();
    public UserController(UserService userService, UserRepository userRepository,
                          AuthorityRepository authorityRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
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
    public String showUserProfile(){

        return "userProfile";
    }
}
