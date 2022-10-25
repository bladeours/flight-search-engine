package com.flight.search.engine.dao;

import com.flight.search.engine.validate.annotation.PasswordMatches;
import com.flight.search.engine.validate.annotation.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class UserDAO {
    @NotNull
    @NotEmpty(message = "can't be empty")
    private String username;

//    @ValidPassword
    @NotNull
    @NotEmpty(message = "can't be empty")
    private String password;

    @NotNull
    @NotEmpty(message = "can't be empty")
    private String matchingPassword;

    public UserDAO() {
    }

    public UserDAO(String username, String password, String matchingPassword) {
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
