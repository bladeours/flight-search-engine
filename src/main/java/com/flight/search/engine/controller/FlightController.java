package com.flight.search.engine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FlightController {

    @RequestMapping("/flight")
    public String showFlight(){
        return "flight";
    }
}
