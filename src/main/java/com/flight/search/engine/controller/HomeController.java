package com.flight.search.engine.controller;

import com.flight.search.engine.dao.FlightDAO;
import com.flight.search.engine.dao.FlightFormDAO;
import com.flight.search.engine.service.AirportService;
import com.flight.search.engine.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        model.addAttribute("airports", airportService.findAll());
        FlightFormDAO flightFormDAO = new FlightFormDAO();
        flightFormDAO.setDepartureDate(LocalDate.now().toString());
        model.addAttribute("flightForm", flightFormDAO);

        return "index";
    }

    @PostMapping("/search")
    public String showResults(@Valid @ModelAttribute("flightForm") FlightFormDAO flightForm, BindingResult bindingResult,
                              Model model){
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("flightForm", flightForm);
        if(bindingResult.hasErrors()){
            return "index";
        }

        if(!Objects.equals(flightForm.getDepartureAirportCode(), "") || !Objects.equals(flightForm.getArrivalAirportCode(), "")){
            model.addAttribute("departureAirport",airportService.getAirport(flightForm.getDepartureAirportCode()));
            model.addAttribute("arrivalAirport",airportService.getAirport(flightForm.getArrivalAirportCode()));
            List<FlightDAO> flights = flightService.getFlightsForCodes(flightForm.getDepartureAirportCode(),
                    flightForm.getArrivalAirportCode(), flightForm.getDepartureDate());
            model.addAttribute("flights",flights);
            model.addAttribute("dateToShow", flightService.getDateToShow(flightForm.getDepartureDate()));

        }

        return "index";
    }

}
