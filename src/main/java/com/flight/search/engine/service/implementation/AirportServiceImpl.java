package com.flight.search.engine.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight.search.engine.dto.AirportDTO;
import com.flight.search.engine.property.ApiProperty;
import com.flight.search.engine.service.AirportService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {
    private final ApiProperty apiProperty;

    public AirportServiceImpl(ApiProperty apiProperty) {
        this.apiProperty = apiProperty;
    }

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public List<AirportDTO> findAll() {
        WebClient client = WebClient.create("");
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(apiProperty.getUrl() + "/airport/all")
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();
        List<AirportDTO> airports;
        try {
            airports = objectMapper.readValue(responseBody, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return airports;

    }

    @Override
    public AirportDTO getAirport(String code) {
        WebClient client = WebClient.create("");
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(apiProperty.getUrl() + "/airport/" + code)
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();
        AirportDTO airport;
        try {
            airport = objectMapper.readValue(responseBody, AirportDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return airport;
    }
}
