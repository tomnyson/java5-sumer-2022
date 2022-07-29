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
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{username}")
    public ResponseEntity<Account> getDetail(@PathVariable("username") String username,
            HttpServletRequest request, HttpServletResponse response) {
        if (username != null) {
            Optional<Account> detail = accountService.findById(username);
            if (!detail.isEmpty()) {
                return ResponseEntity.ok(detail.get());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO login) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtHelper helper = new JwtHelper();
        Optional<Account> account = accountService.findById(login.getUsername());

        if (account != null) {
            if (passwordEncoder.matches(login.getPassword(), account.get().getPassword())) {
                account.get().setToken(helper.generateToken(account.get()));
                return ResponseEntity.ok(account.get());
            }
        }
        return ResponseEntity.notFound().build();
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
    public ResponseEntity<?> create(@Valid @RequestBody AccountDTO account) {

        if (account != null) {
            Account dto = new Account();
            BeanUtils.copyProperties(account, dto);
            Account create = accountService.save(dto);
            return ResponseEntity.ok(create);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody AccountDTO roleDTO) {
        if (roleDTO != null) {
            Account role = new Account();
            BeanUtils.copyProperties(roleDTO, role);
            Account create = accountService.save(role);
            return ResponseEntity.ok(create);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> remove(@PathVariable("id") String username, HttpServletRequest request, HttpServletResponse response) {
        if (username != null) {
            Optional<Account> detail = accountService.findById(username);

            if (detail != null) {
                accountService.delete(detail.get());
                return ResponseEntity.ok(detail.get());
            }
        }
        return ResponseEntity.noContent().build();
    }

}
