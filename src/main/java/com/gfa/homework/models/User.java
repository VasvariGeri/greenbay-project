package com.gfa.homework.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 30)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean isActive;
    private String roles;
    private int greenBayDollar;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Bid> placedBids;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private List<Item> items;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getGreenBayDollar() {
        return greenBayDollar;
    }

    public void setGreenBayDollar(int greenBayDollar) {
        this.greenBayDollar = greenBayDollar;
    }

    public List<Bid> getPlacedBids() {
        return placedBids;
    }

    public void setPlacedBids(List<Bid> placedBids) {
        this.placedBids = placedBids;
    }
}
