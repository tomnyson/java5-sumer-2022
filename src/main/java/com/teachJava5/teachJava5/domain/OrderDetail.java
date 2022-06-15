/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teachJava5.teachJava5.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author tomnyson
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetail {

    @EmbeddedId
    OrderDetailKey id;
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    Order order;
    
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    Product product;
    int quantity;
    double price;
    double total;
}
