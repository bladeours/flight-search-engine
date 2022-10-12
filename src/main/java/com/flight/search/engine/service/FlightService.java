package com.flight.search.engine.service;

import com.flight.search.engine.dao.AirportDAO;
import com.flight.search.engine.dao.FlightDAO;

import java.util.List;

public interface FlightService {
    List<FlightDAO> getFlightsForCodes(String departureCode, String arrivalCode, String date);

    String getDateToShow(String Date);

    FlightDAO getFlight(String id);
}
