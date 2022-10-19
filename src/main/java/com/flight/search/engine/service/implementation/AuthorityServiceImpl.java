package com.flight.search.engine.service.implementation;

import com.flight.search.engine.model.Authority;
import com.flight.search.engine.repository.AuthorityRepository;
import com.flight.search.engine.service.AuthorityService;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;


    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority getById(int id) {
        return authorityRepository.findById(id).get();
    }

    @Override
    public HashSet<Authority> getAuthoritiesByName(String... names) {
        HashSet<Authority> authorities = new HashSet<>();
        for(String name: names){
            authorities.add(getByName(name));
        }
        return authorities;
    }

    @Override
    public Authority getByName(String name) {
        return authorityRepository.getByName(name);
    }
}
