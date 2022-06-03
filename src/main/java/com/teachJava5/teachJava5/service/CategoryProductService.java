/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.service;

import com.teachJava5.teachJava5.domain.Category;
import com.teachJava5.teachJava5.domain.CategoryProduct;
import com.teachJava5.teachJava5.domain.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public interface CategoryProductService {

    void delete(CategoryProduct entity);

    List<CategoryProduct> findAll();

    Optional<CategoryProduct> findById(Long id);

    <S extends CategoryProduct> S save(S entity);
    
}
