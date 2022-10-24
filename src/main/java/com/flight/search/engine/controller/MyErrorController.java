package com.flight.search.engine.controller;

import com.flight.search.engine.service.ErrorService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    private final ErrorService errorService;

    public MyErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @GetMapping ( "/error")
    public String showErrorPage(HttpServletRequest request, Model model){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        model.addAttribute("errorMessage",errorService.getErrorMessage((int)status));

        return "error";

    }

}
