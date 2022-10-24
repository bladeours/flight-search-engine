package com.flight.search.engine.model;

import javax.persistence.*;


@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_from_api")
    private Long idFromApi;

    @ManyToOne(cascade = { CascadeType.ALL})
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getIdFromApi() {
        return idFromApi;
    }

    public void setIdFromApi(Long idFromApi) {
        this.idFromApi = idFromApi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FlightMini{" +
                "id=" + id +
                ", idFromApi=" + idFromApi +
                '}';
    }


}
