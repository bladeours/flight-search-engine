package com.flight.search.engine.service.implementation;

import com.flight.search.engine.dto.AirportDTO;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


class AirportServiceImplTest {

    private static MockWebServer mockBackEnd;
    private AirportServiceImpl airportService;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s",
                mockBackEnd.getPort());
        airportService = new AirportServiceImpl(baseUrl);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void shouldReturnAllAirports() {
        AirportDTO firstAirport = new AirportDTO(
                "AAC",
                "Al Arish",
                "Egypt"
                );
        AirportDTO secondAirport = new AirportDTO(
                "AAE",
                "Annaba",
                "Algeria"
        );

        List<AirportDTO> airports = new ArrayList<>();
        airports.add(firstAirport);
        airports.add(secondAirport);

        String mockData = "[{\"code\":\"AAC\",\"city\":\"Al Arish\",\"country\":\"Egypt\"},{\"code\":\"AAE\",\"city\":\"Annaba\",\"country\":\"Algeria\"}]";
        mockBackEnd.enqueue(new MockResponse()
                .setBody(mockData));

        assertThat(airportService.findAll()).isEqualTo(airports);

    }

    @Test
    void shouldReturnAirportForCode() {
        AirportDTO airport = new AirportDTO(
                "AAC",
                "Al Arish",
                "Egypt"
        );
        String mockData = "{\"code\":\"AAC\",\"city\":\"Al Arish\",\"country\":\"Egypt\"}";
        mockBackEnd.enqueue(new MockResponse()
                .setBody(mockData));
        assertThat(airportService.getAirport("AAC")).isEqualTo(airport);
    }
}