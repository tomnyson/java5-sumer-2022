/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.config;

import com.teachJava5.teachJava5.dto.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author tomnyson
 */
@Configuration
public class BeanConfig {
    @Bean
    public Account getAccount() {
        Account acount = new Account();
        acount.setUsername("admin");
         acount.setPassword("123456");
        acount.setRole("admin");
        return acount;
    }
}
