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

    public boolean add(Account acc) {
        try {
            // kiểm trra xem đã có username chưa
            for (int i = 0; i <= list.size(); i++) {
                if (list.get(i).getUsername().equals(acc.getUsername())) {
                    list.set(i, acc);

                    return true;
                }
            }
            list.add(acc);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean remove(String username) {
        try {
            for (int i = 0; i <= list.size(); i++) {
                if (list.get(i).getUsername().equals(username)) {
                    //xoá đi
                    list.remove(i);
                    return true;
                }
            }
            return  false;
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
