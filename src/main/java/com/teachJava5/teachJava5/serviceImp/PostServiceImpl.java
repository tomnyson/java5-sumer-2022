/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.serviceImp;

import com.teachJava5.teachJava5.domain.Post;
import com.teachJava5.teachJava5.repository.PostRepository;
import com.teachJava5.teachJava5.service.PostService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public <S extends Post> S save(S entity) {
        return postRepository.save(entity);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public void delete(Post entity) {
        postRepository.delete(entity);
    }

    public Post getById(Long id) {
        return postRepository.getById(id);
    }

}
