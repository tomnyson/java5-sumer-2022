/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.controller;

import com.teachJava5.teachJava5.domain.Account;
import com.teachJava5.teachJava5.dto.AccountLoginDTO;
import com.teachJava5.teachJava5.dto.Mail;
import com.teachJava5.teachJava5.service.AccountService;
import com.teachJava5.teachJava5.service.MailService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    @Autowired
    private MailService mailService;

    @GetMapping("login")
    public String login(Model model) {
        AccountLoginDTO account = new AccountLoginDTO();
        model.addAttribute("account", account);
        return "login"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("logout")
    public String logout(Model model) {
        AccountLoginDTO account = new AccountLoginDTO();
        if (session.getAttribute("username") != null) {
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
        Account current = accountService.getById(dto.getUsername());
        if (current != null) {
            //check trung password
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean checkPass = passwordEncoder.matches(dto.getPassword(), current.getPassword());
            if (checkPass) {

                session.setAttribute("username", current.getUsername());
                session.setAttribute("role", current.getRole());
            }
            Mail mail = new Mail();
            mail.setTo("tabletkindfire@gmail.com");
            mail.setTo("tabletkindfire@gmail.com");
            mail.setSubject("thông báo");
            Map<String, Object> props = new HashMap<String, Object>();
            props.put("name", "tomnyson");
            mail.setProps(props);
            try {
                mailService.sendEmail(mail);
            } catch (Exception e) {
                e.printStackTrace();
            }

//            if(session.getAttribute("request-url") !=null) {
//                return "redirect:"+session.getAttribute("request-url");
//            }
            return "redirect:admin/user";
        }

        return "login"; // Return tên của View, model sẽ tự động pass vào view
    }
}
