package com.flight.search.engine.service;

import com.flight.search.engine.dto.FlightDTO;

import java.util.List;

public interface FlightService {
    List<FlightDTO> getFlightsForCodes(String departureCode, String arrivalCode, String date);

    String getDateToShow(String Date);

    FlightDTO getFlight(String id);

    List<FlightDTO> getAllFlights();
}
