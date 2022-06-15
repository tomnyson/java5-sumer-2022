/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teachJava5.teachJava5.serviceImp;

import com.teachJava5.teachJava5.domain.OrderDetail;
import com.teachJava5.teachJava5.domain.OrderDetailKey;
import com.teachJava5.teachJava5.repository.OrderDetailRepository;
import com.teachJava5.teachJava5.service.OrderDetailService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public class OrderDetailImpl implements OrderDetailService{
     @Autowired
     OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    public <S extends OrderDetail> S save(S entity) {
        return orderDetailRepository.save(entity);
    }

    public Optional<OrderDetail> findById(OrderDetailKey id) {
        return orderDetailRepository.findById(id);
    }

    public void deleteById(OrderDetailKey id) {
        orderDetailRepository.deleteById(id);
    }
     
}
