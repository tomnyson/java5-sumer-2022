/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.service;

import com.teachJava5.teachJava5.domain.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public interface AccountService {

    long count();

    <S extends com.teachJava5.teachJava5.domain.Account> long count(Example<S> example);

    void delete(com.teachJava5.teachJava5.domain.Account entity);

    void deleteAll();

    void deleteAll(Iterable<? extends com.teachJava5.teachJava5.domain.Account> entities);

    void deleteAllById(Iterable<? extends String> ids);

    void deleteAllByIdInBatch(Iterable<String> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<com.teachJava5.teachJava5.domain.Account> entities);

    void deleteById(String id);

    void deleteInBatch(Iterable<com.teachJava5.teachJava5.domain.Account> entities);

    <S extends com.teachJava5.teachJava5.domain.Account> boolean exists(Example<S> example);

    boolean existsById(String id);

    List<com.teachJava5.teachJava5.domain.Account> findAll();

    <S extends com.teachJava5.teachJava5.domain.Account> List<S> findAll(Example<S> example);

    <S extends com.teachJava5.teachJava5.domain.Account> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends com.teachJava5.teachJava5.domain.Account> List<S> findAll(Example<S> example, Sort sort);

    Page<com.teachJava5.teachJava5.domain.Account> findAll(Pageable pageable);

    List<com.teachJava5.teachJava5.domain.Account> findAll(Sort sort);

    List<com.teachJava5.teachJava5.domain.Account> findAllById(Iterable<String> ids);

    <S extends com.teachJava5.teachJava5.domain.Account, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    Optional<com.teachJava5.teachJava5.domain.Account> findById(String id);

    <S extends com.teachJava5.teachJava5.domain.Account> Optional<S> findOne(Example<S> example);

    void flush();

    com.teachJava5.teachJava5.domain.Account getById(String id);

    com.teachJava5.teachJava5.domain.Account getOne(String id);

    <S extends com.teachJava5.teachJava5.domain.Account> S save(S entity);

    <S extends com.teachJava5.teachJava5.domain.Account> List<S> saveAll(Iterable<S> entities);

    <S extends com.teachJava5.teachJava5.domain.Account> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends com.teachJava5.teachJava5.domain.Account> S saveAndFlush(S entity);

    Account checkLogin(String username, String password);
    
    
}
