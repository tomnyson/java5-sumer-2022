/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.repository;

import com.teachJava5.teachJava5.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author tomnyson
 */
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT u FROM Account u WHERE u.username = :username and u.password = :password")
    public Account checkLogin(@Param("username") String username, @Param("password") String password);
}
