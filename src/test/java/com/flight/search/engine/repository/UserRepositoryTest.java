package com.flight.search.engine.repository;

import com.flight.search.engine.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void itShouldFindUserByName() {
        User user = new User(
          "passwd",
          "testUser",
          true,
          new HashSet<>()
        );
        userRepository.save(user);

        assertThat(userRepository.getUserByUsername("testUser")).isEqualTo(user);
    }
}