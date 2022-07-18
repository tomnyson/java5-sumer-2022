/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teachJava5.teachJava5.domain;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author tomnyson
 */
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String customerName;
    @Column(length = 15, nullable = false)
    String phone;
    @Column(length = 200, nullable = false)
    String note;
    double total;
    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;
    int status;
    @OneToMany(mappedBy = "order")
    Set<OrderDetail> orderDetails;
}
