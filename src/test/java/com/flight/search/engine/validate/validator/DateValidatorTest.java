package com.flight.search.engine.validate.validator;

import com.flight.search.engine.controller.HomeController;
import com.flight.search.engine.service.AirportService;
import com.flight.search.engine.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(HomeController.class)
class DateValidatorTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AirportService airportService;
    @MockBean
    private FlightService flightService;

    @Test
    void shouldShowsError_WhenDateIsNotCorrect() throws Exception {
        when(airportService.findAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/search")
                        .param("departureAirportCode", "test")
                        .param("arrivalAirportCode", "test")
                        .param("departureDate", "2022-13-32"))
                .andExpect(model().attributeHasFieldErrorCode("flightForm", "departureDate", "DateValidation"));
    }
}