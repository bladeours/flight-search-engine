package com.flight.search.engine.controller;

import com.flight.search.engine.dao.FlightDAO;
import com.flight.search.engine.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping("/flight/{id}")
    public String showFlight(Model model, @PathVariable String id){
        FlightDAO flight = flightService.getFlight(id);
        model.addAttribute("flight",flight);
        return "flight";
    }
}
