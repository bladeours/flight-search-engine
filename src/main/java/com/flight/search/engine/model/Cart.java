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
    private Set<CartItem> cartItems;

    @OneToOne(mappedBy = "cart", cascade = { CascadeType.ALL})
    private User user;

    public Cart() {
    }

    public Cart(Set<CartItem> cartItems, User user) {
        this.cartItems = cartItems;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
