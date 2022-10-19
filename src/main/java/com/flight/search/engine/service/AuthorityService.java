package com.flight.search.engine.service;

import com.flight.search.engine.model.Authority;

import java.util.HashSet;

public interface AuthorityService {

    Authority getById(int id);

    HashSet<Authority> getAuthoritiesByName(String... names);

    Authority getByName(String name);
}
