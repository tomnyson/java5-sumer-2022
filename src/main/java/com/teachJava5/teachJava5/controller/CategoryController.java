/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.controller;

import com.teachJava5.teachJava5.domain.Category;
import com.teachJava5.teachJava5.dto.AccountDTO;
import com.teachJava5.teachJava5.dto.CategoryDTO;
import com.teachJava5.teachJava5.service.CategoryService;
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
@RequestMapping("admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    
    @GetMapping("")
    // tự động new đối tượng;
    public String index(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "categories/list";  // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("create")
    public String create(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "categories/create";  // Return tên của View, model sẽ tự động pass vào view
    }

    @PostMapping("create")
    public String cateCategory(Model model,
            @Valid @ModelAttribute("category") CategoryDTO dto,
            BindingResult result,
            RedirectAttributes redirAttrs
    ) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            return "/categories/create";

        }
        
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);
        categoryService.save(category);
        redirAttrs.addFlashAttribute("success", "thêm thành công");
        return "redirect:/admin/category";  // Return tên của View, model sẽ tự động pass vào view
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
            // đẩy lại view và đưa ra thông báo lỗi
            return "/categories/edit";

        }

        Category category = new Category();
//        role.setRoleId(dto.getName());
        BeanUtils.copyProperties(dto, category);
        categoryService.save(category);
        redirAttrs.addFlashAttribute("success", "edit thành công");
        return "redirect:/admin/category";  // Return tên của View, model sẽ tự động pass vào view
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
