/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.service;

import com.teachJava5.teachJava5.domain.PostTag;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public interface PostTagService {

    <S extends PostTag> S save(S entity);

    <S extends PostTag> List<S> saveAllAndFlush(Iterable<S> entities);
    
}
