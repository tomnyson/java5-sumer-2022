/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.service;

import com.teachJava5.teachJava5.domain.Category;
import com.teachJava5.teachJava5.domain.CategoryProduct;
import com.teachJava5.teachJava5.domain.Product;
import com.teachJava5.teachJava5.domain.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public interface ProductService {

    void delete(Product entity);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    <S extends Product> S save(S entity);

    
}
