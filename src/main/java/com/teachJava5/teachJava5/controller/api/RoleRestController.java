/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.controller.api;

import com.teachJava5.teachJava5.domain.Role;
import com.teachJava5.teachJava5.dto.RoleDTO;
import com.teachJava5.teachJava5.service.RoleService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author tomnyson
 */
@Controller
@RequestMapping("api/test")
public class RoleRestController {

    @Autowired
    RoleService roleService;

    @GetMapping("")
    public ResponseEntity<List<Role>> getAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<String> create(@Valid @RequestBody RoleDTO role) {
           return ResponseEntity.ok("User is valid");   
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> detail(@PathVariable("id") Long id) {
        Optional<Role> currentData = roleService.findById(id);
        if (currentData.isPresent()) {
            return new ResponseEntity<Role>(currentData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> delete(@PathVariable("id") Long id) {
        Optional<Role> currentData = roleService.findById(id);
        if (currentData.isPresent()) {
            roleService.delete(currentData.get());
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   
}
