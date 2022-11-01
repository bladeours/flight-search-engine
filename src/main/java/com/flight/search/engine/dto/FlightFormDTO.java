package com.flight.search.engine.dto;

import com.flight.search.engine.validate.annotation.AirportValidation;
import com.flight.search.engine.validate.annotation.DateValidation;

import javax.validation.constraints.NotEmpty;

public class FlightFormDTO {

    @AirportValidation
    @NotEmpty(message = "Airport can not be empty")
    private String departureAirportCode;
    @AirportValidation
    @NotEmpty(message = "Airport can not be empty")
    private String arrivalAirportCode;
    @DateValidation
    @NotEmpty(message = "Departure date can not be empty")
    private String departureDate;
    private String returnDate;

    public FlightFormDTO(String departureAirportCode, String arrivalAirportCode, String departureDate, String returnDate) {
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    public FlightFormDTO() {
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "FlightFormDTO{" +
                "departureAirportCode='" + departureAirportCode + '\'' +
                ", arrivalAirportCode='" + arrivalAirportCode + '\'' +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
