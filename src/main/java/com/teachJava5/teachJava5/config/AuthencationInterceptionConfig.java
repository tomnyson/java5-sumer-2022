/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.config;

import com.teachJava5.teachJava5.interceptor.AdminAuthencationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author tomnyson
 */
@Configuration
public class AuthencationInterceptionConfig implements WebMvcConfigurer{
    @Autowired
    private AdminAuthencationInterceptor adminAuthencationInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(adminAuthencationInterceptor)
               .addPathPatterns("/admin/**");
    }
    
}
