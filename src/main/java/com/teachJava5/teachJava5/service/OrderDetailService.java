/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.teachJava5.teachJava5.service;

import com.teachJava5.teachJava5.domain.Order;
import com.teachJava5.teachJava5.domain.OrderDetail;
import com.teachJava5.teachJava5.domain.OrderDetailKey;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public interface OrderDetailService {

    void deleteById(OrderDetailKey id);

    List<OrderDetail> findAll();

    Optional<OrderDetail> findById(OrderDetailKey id);

    <S extends OrderDetail> S save(S entity);

   
}
