package com.flight.search.engine.repository;

import com.flight.search.engine.model.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority,Integer > {
}
