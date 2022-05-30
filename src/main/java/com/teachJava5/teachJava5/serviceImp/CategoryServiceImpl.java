/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.serviceImp;

import com.teachJava5.teachJava5.domain.Category;
import com.teachJava5.teachJava5.repository.CategoryRepository;
import com.teachJava5.teachJava5.service.CategoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public <S extends Category> S save(S entity) {
        return categoryRepository.save(entity);
    }

    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    public void delete(Category entity) {
        categoryRepository.delete(entity);
    }
    
}
