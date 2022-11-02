package com.flight.search.engine.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String password;
    @Column
    String username;
    @Column
    boolean enabled;

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.REFRESH,
            CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities = new HashSet<>();

    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH,
            CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public User() {
    }

    public User(String password, String username, boolean enabled, Set<Authority> authorities) {
        this.password = password;
        this.username = username;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public User(String password, String username, boolean enabled, Set<Authority> authorities, Cart cart) {
        this.password = password;
        this.username = username;
        this.enabled = enabled;
        this.authorities = authorities;
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> roles) {
        this.authorities = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                ", roles=" + authorities +
//                ", flightsIds=" + cartItems +
                '}';
    }

    public void addAuthority(Authority authority){
        this.authorities.add(authority);
        authority.getUsers().add(this);
    }
    public void removeAuthority(Authority authority){
        this.authorities.remove(authority);
        authority.getUsers().remove(this);
    }



}
