package com.flight.search.engine.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AirportDTO airport = (AirportDTO) obj;
        return code.equals(airport.code) && city.equals(airport.city) && country.equals(airport.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, city, country);
    }
}
