/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.service;

import com.teachJava5.teachJava5.dto.AccountDTO;
import com.teachJava5.teachJava5.dto.AccountDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public class AccountService1 {

    List<AccountDTO> list = new ArrayList<AccountDTO>();

    public void setList(List<AccountDTO> list) {
        this.list = list;
    }

    public AccountService1() {
        list.add(new AccountDTO("admin", "12345", "admin"));
        list.add(new AccountDTO("admin1", "12345", "admin"));
        list.add(new AccountDTO("admin2", "12345", "admin"));
        list.add(new AccountDTO("user", "12345", "user"));
    }

    public List<AccountDTO> getList() {
        return this.list;
    }

    public boolean add(AccountDTO acc) {
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
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public AccountDTO findAccountDTOByUsername(String username) {
        for (AccountDTO AccountDTO : list) {
            if (AccountDTO.getUsername().equals(username)) {
                return AccountDTO;
            }
        }
        return null;
    }
}
