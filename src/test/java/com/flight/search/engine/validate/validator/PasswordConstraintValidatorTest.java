package com.flight.search.engine.validate.validator;

import com.flight.search.engine.controller.HomeController;
import com.flight.search.engine.controller.UserController;
import com.flight.search.engine.repository.UserRepository;
import com.flight.search.engine.service.CartService;
import com.flight.search.engine.service.FlightService;
import com.flight.search.engine.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
class PasswordConstraintValidatorTest {
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private FlightService flightService;
    @MockBean
    private CartService cartService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldShowError_WhenPasswordIsTooWeak() throws Exception {
        mockMvc.perform(post("/register")
                        .param("username", "test")
                        .param("password", "weak_password")
                        .param("matchingPassword", "weak_password"))
                .andExpect(model().attributeHasFieldErrorCode("user", "password", "ValidPassword"));
    }

}