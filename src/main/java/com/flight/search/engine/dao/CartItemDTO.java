package com.flight.search.engine.dao;

import java.sql.Time;
import java.sql.Timestamp;

public class CartItemDTO {
    private Long idFromCart;
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

    public CartItemDTO() {
    }

    public Long getIdFromCart() {
        return idFromCart;
    }

    public void setIdFromCart(Long idFromCart) {
        this.idFromCart = idFromCart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
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
}
