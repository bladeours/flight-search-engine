package com.flight.search.engine.validate.validator;

import com.flight.search.engine.validate.annotation.DateValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateValidation, String> {

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        if(date == null) return false;
        if(date.equals("")) return true;
        return date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
    }
}
