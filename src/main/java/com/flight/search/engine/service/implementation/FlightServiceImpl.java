package com.flight.search.engine.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight.search.engine.dao.AirportDAO;
import com.flight.search.engine.dao.FlightDAO;
import com.flight.search.engine.service.FlightService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    @Override
    public List<FlightDAO> getFlightsForCodes(String departureCode, String arrivalCode,String date) {
        WebClient client = WebClient.create("");
        WebClient.ResponseSpec responseSpec = client.get()
                .uri("http://localhost:8082/flight/" + departureCode + "/" + arrivalCode + "?date=" + date)
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();
        List<FlightDAO> flights;
        try {
            flights = objectMapper.readValue(responseBody, new TypeReference<>() {});
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

    private static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
