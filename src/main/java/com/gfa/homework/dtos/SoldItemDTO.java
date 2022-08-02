package com.gfa.homework.dtos;

public class SoldItemDTO extends ItemDTO {

  private String buyer;

  public SoldItemDTO() {
    super();
  }

  public String getBuyer() {
    return buyer;
  }

  public void setBuyer(String buyer) {
    this.buyer = buyer;
  }
}
