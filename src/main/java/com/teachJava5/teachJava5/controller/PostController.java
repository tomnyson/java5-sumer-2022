/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.controller;

import com.teachJava5.teachJava5.domain.Category;
import com.teachJava5.teachJava5.domain.Post;
import com.teachJava5.teachJava5.domain.PostTag;
import com.teachJava5.teachJava5.domain.PostTagkey;
import com.teachJava5.teachJava5.domain.Tag;
import com.teachJava5.teachJava5.dto.PostDTO;
import com.teachJava5.teachJava5.service.CategoryService;
import com.teachJava5.teachJava5.service.FileService;
import com.teachJava5.teachJava5.service.PostService;
import com.teachJava5.teachJava5.service.PostTagService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;

/**
 *
 * @author tomnyson
 */
@Controller
@RequestMapping("admin/post")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    FileService fileService;
    @Autowired
    PostTagService postTagService;
    
    @ModelAttribute("categories")
    public List<Category> getCategories() {
        List<Category> countries = categoryService.findAll();
        return countries;
    }

    @GetMapping("")
    // tự động new đối tượng;
    public String index(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts/list"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("create")
    public String create(Model model) {
        PostDTO post = new PostDTO();
        model.addAttribute("post", post);
        return "posts/create"; // Return tên của View, model sẽ tự động pass vào view
    }

    @PostMapping("create")
    public String createPost(Model model,
            @Valid @ModelAttribute("post") PostDTO dto,
            BindingResult result,
            RedirectAttributes redirAttrs) throws IOException {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            return "/posts/create";

        }
        String uploadDir = "src/main/resources/static/images";
        String userDirectory = System.getProperty("user.dir") + "/src/main/resources/static/image";
        Post post = new Post();
        Category cat = new Category();
        String fileName = StringUtils.cleanPath(dto.getImage().getOriginalFilename());
        BeanUtils.copyProperties(dto, post);
        post.setImage(fileName);
        cat.setId(dto.getCategoryId());
        post.setCategory(cat);
        Tag tag = new Tag();
        tag.setId(1);
        PostTag postTag = new PostTag();
       
        
        Post postSaved = postService.save(post);
        PostTagkey id = new PostTagkey(postSaved.getId(), tag.getId());
        postTag.setId(id);
        postTag.setPost(postSaved);
        postTag.setTag(tag);
        postTag.setStatus(true);
        postTagService.save(postTag);
        FileService.saveFile(userDirectory, fileName, dto.getImage());
       ;
        redirAttrs.addFlashAttribute("success", "thêm thành công");
        return "redirect:/admin/post"; // Return tên của View, model sẽ tự động pass vào view
    }

    @PostMapping("edit")
    public String editRole(Model model,
            @Valid @ModelAttribute("post") PostDTO dto,
            BindingResult result,
            RedirectAttributes redirAttrs) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            System.out.print("loi");
            // đẩy lại view và đưa ra thông báo lỗi
            return "/categories/edit";

        }

        Post post = new Post();
        Category cat = new Category();
        cat.setId(dto.getCategoryId());
        post.setCategory(cat);
        BeanUtils.copyProperties(dto, post);
        postService.save(post);
        redirAttrs.addFlashAttribute("success", "edit thành công");
        return "redirect:/admin/post"; // Return tên của View, model sẽ tự động pass vào view
    }
    //
    // @GetMapping("delete/{roleId}")
    // public String delete(
    // @PathVariable("roleId") String roleId,
    // RedirectAttributes redirAttrs
    // ) {
    // if (roleId != null) {
    // Optional<Post> detail = postService.findById(roleId);
    // if (detail.isPresent()) {
    // postService.delete(detail.get());
    // redirAttrs.addFlashAttribute("success", "delete thành công");
    // return "redirect:/admin/Post";
    // }
    // }
    // return "redirect:/admin/role";
    // }
    //

    @GetMapping("edit/{postId}")
    public String edit(Model model, @PathVariable("postId") Long postId) {

        if (postId != null) {
            Post detail = postService.getById(postId);
            PostDTO dto = new PostDTO();
            BeanUtils.copyProperties(detail, dto);
            if (detail != null) {
                model.addAttribute("post", dto);
                return "/posts/edit";
            }

        }
        return "redirect:/posts";
    }
}
