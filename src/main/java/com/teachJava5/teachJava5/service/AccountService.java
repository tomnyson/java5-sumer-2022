/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.service;

import com.teachJava5.teachJava5.dto.Account;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public class AccountService {

    List<Account> list = new ArrayList<Account>();

    public void setList(List<Account> list) {
        this.list = list;
    }

    public AccountService() {
        list.add(new Account("admin", "12345", "admin"));
        list.add(new Account("admin1", "12345", "admin"));
        list.add(new Account("admin2", "12345", "admin"));
        list.add(new Account("user", "12345", "user"));
    }

    public List<Account> getList() {
        return this.list;
    }

    public boolean add(Account account) {
        try {
            list.add(account);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Account findAccountByUsername(String username) {
        for (Account account : list) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }
}
