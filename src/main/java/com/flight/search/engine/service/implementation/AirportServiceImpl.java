package com.flight.search.engine.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.flight.search.engine.dao.AirportDAO;
import com.flight.search.engine.service.AirportService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public List<AirportDAO> findAll() {
        WebClient client = WebClient.create("");
        WebClient.ResponseSpec responseSpec = client.get()
                .uri("http://localhost:8082/airport/airports")
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();
        List<AirportDAO> airports;
        try {
            airports = objectMapper.readValue(responseBody, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return airports;

    }

    @Override
    public AirportDAO getAirport(String code) {
        WebClient client = WebClient.create("");
        WebClient.ResponseSpec responseSpec = client.get()
                .uri("http://localhost:8082/airport/" + code)
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();
        AirportDAO airport;
        try {
            airport = objectMapper.readValue(responseBody, AirportDAO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return airport;
    }
}
