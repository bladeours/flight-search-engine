package com.flight.search.engine.service;

import com.flight.search.engine.dto.AirportDTO;

import java.util.List;

public interface AirportService {
    List<AirportDTO> findAll();

    AirportDTO getAirport(String code);

}
