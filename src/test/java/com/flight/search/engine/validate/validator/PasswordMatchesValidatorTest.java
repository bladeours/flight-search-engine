package com.flight.search.engine.validate.validator;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
class PasswordMatchesValidatorTest {
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
                        .param("username", "testUser")
                        .param("password", "IFz0#SFK9#3F")
                        .param("matchingPassword", "different_IFz0#SFK9#3F"))
                .andExpect(model().attributeHasErrors("user"))
                .andExpect(model().errorCount(1));
    }
}