/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teachJava5.teachJava5.controller.api;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author tomnyson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements  Serializable{
    private int id;
    private String name;
    private double  price;
}
