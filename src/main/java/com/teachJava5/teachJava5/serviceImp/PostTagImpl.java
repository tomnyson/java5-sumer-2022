/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.serviceImp;

import com.teachJava5.teachJava5.domain.PostTag;
import com.teachJava5.teachJava5.repository.PostTagRepository;
import com.teachJava5.teachJava5.service.PostTagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public class PostTagImpl implements PostTagService{
    @Autowired
    PostTagRepository postTagRepository;

    public <S extends PostTag> List<S> saveAllAndFlush(Iterable<S> entities) {
        return postTagRepository.saveAllAndFlush(entities);
    }

    public <S extends PostTag> S save(S entity) {
        return postTagRepository.save(entity);
    }
    
    
}
