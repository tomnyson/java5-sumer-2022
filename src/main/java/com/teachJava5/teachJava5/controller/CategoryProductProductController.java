/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.controller;

import com.teachJava5.teachJava5.domain.CategoryProduct;
import com.teachJava5.teachJava5.dto.CategoryProductDTO;
import com.teachJava5.teachJava5.service.CategoryProductService;
import com.teachJava5.teachJava5.utils.TextHelper;
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
@RequestMapping("admin/category-product")
public class CategoryProductProductController {

    @Autowired
    CategoryProductService categoryProductService;

    @GetMapping("")
    // tự động new đối tượng;
    public String index(Model model) {
        List<CategoryProduct> categories = categoryProductService.findAll();
        model.addAttribute("categories", categories);
        return "category_products/list";  // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("create")
    public String create(Model model) {
        CategoryProduct CategoryProduct = new CategoryProduct();
        model.addAttribute("categoryProduct", CategoryProduct);
        return "category_products/create";  // Return tên của View, model sẽ tự động pass vào view
    }

    @PostMapping("create")
    public String cateCategoryProduct(Model model,
            @Valid @ModelAttribute("CategoryProduct") CategoryProductDTO dto,
            BindingResult result,
            RedirectAttributes redirAttrs
    ) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            return "/category_product/create";

        }
        
        CategoryProduct CategoryProduct = new CategoryProduct();
        BeanUtils.copyProperties(dto, CategoryProduct);
        categoryProductService.save(CategoryProduct);
        redirAttrs.addFlashAttribute("success", "thêm thành công");
        return "redirect:/admin/category-product";  // Return tên của View, model sẽ tự động pass vào view
    }

    @PostMapping("edit")
    public String editRole(Model model,
            @Valid @ModelAttribute("categoryProduct") CategoryProductDTO dto,
            BindingResult result,
            RedirectAttributes redirAttrs
    ) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            System.out.print("loi");
            // đẩy lại view và đưa ra thông báo lỗi
            return "/category-products/edit";

        }

        CategoryProduct CategoryProduct = new CategoryProduct();
//        role.setRoleId(dto.getName());
        BeanUtils.copyProperties(dto, CategoryProduct);
        categoryProductService.save(CategoryProduct);
        redirAttrs.addFlashAttribute("success", "edit thành công");
        return "redirect:/admin/category-product";  // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("delete/{CategoryProductId}")
    public String delete(
            @PathVariable("CategoryProductId") Long CategoryProductId,
            RedirectAttributes redirAttrs
    ) {
        if (CategoryProductId != null) {
            Optional<CategoryProduct> detail = categoryProductService.findById(CategoryProductId);
            if (detail.isPresent()) {
                categoryProductService.delete(detail.get());
                redirAttrs.addFlashAttribute("success", "delete thành công");
                return "redirect:/admin/category-product";
            }
        }
        return "redirect:/admin/category-product";
    }

    @GetMapping("edit/{categoryProductId}")
    public String edit(Model model, @PathVariable("categoryProductId") Long CategoryProductId) {

        if (CategoryProductId != null) {
            Optional<CategoryProduct> detail = categoryProductService.findById(CategoryProductId);
            if (detail.isPresent()) {
                model.addAttribute("categoryProduct", detail.get());
                return "/category_products/edit";
            }

        }
        return "redirect:/admin/category-product";
    }
}
