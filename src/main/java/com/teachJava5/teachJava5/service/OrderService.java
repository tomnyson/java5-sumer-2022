/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teachJava5.teachJava5.service;

import com.teachJava5.teachJava5.domain.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public interface OrderService {

    void deleteById(Long id);

    List<Order> findAll();

    Optional<Order> findById(Long id);

    <S extends Order> S save(S entity);
    
}
