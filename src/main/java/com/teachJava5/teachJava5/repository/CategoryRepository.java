/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.repository;

import com.teachJava5.teachJava5.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tomnyson
 */
// Long
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
