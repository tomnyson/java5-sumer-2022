/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.controller;

import com.teachJava5.teachJava5.dto.Account;
import java.util.Optional;
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
@RequestMapping("admin")
public class HomeController {
    @GetMapping("/user")
    public String home(Model model) {
        Account dto = new Account();
        dto.setUsername("admin");
        dto.setPassword("123456");
        dto.setRole("admin");
        model.addAttribute("account", dto);
        model.addAttribute("message", "hello world 5555");
        return "index";  // Return tên của View, model sẽ tự động pass vào view
    }
    
    @GetMapping("user/search")
    @ResponseBody
     public String search(@RequestParam("keyword") Optional<String> keyword) {
        return "index";  // Return tên của View, model sẽ tự động pass vào view
    }
     
   @GetMapping("user/redirect")
   public String redirect( RedirectAttributes params) {
       params.addAttribute("message", "rediect url");
       return "redirect:/admin/user"; 
   }
   
   // get request has path
    @GetMapping("user/{id}")
     public String detail(@PathVariable("id") int id) {
         System.out.println("id: "+id);
        return "index";  // Return tên của View, model sẽ tự động pass vào view
    }
     
}
