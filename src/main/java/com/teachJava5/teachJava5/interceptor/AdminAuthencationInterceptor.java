/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author tomnyson
 */
@Component
public class AdminAuthencationInterceptor implements HandlerInterceptor{
    @Autowired
    private HttpSession session;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse 
            response, Object handler) throws Exception {
        System.out.println("preHandle"+ request.getRequestURI());
       if(session.getAttribute("username") != null) {
           return true;
       }
       // trả về lại trang cũ khi login thành công
       session.setAttribute("request-url", request.getRequestURI());
       response.sendRedirect("/login");
       return false;
    }
    
    
}
