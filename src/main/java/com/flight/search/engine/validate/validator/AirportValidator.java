package com.flight.search.engine.validate.validator;

import com.flight.search.engine.dao.AirportDAO;
import com.flight.search.engine.service.AirportService;
import com.flight.search.engine.validate.annotation.AirportValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class AirportValidator implements ConstraintValidator<AirportValidation, String> {

    private final AirportService airportService;

    public AirportValidator(AirportService airportService) {
        this.airportService = airportService;
    }

    @Override
    public boolean isValid(String airportCode, ConstraintValidatorContext constraintValidatorContext) {
        List<AirportDAO> airports = airportService.findAll();
        ArrayList<String> airportsCodes = new ArrayList<>();
        for(AirportDAO airportTMP  : airports){
            airportsCodes.add(airportTMP.getCode());
        }
        return airportsCodes.contains(airportCode);
    }
}
