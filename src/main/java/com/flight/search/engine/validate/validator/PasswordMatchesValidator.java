package com.flight.search.engine.validate.validator;

import com.flight.search.engine.dto.PasswordDTO;
import com.flight.search.engine.dto.UserDTO;
import com.flight.search.engine.validate.annotation.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        if(obj instanceof UserDTO){
            UserDTO user = (UserDTO) obj;
            return user.getPassword().equals(user.getMatchingPassword());
        }
        if(obj instanceof PasswordDTO){
            PasswordDTO passwordDTO = (PasswordDTO) obj;
            return passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword());
        }
        return true;
    }
}
