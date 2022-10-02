package com.flight.search.engine.controller;

import com.flight.search.engine.dao.FlightFormDAO;
import com.flight.search.engine.service.AirportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final AirportService airportService;

    public HomeController(AirportService airportService) {
        this.airportService = airportService;
    }


    @GetMapping("/")
    public String showHomePage(Model model){
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("flightForm", new FlightFormDAO());
        return "index";
    }

    @PostMapping("/")
    public String showResults(@ModelAttribute FlightFormDAO flightForm, Model model){
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("flightForm", flightForm);
        System.out.println(flightForm);
        model.addAttribute("flightForm", new FlightFormDAO()); //clear form
        return "index";
    }

}
