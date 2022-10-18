package com.flight.search.engine.controller;

import com.flight.search.engine.dao.UserDAO;
import com.flight.search.engine.model.Authority;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.AuthorityRepository;
import com.flight.search.engine.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private ModelMapper modelMapper = new ModelMapper();
    public UserController(UserRepository userRepository, AuthorityRepository authorityRepository) {
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
    public String registerUser(@ModelAttribute("user") @Valid UserDAO userDAO){
        User user = new User();
        user.setEnabled(true);
//        BCrypt.hashpw(userDAO.getUsername(), BCrypt.gensalt());
        user.setUsername(userDAO.getUsername());
        user.setPassword(BCrypt.hashpw(userDAO.getUsername(), BCrypt.gensalt()));
        Authority authority = authorityRepository.findById(1).get();

        HashSet<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        user.setRoles(authorities);
        userRepository.save(user);
        System.out.println("poszlo!");
        return "successRegister";
    }

    @RequestMapping("/profile")
    public String showUserProfile(){

        return "userProfile";
    }
}
