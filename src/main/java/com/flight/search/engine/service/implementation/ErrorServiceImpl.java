package com.flight.search.engine.service.implementation;

import com.flight.search.engine.service.ErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public String getErrorMessage(int statusCode) {

        return switch (statusCode) {
            case 404 -> "404 Can't find this page.";
            case 500 -> "500 Sorry, my bad :/";
            default -> statusCode + "Unknown error";
        };
    }
}
