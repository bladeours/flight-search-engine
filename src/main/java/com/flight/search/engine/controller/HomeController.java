package com.flight.search.engine.controller;

import com.flight.search.engine.service.AirportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final AirportService airportService;

    public HomeController(AirportService airportService) {
        this.airportService = airportService;
    }


    @RequestMapping("/")
    public String showHomePage(Model model){

        model.addAttribute("airports", airportService.findAll());
        return "index";
    }

}
