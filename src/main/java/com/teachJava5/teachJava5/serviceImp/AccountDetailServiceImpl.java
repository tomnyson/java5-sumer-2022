/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.serviceImp;

import com.teachJava5.teachJava5.domain.Account;
import com.teachJava5.teachJava5.repository.AccountRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public class AccountDetailServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account =  accountRepository.getById(username);
        System.out.println("account"+account.toString());
        return new User(account.getUsername(), account.getPassword(), new ArrayList<>());
    }
    
    
}
