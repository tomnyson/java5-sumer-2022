/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.dto;

/**
 *
 * @author tomnyson
 */
public class CartDTO {
    private Long productId;
    private Double price;
    private String name;
    private int quantity;
    private String image;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CartDTO(Long productId, Double price, String name, int quantity, String image) {
        this.productId = productId;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
        this.image = image;
    }

    public CartDTO() {
    }
    
}
