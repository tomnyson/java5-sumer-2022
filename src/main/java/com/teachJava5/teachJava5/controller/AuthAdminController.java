/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.controller;

import com.teachJava5.teachJava5.domain.Account;
import com.teachJava5.teachJava5.dto.AccountDTO;
import com.teachJava5.teachJava5.dto.AccountLoginDTO;
import com.teachJava5.teachJava5.service.AccountService;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import static org.apache.tomcat.jni.User.username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author tomnyson
 */
@Controller
@RequestMapping("/")

public class AuthAdminController {
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private HttpSession session;
    
    @GetMapping("login")
    public String login(Model model) {
        AccountLoginDTO account = new AccountLoginDTO();
        model.addAttribute("account", account);
        return "login"; // Return tên của View, model sẽ tự động pass vào view
    }
    
    @GetMapping("logout")
    public String logout(Model model) {
        AccountLoginDTO account = new AccountLoginDTO();
        if (session.getAttribute("username")!= null) {
            session.removeAttribute("username");
            session.removeAttribute("role");
            return "redirect:/login";
        }
        return "redirect:/login"; // Return tên của View, model sẽ tự động pass vào view
    }
    
    @PostMapping("login")
    public String checkLogin(@Valid @ModelAttribute("account") AccountLoginDTO dto,
            BindingResult result) {
        AccountLoginDTO account = new AccountLoginDTO();
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
//            List<FieldError> errors = result.getFieldErrors();
//            for (FieldError error : errors) {
//                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
//            }
            return "login";
            
        }
        Account check = accountService.checkLogin(dto.getUsername(), dto.getPassword());
        if (check != null) {
            session.setAttribute("username", check.getUsername());
            session.setAttribute("role", check.getRole());
//            if(session.getAttribute("request-url") !=null) {
//                return "redirect:"+session.getAttribute("request-url");
//            }
             return "redirect:admin/user";
        }
        
        return "login"; // Return tên của View, model sẽ tự động pass vào view
    }
}
