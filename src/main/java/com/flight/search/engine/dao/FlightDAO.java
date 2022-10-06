package com.flight.search.engine.dao;

import java.sql.Time;
import java.sql.Timestamp;

public class FlightDAO {
    private Long id;
    private AirportDAO departureAirport;
    private AirportDAO arrivalAirport;
    private Timestamp departureDate;
    private Timestamp arrivalDate;
    private Time flightTime;
    private int allSeats;
    private int freeSeats;
    private double price;
    private CompanyDAO company;
    private int distance_km;

    public FlightDAO() {
    }

    public FlightDAO(Long id, AirportDAO departureAirport, AirportDAO arrivalAirport, Timestamp departureDate, Time flightTime, int allSeats,
                     int freeSeats, double price, CompanyDAO company, int distance_km) {
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.flightTime = flightTime;
        this.allSeats = allSeats;
        this.freeSeats = freeSeats;
        this.price = price;
        this.company = company;
        this.distance_km = distance_km;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public Time getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Time flightTime) {
        this.flightTime = flightTime;
    }

    public int getAllSeats() {
        return allSeats;
    }

    public void setAllSeats(int allSeats) {
        this.allSeats = allSeats;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CompanyDAO getCompany() {
        return company;
    }

    public void setCompany(CompanyDAO company) {
        this.company = company;
    }

    public int getDistance_km() {
        return distance_km;
    }

    public void setDistance_km(int distance_km) {
        this.distance_km = distance_km;
    }

    public AirportDAO getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(AirportDAO departureAirport) {
        this.departureAirport = departureAirport;
    }

    public AirportDAO getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(AirportDAO arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
