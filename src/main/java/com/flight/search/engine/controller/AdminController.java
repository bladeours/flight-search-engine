package com.flight.search.engine.controller;

import com.flight.search.engine.service.FlightService;
import com.flight.search.engine.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    private final UserService userService;
    private final FlightService flightService;
    public AdminController(UserService userService, FlightService flightService) {
        this.userService = userService;
        this.flightService = flightService;
    }

    @RequestMapping("/admin")
    public String showAdminPage(Model model){
        model.addAttribute("users",userService.getAllUsers());
        model.addAttribute("flights", flightService.getAllFlights());
        return "admin";
    }

    @RequestMapping("/admin/removeUser/{id}")
    public String removeUser(@PathVariable long id){
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/changeEnabled/{id}")
    public String changeEnabled(@PathVariable long id){
        userService.changeEnabled(id);
        return "redirect:/admin";
    }

}
