package com.flight.search.engine.service.implementation;

import com.flight.search.engine.dto.UserDTO;
import com.flight.search.engine.exception.UserAlreadyExistsException;
import com.flight.search.engine.model.Authority;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.UserRepository;
import com.flight.search.engine.service.AuthorityService;
import com.flight.search.engine.service.CartService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthorityService authorityService;
    @Mock
    private CartService cartService;
    private UserServiceImpl userService;
    private UserServiceImpl spyUserService;
    @Captor
    ArgumentCaptor<User> userCaptor;
    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, authorityService, cartService);
        spyUserService = Mockito.spy(userService);
    }

    @Test
    void shouldThrowException_WhenUserExits(){
        UserDTO userDTO = new UserDTO(
          "testUser",
          "passwd",
          "passwd"
        );

        when(authorityService.getAuthoritiesByName(any())).thenReturn(new HashSet<>());
        doReturn(true).when(spyUserService).userExists(any());
        assertThrows(UserAlreadyExistsException.class, () -> spyUserService.registerUser(userDTO));
    }

    @Test
    void shouldRegisterUserProperly(){
        UserDTO userDTO = new UserDTO(
                "testUser",
                "passwd",
                "passwd"
        );

        when(authorityService.getAuthoritiesByName(any())).thenReturn(new HashSet<>());
        doReturn(false).when(spyUserService).userExists(any());
        spyUserService.registerUser(userDTO);

        verify(userRepository).save(userCaptor.capture());
        assertThat(userCaptor.getValue().getUsername()).isEqualTo(userDTO.getUsername());
        assertThat(BCrypt.checkpw(userDTO.getPassword(),userCaptor.getValue().getPassword()))
                .isEqualTo(true);

    }
}