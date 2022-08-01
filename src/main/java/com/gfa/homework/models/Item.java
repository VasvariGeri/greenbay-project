package com.gfa.homework.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private String url;
  private int startingPrice;
  private int purchasePrice;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JsonBackReference
  private User user;

  public Item() {
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(int startingPrice) {
    this.startingPrice = startingPrice;
  }

  public int getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(int purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
