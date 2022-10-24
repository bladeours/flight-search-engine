package com.flight.search.engine.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = { CascadeType.ALL})
    private Set<CartItem> CartItems;

    @OneToOne(mappedBy = "cart", cascade = { CascadeType.ALL})
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CartItem> getCartItems() {
        return CartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        CartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
