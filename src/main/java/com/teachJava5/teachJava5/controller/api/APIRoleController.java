/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teachJava5.teachJava5.controller.api;

import com.teachJava5.teachJava5.domain.Role;
import com.teachJava5.teachJava5.dto.RoleDTO;
import com.teachJava5.teachJava5.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tomnyson
 */
@RestController
@RequestMapping("api/role")
public class APIRoleController {
    @Autowired
    private RoleService roleService;
    List<ProductDTO> listProduct = new ArrayList<>();

    APIRoleController() {
        listProduct.add(new ProductDTO(1, "iphone 11", 1200));
        listProduct.add(new ProductDTO(2, "sasung fold", 1800));
    }

    @GetMapping("")
    public List<Role> getList() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public Role getDetail(@PathVariable("id") Long id,
            HttpServletRequest request, HttpServletResponse response) {
        if (id != null) {
            Optional<Role> detail = roleService.findById(id);
            if (!detail.isEmpty()) {
                return detail.get();
            }
        }
        response.setStatus( HttpServletResponse.SC_BAD_REQUEST  );
       return null;
    }
    
    @PostMapping("")
    public ProductDTO createProduct(@RequestBody ProductDTO product) {
        if(product != null) {
            listProduct.add(product);
            return product;
        }
        return  null;
    }
    
    @DeleteMapping("/{id}")
    public ProductDTO removeProduct(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        if(id != null) {
           ProductDTO detail = null;
            for (ProductDTO productDTO : listProduct) {
                if(productDTO.getId()== id){
                     detail = productDTO;
                     break;
                }
                        
            }
            if(detail != null) {
                listProduct.remove(detail);
                return detail;
            }
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST  );
        }
        return  null;
    }

}
