/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.service;

import com.teachJava5.teachJava5.dto.Account;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomnyson
 */
public class AccountService {

    public List<Account> getList() {
        List<Account> list = new ArrayList<Account>();
        list.add(new Account("admin", "12345", "admin"));
        list.add(new Account("admin1", "12345", "admin"));
        list.add(new Account("admin2", "12345", "admin"));
        list.add(new Account("user", "12345", "user"));
        return list;
    }

    public Account findAccountByUsername(String username) {
        List<Account> list = this.getList();
        for (Account account : list) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }
}
