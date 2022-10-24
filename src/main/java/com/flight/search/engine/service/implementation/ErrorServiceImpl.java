package com.flight.search.engine.service.implementation;

import com.flight.search.engine.service.ErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public String getErrorMessage(int statusCode) {

        switch (statusCode){
            case 404:
                return "404 Can't find this page.";
            case 500:
                return "500 Sorry, my bad :/";
        }
        return statusCode + "Unknown error";
    }
}
