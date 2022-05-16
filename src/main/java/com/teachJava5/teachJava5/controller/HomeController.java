/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.controller;

import com.teachJava5.teachJava5.dto.Account;
import com.teachJava5.teachJava5.service.AccountService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author tomnyson
 */
@Controller
@RequestMapping("")
public class HomeController {

//    @Autowired
    @GetMapping("user")
    // tự động new đối tượng;

    public String home(Model model) {
        Account dto = new Account();
        dto.setUsername("admin");
        dto.setPassword("123456");
        dto.setRole("creator");
        model.addAttribute("account", dto);
        model.addAttribute("message", "hello world 5555");
        AccountService service = new AccountService();
        List<Account> accounts = service.getList();
        System.err.println(accounts.size());
        model.addAttribute("accounts", accounts);
        return "index";  // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("user/create")
    public String create () {
        return "users/create";  // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("user/search")
    @ResponseBody
    public String search(@RequestParam("keyword") Optional<String> keyword
    ) {
        return "index";  // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("user/redirect")
    public String redirect(RedirectAttributes params
    ) {
        params.addAttribute("message", "rediect url");
        return "redirect:/admin/user";
    }

    // get request has path
    @GetMapping("user/{username}")
    public String detail(Model model,
            @PathVariable("username") String username
    ) {

        AccountService service = new AccountService();
        if (username != null) {
            Account detail = service.findAccountByUsername(username);
            model.addAttribute("account", detail);
            return "users/detail";
        }
        return "redirect:users";  // Return tên của View, model sẽ tự động pass vào view
    }

}
