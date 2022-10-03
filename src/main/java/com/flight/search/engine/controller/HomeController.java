package com.flight.search.engine.controller;

import com.flight.search.engine.dao.FlightFormDAO;
import com.flight.search.engine.service.AirportService;
import com.flight.search.engine.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final AirportService airportService;
    private final FlightService flightService;
    public HomeController(AirportService airportService, FlightService flightService) {
        this.airportService = airportService;
        this.flightService = flightService;
    }


//    @RequestMapping("/")
//    public String showHomePage(Model model){
////        model.addAttribute("airports", airportService.findAll());
//        model.addAttribute("flightForm", new FlightFormDAO());
//        return "index";
//    }

    @PostMapping("/")
    public String showResults(@ModelAttribute FlightFormDAO flightForm, Model model){

        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("flightForm", flightForm);
        model.addAttribute("flightForm", new FlightFormDAO()); //clear form
        model.addAttribute("departureAirport",airportService.getAirport(flightForm.getDepartureAirportCode()));
        model.addAttribute("arrivalAirport",airportService.getAirport(flightForm.getArrivalAirportCode()));
        model.addAttribute("flights", flightService.getFlightsForCodes(flightForm.getDepartureAirportCode(),
                flightForm.getArrivalAirportCode()));
        System.out.println(flightService.getFlightsForCodes(flightForm.getDepartureAirportCode(),
                flightForm.getArrivalAirportCode()));
        return "index";
    }

}
