/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teachJava5.teachJava5.controller.api;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("api/product")
public class APIController {

    List<ProductDTO> listProduct = new ArrayList<>();

    APIController() {
        listProduct.add(new ProductDTO(1, "iphone 11", 1200));
        listProduct.add(new ProductDTO(2, "sasung fold", 1800));
    }

    @GetMapping("")
    public List<ProductDTO> getList() {
        return listProduct;
    }

    @GetMapping("/{id}")
    public ProductDTO getDetail(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        if (id != null) {
            List<ProductDTO> detail = listProduct.stream().filter(prdct -> prdct.getId() == id).toList();
            if (!detail.isEmpty()) {
                return detail.get(0);
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
