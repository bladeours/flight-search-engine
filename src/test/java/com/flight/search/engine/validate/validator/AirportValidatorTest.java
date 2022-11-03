package com.flight.search.engine.validate.validator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight.search.engine.controller.HomeController;
import com.flight.search.engine.dto.AirportDTO;
import com.flight.search.engine.dto.CompanyDTO;
import com.flight.search.engine.dto.FlightDTO;
import com.flight.search.engine.dto.FlightFormDTO;
import com.flight.search.engine.service.AirportService;
import com.flight.search.engine.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(HomeController.class)
class AirportValidatorTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AirportService airportService;
    @MockBean
    private FlightService flightService;

    @Test
    void shouldShowsError_WhenAirportIsNotCorrect() throws Exception {
        when(airportService.findAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/search")
                    .param("departureAirportCode", "test")
                    .param("arrivalAirportCode", "test"))
                .andExpect(model().attributeHasFieldErrorCode("flightForm", "arrivalAirportCode", "AirportValidation"))
                .andExpect(model().attributeHasFieldErrorCode("flightForm", "departureAirportCode", "AirportValidation"));
    }



}