package com.flight.search.engine.repository;

import com.flight.search.engine.model.Authority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority,Integer > {
    @Query("SELECT a FROM Authority a WHERE a.name = :name")
    Authority getByName(String name);
}
