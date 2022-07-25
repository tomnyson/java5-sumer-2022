/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teachJava5.teachJava5.controller.api;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.teachJava5.teachJava5.domain.Account;
import com.teachJava5.teachJava5.domain.Role;
import com.teachJava5.teachJava5.dto.RoleDTO;
import com.teachJava5.teachJava5.service.AccountService;
import com.teachJava5.teachJava5.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("")
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

//    public List<Account> getList() {
//        return accountService.findAll();
//    }

//    @GetMapping("/{id}")
//    public Role getDetail(@PathVariable("id") Long id,
//            HttpServletRequest request, HttpServletResponse response) {
//        if (id != null) {
//            Optional<Role> detail = roleService.findById(id);
//            if (!detail.isEmpty()) {
//                return detail.get();
//            }
//        }
//        response.setStatus( HttpServletResponse.SC_BAD_REQUEST  );
//       return null;
//    }
//    
//    @PostMapping("")
//    public Role createRole(@Valid @RequestBody RoleDTO roleDTO) {
//        if(roleDTO != null) {
//              Role role = new Role();
//              BeanUtils.copyProperties(roleDTO, role);
//            Role create = roleService.save(role);
//            return create;
//        }
//        return  null;
//    }
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
