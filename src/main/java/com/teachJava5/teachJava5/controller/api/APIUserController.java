/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teachJava5.teachJava5.controller.api;

import com.teachJava5.teachJava5.domain.Account;
import com.teachJava5.teachJava5.dto.AccountDTO;
import com.teachJava5.teachJava5.dto.LoginDTO;
import com.teachJava5.teachJava5.service.AccountService;
import com.teachJava5.teachJava5.utils.JwtHelper;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tomnyson
 */
@RestController
@RequestMapping("api/user")
public class APIUserController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("")
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

//    public List<Account> getList() {
//        return accountService.findAll();
//    }
    @GetMapping("/{username}")
    public Account getDetail(@PathVariable("username") String username,
            HttpServletRequest request, HttpServletResponse response) {
        if (username != null) {
            Optional<Account> detail = accountService.findById(username);
            if (!detail.isEmpty()) {
                return detail.get();
            }
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

    @PostMapping("/login")
    public Account login(@Valid @RequestBody LoginDTO login) {
        System.out.println(login.getUsername());
        System.out.println(login.getPassword());
        try {
        authenticationManager.authenticate(new 
        UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (Exception e) {
            System.out.println("Still eror");
            e.printStackTrace();
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtHelper helper = new JwtHelper();
        Optional<Account> account = accountService.findById(login.getUsername());
        
        if (account != null) {
            if (passwordEncoder.matches(login.getPassword(), account.get().getPassword())) {
                System.out.println("token" + helper.generateToken(account.get()));
                account.get().setToken(helper.generateToken(account.get()));
                return account.get();
            }
        }
        return null;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("Go here");
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @PostMapping("")
    public Account create(@Valid @RequestBody AccountDTO account) {
        
        if(account != null) {
              Account dto = new Account();
              BeanUtils.copyProperties(account, dto);
            Account create = accountService.save(dto);
            return create;
        }
        return  null;
    }
//    
//    @PutMapping("/{id}")
//    public Role update(@Valid @RequestBody RoleDTO roleDTO) {
//        if(roleDTO != null) {
//              Role role = new Role();
//              BeanUtils.copyProperties(roleDTO, role);
//            Role create = roleService.save(role);
//            return create;
//        }
//        return  null;
//    }
//    
//    @DeleteMapping("/{id}")
//    public Optional<Role> remove(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
//        if(id != null) {
//            Optional<Role> detail = roleService.findById(id);
//            
//            if(detail != null) {
//              roleService.delete(detail.get());
//                return detail;
//            }
//            response.setStatus( HttpServletResponse.SC_BAD_REQUEST  );
//        }
//        return  null;
//    }

    }
