package com.flight.search.engine.dto;

public class AirportDTO {
    private String code;
    private String city;
    private String country;


    public AirportDTO() {
    }

    public AirportDTO(String code, String city, String country) {
        this.code = code;
        this.city = city;
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AirportDTO{" +
                "code='" + code + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
