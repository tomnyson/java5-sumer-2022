/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.serviceImp;

import com.teachJava5.teachJava5.domain.CategoryProduct;
import com.teachJava5.teachJava5.repository.CategoryProductRepository;
import com.teachJava5.teachJava5.service.CategoryProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public class CategoryProductServiceImpl implements CategoryProductService{
    @Autowired
    CategoryProductRepository categoryProductRepository;
    
    public List<CategoryProduct> findAll() {
        return categoryProductRepository.findAll();
    }

    public <S extends CategoryProduct> S save(S entity) {
        return categoryProductRepository.save(entity);
    }

    public Optional<CategoryProduct> findById(Long id) {
        return categoryProductRepository.findById(id);
    }

    public void delete(CategoryProduct entity) {
        categoryProductRepository.delete(entity);
    }
    
}
