/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.controller;

import com.teachJava5.teachJava5.domain.Account;
import com.teachJava5.teachJava5.domain.Role;
import com.teachJava5.teachJava5.dto.AccountDTO;
import com.teachJava5.teachJava5.dto.RoleDTO;
import com.teachJava5.teachJava5.service.AccountService;
import com.teachJava5.teachJava5.service.RoleService;
import java.util.ArrayList;
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
@RequestMapping("admin/role")
public class RoleController {

    @Autowired
    RoleService roleService;
    @Autowired
    AccountDTO accountDto;

    @GetMapping("")
    // tự động new đối tượng;
    public String index(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "roles/list";  // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("create")
    public String create(Model model) {
        Role role = new Role();
        model.addAttribute("role", role);
        return "roles/create";  // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("edit/{roleId}")
    public String edit(Model model, @PathVariable("roleId") String roleId) {

        if (roleId != null) {
            Optional<Role> roleDetail = roleService.findById(roleId);
            if (roleDetail.isPresent()) {
                model.addAttribute("role", roleDetail.get());
                return "/roles/edit";
            }

        }
        return "redirect:/user";
    }

    @PostMapping("create")
    public String createRole(Model model,
            @Valid @ModelAttribute("role") RoleDTO dto,
            BindingResult result,
            RedirectAttributes redirAttrs
    ) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            return "/roles/create";

        }
        Role role = new Role();
        role.setRoleId(dto.getName());
        BeanUtils.copyProperties(dto, role);
        roleService.save(role);
        redirAttrs.addFlashAttribute("success", "thêm thành công");
        return "redirect:/admin/role";  // Return tên của View, model sẽ tự động pass vào view
    }

    @PostMapping("edit")
    public String editRole(Model model,
            @Valid @ModelAttribute("role") RoleDTO dto,
            BindingResult result,
            RedirectAttributes redirAttrs
    ) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            System.out.print("loi");
            // đẩy lại view và đưa ra thông báo lỗi
            return "/roles/edit";

        }

        Role role = new Role();
//        role.setRoleId(dto.getName());
        BeanUtils.copyProperties(dto, role);
        role.setRoleId(dto.getRoleId());
        roleService.save(role);
        redirAttrs.addFlashAttribute("success", "edit thành công");
        return "redirect:/admin/role";  // Return tên của View, model sẽ tự động pass vào view
    }
//    // get request has path
//    @GetMapping("user/{username}")
//    public String detail(Model model,
//            @PathVariable("username") String username
//    ) {
//
//        if (username != null) {
//            Account detail = accountService.getById(username);
//            model.addAttribute("account", detail);
//            return "users/detail";
//        }
//        return "redirect:users";  // Return tên của View, model sẽ tự động pass vào view
//    }
//

    @GetMapping("delete/{roleId}")
    public String delete(
            @PathVariable("roleId") String roleId,
            RedirectAttributes redirAttrs
    ) {
        if (roleId != null) {
            Optional<Role> detail = roleService.findById(roleId);
            if (detail.isPresent()) {
                roleService.delete(detail.get());
                redirAttrs.addFlashAttribute("success", "delete thành công");
                return "redirect:/admin/role";
            }
        }
        return "redirect:/admin/role";
    }

}
