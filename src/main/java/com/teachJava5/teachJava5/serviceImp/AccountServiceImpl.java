/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.serviceImp;

import com.teachJava5.teachJava5.domain.Account;
import com.teachJava5.teachJava5.repository.AccountRepository;
import com.teachJava5.teachJava5.service.AccountService;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public List<Account> findAll(Sort sort) {
        return accountRepository.findAll(sort);
    }

    public List<Account> findAllById(Iterable<String> ids) {
        return accountRepository.findAllById(ids);
    }

    public <S extends Account> List<S> saveAll(Iterable<S> entities) {
        return accountRepository.saveAll(entities);
    }

    public void flush() {
        accountRepository.flush();
    }

    public <S extends Account> S saveAndFlush(S entity) {
        return accountRepository.saveAndFlush(entity);
    }

    public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
        return accountRepository.saveAllAndFlush(entities);
    }

    public void deleteInBatch(Iterable<Account> entities) {
        accountRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Account> entities) {
        accountRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<String> ids) {
        accountRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        accountRepository.deleteAllInBatch();
    }

    public Account getOne(String id) {
        return accountRepository.getOne(id);
    }

    public Account getById(String id) {
        return accountRepository.getById(id);
    }

    public <S extends Account> List<S> findAll(Example<S> example) {
        return accountRepository.findAll(example);
    }

    public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
        return accountRepository.findAll(example, sort);
    }

    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public <S extends Account> S save(S entity) {
        return accountRepository.save(entity);
    }

    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }

    public boolean existsById(String id) {
        return accountRepository.existsById(id);
    }

    public long count() {
        return accountRepository.count();
    }

    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }

    public void delete(Account entity) {
        accountRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends String> ids) {
        accountRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends Account> entities) {
        accountRepository.deleteAll(entities);
    }

    public void deleteAll() {
        accountRepository.deleteAll();
    }

    public <S extends Account> Optional<S> findOne(Example<S> example) {
        return accountRepository.findOne(example);
    }

    public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
        return accountRepository.findAll(example, pageable);
    }

    public <S extends Account> long count(Example<S> example) {
        return accountRepository.count(example);
    }

    public <S extends Account> boolean exists(Example<S> example) {
        return accountRepository.exists(example);
    }

    public <S extends Account, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return accountRepository.findBy(example, queryFunction);
    }
    
    
}
