/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.service;

import com.teachJava5.teachJava5.domain.Product;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public interface CartService {
    public int add(Product p);
    public int remove(int id);
}
