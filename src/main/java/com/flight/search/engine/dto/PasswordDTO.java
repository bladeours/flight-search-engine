package com.flight.search.engine.dto;

import com.flight.search.engine.validate.annotation.PasswordMatches;
import com.flight.search.engine.validate.annotation.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class PasswordDTO {
    @NotNull
    @NotEmpty(message = "current password can't be empty")
    private String oldPassword;
    @ValidPassword
    @NotNull
    @NotEmpty(message = "new password can't be empty")
    private String newPassword;
    @NotNull
    @NotEmpty(message = "confirm password can't be empty")
    private String confirmPassword;

    public PasswordDTO() {
    }

    public PasswordDTO(String oldPassword, String newPassword, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
