package com.flight.search.engine.dao;

import java.sql.Timestamp;

public class FlightFormDAO {

    private String departureAirportCode;
    private String arrivalAirportCode;
    private String departureDate;
    private String returnDate;

    public FlightFormDAO(String departureAirportCode, String arrivalAirportCode, String departureDate, String returnDate) {
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    public FlightFormDAO() {
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
        return "FlightFormDAO{" +
                "departureAirportCode='" + departureAirportCode + '\'' +
                ", arrivalAirportCode='" + arrivalAirportCode + '\'' +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
