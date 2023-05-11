package com.serbatic.facturas.accessingData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idArt;

  private String name;

  private String category;

  private int stock;
  
  private double price;

  // Getters and Setters
  public Long getId() {
    return idArt;
  }

  public void setId(Long id) {
    this.idArt = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


}
