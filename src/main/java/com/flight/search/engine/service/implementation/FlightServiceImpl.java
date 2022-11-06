package com.flight.search.engine.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight.search.engine.dto.FlightDTO;
import com.flight.search.engine.service.FlightService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {


    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    String baseUrl = "http://localhost:8082";

    public FlightServiceImpl() {
    }

    public FlightServiceImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<FlightDTO> getFlightsForCodes(String departureCode, String arrivalCode, String date) {
        WebClient client = WebClient.create("");
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(baseUrl + "/flight/" + departureCode + "/" + arrivalCode + "?date=" + date)
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();
        List<FlightDTO> flights;

        try {
            flights = objectMapper.readValue(responseBody, new TypeReference<>() {});
            for(FlightDTO flight : flights){
                flight.setArrivalDate(new Timestamp(0));
                flight.getArrivalDate().setTime(flight.getDepartureDate().getTime() + flight.getFlightTime().getTime());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return flights;
    }

    @Override
    public String getDateToShow(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateTime = LocalDate.parse(date, formatter);
        String dateToShow =  capitalize(localDateTime.getDayOfWeek().toString()) + " " + localDateTime.getDayOfMonth() +
                 " " + capitalize(localDateTime.getMonth().toString() + ", " + localDateTime.getYear());
        return dateToShow;
    }

    @Override
    public FlightDTO getFlight(String id) {
        WebClient client = WebClient.create("");
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(baseUrl + "/flight/" + id)
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();
        FlightDTO flight;
        try {
            flight = objectMapper.readValue(responseBody, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        flight.setArrivalDate(new Timestamp(0));
        flight.getArrivalDate().setTime(flight.getDepartureDate().getTime() + flight.getFlightTime().getTime());
        return  flight;
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        WebClient client = WebClient.create("");
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(baseUrl + "/flight/all")
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();
        List<FlightDTO> flights;

        try {
            flights = objectMapper.readValue(responseBody, new TypeReference<>() {});
            for(FlightDTO flight : flights){
                flight.setArrivalDate(new Timestamp(0));
                flight.getArrivalDate().setTime(flight.getDepartureDate().getTime() + flight.getFlightTime().getTime());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return flights;
    }

    private static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
