/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.controller;

import com.teachJava5.teachJava5.domain.Category;
import com.teachJava5.teachJava5.domain.CategoryProduct;
import com.teachJava5.teachJava5.domain.Product;
import com.teachJava5.teachJava5.dto.AccountDTO;
import com.teachJava5.teachJava5.dto.CategoryDTO;
import com.teachJava5.teachJava5.dto.ProductDTO;
import com.teachJava5.teachJava5.service.CategoryProductService;
import com.teachJava5.teachJava5.service.CategoryService;
import com.teachJava5.teachJava5.service.ProductService;
import com.teachJava5.teachJava5.utils.TextHelper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author tomnyson
 */
@Controller
@RequestMapping("admin/product")
public class ProductController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @Autowired
    CategoryProductService categoryProductService;

    @ModelAttribute("categories")
    public List<CategoryProduct> getCategories() {
        List<CategoryProduct> categories = categoryProductService.findAll();
        return categories;
    }

    @GetMapping("")
    // tự động new đối tượng;
    public String index(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products/list";  // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("create")
    public String create(Model model) {
        ProductDTO product = new ProductDTO();
        model.addAttribute("product", product);
        return "products/create";  // Return tên của View, model sẽ tự động pass vào view
    }

    @PostMapping("create")
    public String createProduct(Model model,
            @Valid @ModelAttribute("product") ProductDTO dto,
            BindingResult result,
            RedirectAttributes redirAttrs
    ) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            System.err.println("lỗi");
            // đẩy lại view và đưa ra thông báo lỗi

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return "/products/create";

        }

        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        try {
            System.out.println("date"+dto.getPublicationDate());
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getPublicationDate());
           product.setPublicationDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        CategoryProduct cat = new CategoryProduct();
        cat.setId(dto.getCategoryId());
        product.setCategory(cat);
        productService.save(product);
        redirAttrs.addFlashAttribute("success", "thêm thành công");
        return "redirect:/admin/product";  // Return tên của View, model sẽ tự động pass vào view
    }

    @PostMapping("edit")
    public String editRole(Model model,
            @Valid @ModelAttribute("category") CategoryDTO dto,
            BindingResult result,
            RedirectAttributes redirAttrs
    ) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            System.out.print("loi");

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            // đẩy lại view và đưa ra thông báo lỗi
            return "/categories/edit";

        }

        Category category = new Category();
//        role.setRoleId(dto.getName());
        BeanUtils.copyProperties(dto, category);
        categoryService.save(category);
        redirAttrs.addFlashAttribute("success", "edit thành công");
        return "redirect:/admin/product";  // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("delete/{categoryId}")
    public String delete(
            @PathVariable("categoryId") Long categoryId,
            RedirectAttributes redirAttrs
    ) {
        if (categoryId != null) {
            Optional<Category> detail = categoryService.findById(categoryId);
            if (detail.isPresent()) {
                categoryService.delete(detail.get());
                redirAttrs.addFlashAttribute("success", "delete thành công");
                return "redirect:/admin/category";
            }
        }
        return "redirect:/admin/role";
    }

    @GetMapping("edit/{categoryId}")
    public String edit(Model model, @PathVariable("categoryId") Long categoryId) {

        if (categoryId != null) {
            Optional<Category> detail = categoryService.findById(categoryId);
            if (detail.isPresent()) {
                model.addAttribute("category", detail.get());
                return "/categories/edit";
            }

        }
        return "redirect:/user";
    }
}
