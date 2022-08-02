package com.gfa.homework.dtos;

public class BidDTO {

    private Integer price;
    private String username;

    public BidDTO() {
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
