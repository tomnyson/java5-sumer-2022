/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.repository;

import com.teachJava5.teachJava5.domain.Post;
import com.teachJava5.teachJava5.domain.PostTag;
import com.teachJava5.teachJava5.domain.PostTagkey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tomnyson
 */
public interface PostTagRepository extends JpaRepository<PostTag, PostTagkey>{
    
}
