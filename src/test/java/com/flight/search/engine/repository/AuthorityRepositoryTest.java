package com.flight.search.engine.repository;

import com.flight.search.engine.model.Authority;
import com.flight.search.engine.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthorityRepositoryTest {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    void itShouldFindAuthorityByName() {
        Authority authority = new Authority(
                "ADMIN",
                new HashSet<>()
        );

        authorityRepository.save(authority);

        assertThat(authorityRepository.getByName("ADMIN")).isEqualTo(authority);
    }
}