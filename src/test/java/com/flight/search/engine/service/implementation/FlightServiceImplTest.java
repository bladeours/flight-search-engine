package com.flight.search.engine.service.implementation;

import com.flight.search.engine.dto.AirportDTO;
import com.flight.search.engine.dto.CompanyDTO;
import com.flight.search.engine.dto.FlightDTO;
import com.flight.search.engine.property.ApiProperty;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class FlightServiceImplTest {
    private static MockWebServer mockBackEnd;
    private FlightServiceImpl flightService;
    private ApiProperty apiProperty;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }
    @BeforeEach
    void initialize() {

        String baseUrl = String.format("http://localhost:%s",
                mockBackEnd.getPort());
        apiProperty = new ApiProperty(baseUrl);
        flightService = new FlightServiceImpl(apiProperty);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void shouldReturnFlightsForCodes(){
        AirportDTO departureAirport = new AirportDTO(
                "WDH",
                "Windhoek - Hosea Kutako Int`l",
                "Namibia"
        );
        AirportDTO arrivalAirport = new AirportDTO(
                "RKS",
                "Rock Springs, WY",
                "USA"
        );
        CompanyDTO company1 = new CompanyDTO(63L, "AirTran Airways");
        CompanyDTO company2 = new CompanyDTO(68L, "Air Canada");
        FlightDTO flight1 = new FlightDTO(
                126L,
                departureAirport,
                arrivalAirport,
                new Timestamp(1671269860000L),
                new Time((10800 + 900 + 19 - 3600) * 1000),
                311,
                92,
                193.07,
                company1,
                9853
        );
        FlightDTO flight2 = new FlightDTO(
                127L,
                departureAirport,
                arrivalAirport,
                new Timestamp(1671269860000L),
                new Time((10800 + 900 + 19 - 3600) * 1000),
                311,
                92,
                193.07,
                company2,
                9853
        );

        List<FlightDTO> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);
        for(FlightDTO flight : flights){
            flight.setArrivalDate(new Timestamp(0));
            flight.getArrivalDate().setTime(flight.getDepartureDate().getTime() + flight.getFlightTime().getTime());
        }
        String mockData ="[{\"id\":126,\"departureAirport\":{\"code\":\"WDH\",\"city\":\"Windhoek - Hosea Kutako Int`l\",\"country\":\"Namibia\"},\"arrivalAirport\":{\"code\":\"RKS\",\"city\":\"Rock Springs, WY\",\"country\":\"USA\"},\"departureDate\":1671269860000,\"flightTime\":\"03:15:19\",\"allSeats\":311,\"freeSeats\":92,\"price\":193.07,\"company\":{\"id\":63,\"name\":\"AirTran Airways\"},\"distance_km\":9853},{\"id\":127,\"departureAirport\":{\"code\":\"WDH\",\"city\":\"Windhoek - Hosea Kutako Int`l\",\"country\":\"Namibia\"},\"arrivalAirport\":{\"code\":\"RKS\",\"city\":\"Rock Springs, WY\",\"country\":\"USA\"},\"departureDate\":1671269860000,\"flightTime\":\"03:15:19\",\"allSeats\":311,\"freeSeats\":92,\"price\":193.07,\"company\":{\"id\":68,\"name\":\"Air Canada\"},\"distance_km\":9853}]";
        mockBackEnd.enqueue(new MockResponse()
                .setBody(mockData));
        assertThat(flightService.getFlightsForCodes("WDH","RKS", "2022-12-17")).isEqualTo(flights);
    }

    @Test
    void shouldReturnProperFormatDate(){
        assertThat(flightService.getDateToShow("2022-10-05")).isEqualTo("Wednesday 5 October, 2022");
    }

    @Test
    void shouldThrowException_WhenBadDateFormat(){
        assertThrows(DateTimeParseException.class, () -> flightService.getDateToShow("2022-13-05"));
    }




}