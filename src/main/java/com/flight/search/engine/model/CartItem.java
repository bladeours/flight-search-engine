package com.flight.search.engine.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_from_api")
    private Long idFromApi;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH,
            CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @Column(name = "amount")
    private int amount;

    public CartItem(Long idFromApi, Cart cart, int amount) {
        this.idFromApi = idFromApi;
        this.cart = cart;
        this.amount = amount;
    }

    public CartItem() {
    }

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", idFromApi=" + idFromApi +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return amount == cartItem.amount && Objects.equals(id, cartItem.id) &&
                Objects.equals(idFromApi, cartItem.idFromApi) && Objects.equals(cart, cartItem.cart);
    }

}
