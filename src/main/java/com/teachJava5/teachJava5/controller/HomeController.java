/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.controller;

import com.teachJava5.teachJava5.domain.Account;
import com.teachJava5.teachJava5.domain.Role;
import com.teachJava5.teachJava5.dto.AccountDTO;
import com.teachJava5.teachJava5.service.AccountService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author tomnyson
 */
@Controller
@RequestMapping("admin/user")
public class HomeController {

    @Autowired
    AccountService accountService;
    @Autowired
    AccountDTO accountDto;

    @GetMapping("")
    // tự động new đối tượng;
    public String home(Model model) {
        model.addAttribute("message", "hello world 5555");
        List<Account> accounts = accountService.findAll();
        System.err.println(accounts.size());
        model.addAttribute("accounts", accounts);
        return "index"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("create")
    public String create(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "users/create"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("edit/{username}")
    public String edit(Model model, @PathVariable("username") String username) {

        if (username != null) {
            Account accountEdit = accountService.getById(username);
            if (accountEdit != null) {
                AccountDTO dto = new AccountDTO();
                BeanUtils.copyProperties(accountEdit, dto);
                System.err.println("accountEdit" + accountEdit.getUsername());
                model.addAttribute("account", dto);
                return "/users/edit";
            }

        }
        return "redirect:/user";
    }

    @PostMapping("create")
    public String createUser(Model model,
            @Valid @ModelAttribute("account") AccountDTO dto,
            BindingResult result) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            return "users/create";

        }
        Role role = new Role();
        Account copy = new Account();
        role.setRoleId(dto.getRole());
        copy.setRole(role);
        BeanUtils.copyProperties(dto, copy);
        accountService.save(copy);
        return "redirect:/admin/user"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("user/search")
    @ResponseBody
    public String search(@RequestParam("keyword") Optional<String> keyword) {
        return "index"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("user/redirect")
    public String redirect(RedirectAttributes params) {
        params.addAttribute("message", "rediect url");
        return "redirect:/admin/user";
    }

    // get request has path
    @GetMapping("user/{username}")
    public String detail(Model model,
            @PathVariable("username") String username) {

        if (username != null) {
            Account detail = accountService.getById(username);
            model.addAttribute("account", detail);
            return "users/detail";
        }
        return "redirect:users"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("user/delete/{username}")
    public String delete(
            @PathVariable("username") String username) {
        if (username != null) {
            Optional<Account> detail = accountService.findById(username);
        }
        return "redirect:/user"; // Return tên của View, model sẽ tự động pass vào view
    }

}
