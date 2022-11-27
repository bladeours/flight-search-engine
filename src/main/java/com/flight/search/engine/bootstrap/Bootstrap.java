package com.flight.search.engine.bootstrap;

import com.flight.search.engine.dto.UserDTO;
import com.flight.search.engine.model.Authority;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.AuthorityRepository;
import com.flight.search.engine.service.AuthorityService;
import com.flight.search.engine.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;

@Configuration
public class Bootstrap {
    private final UserService userService;
    private final AuthorityService authorityService;

    public Bootstrap(UserService userService, AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @Bean
    public CommandLineRunner run(){
        return args -> {
            if(userService.getUserByUsername("admin") == null){
                User user = userService.registerUser(new UserDTO(
                        "admin",
                        "admin",
                        "admin"
                ));
                userService.addAuthorityToUser(user, authorityService.getByName("ADMIN"));
            }

        };
    }
}
