package com.flight.search.engine.controller;

import com.flight.search.engine.dao.FlightFormDAO;
import com.flight.search.engine.service.AirportService;
import com.flight.search.engine.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.DefaultSessionAttributeStore;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@Controller
public class HomeController {

    private final AirportService airportService;
    private final FlightService flightService;
    public HomeController(AirportService airportService, FlightService flightService) {
        this.airportService = airportService;
        this.flightService = flightService;
    }


    @GetMapping("/")
    public String showHomePage(Model model){
        System.out.println("GET!");
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("flightForm", new FlightFormDAO());
        return "index";
    }

    @PostMapping("/search")
    public String showResults(@ModelAttribute("flightForm") FlightFormDAO flightForm, Model model, WebRequest request,
                              DefaultSessionAttributeStore store, SessionStatus status){
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("flightForm", flightForm);
//        model.addAttribute("flightForm", new FlightFormDAO());//clear form
        System.out.println("departure airport: " + flightForm.getDepartureAirportCode());
        if(!Objects.equals(flightForm.getDepartureAirportCode(), "") || !Objects.equals(flightForm.getArrivalAirportCode(), "")){
            model.addAttribute("departureAirport",airportService.getAirport(flightForm.getDepartureAirportCode()));
            model.addAttribute("arrivalAirport",airportService.getAirport(flightForm.getArrivalAirportCode()));
            model.addAttribute("flights", flightService.getFlightsForCodes(flightForm.getDepartureAirportCode(),
                    flightForm.getArrivalAirportCode(), flightForm.getDepartureDate()));
            model.addAttribute("dateToShow", flightService.getDateToShow(flightForm.getDepartureDate()));
        }

        return "index";
    }

}
