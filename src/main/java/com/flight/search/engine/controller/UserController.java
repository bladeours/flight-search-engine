package com.flight.search.engine.controller;

import com.flight.search.engine.dao.FlightDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/profile")
    public String showUserProfile(){

        return "userProfile";
    }
}
