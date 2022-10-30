package com.flight.search.engine.repository;

import com.flight.search.engine.model.Cart;
import com.flight.search.engine.model.CartItem;
import com.flight.search.engine.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    @Query("select c from CartItem c where c.idFromApi = :flightId and c.cart= :cartId")
    CartItem findByFlightIdAndCart(@Param("flightId")long flightId, @Param("cartId") Cart cart);

}
