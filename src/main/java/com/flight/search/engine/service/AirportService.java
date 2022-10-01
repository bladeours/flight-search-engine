package com.flight.search.engine.service;

import com.flight.search.engine.dao.AirportDAO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AirportService {
    List<AirportDAO> findAll();
}
