/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.controller;

import com.teachJava5.teachJava5.domain.Account;
import com.teachJava5.teachJava5.domain.Order;
import com.teachJava5.teachJava5.domain.OrderDetail;
import com.teachJava5.teachJava5.domain.OrderDetailKey;
import com.teachJava5.teachJava5.domain.Product;
import com.teachJava5.teachJava5.domain.Role;
import com.teachJava5.teachJava5.dto.AccountDTO;
import com.teachJava5.teachJava5.dto.CartDTO;
import com.teachJava5.teachJava5.dto.CategoryDTO;
import com.teachJava5.teachJava5.dto.ItemDTO;
import com.teachJava5.teachJava5.dto.OrderDTO;
import com.teachJava5.teachJava5.service.AccountService;
import com.teachJava5.teachJava5.service.OrderDetailService;
import com.teachJava5.teachJava5.service.OrderService;
import com.teachJava5.teachJava5.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
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
@RequestMapping("cart")
public class CartController {

    @Autowired
    HttpSession httpSession;
    @Autowired
    ProductService productService;
    @Autowired
    AccountService accountService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @GetMapping("")
    public String list(Model model) {
        return "carts/list";
    }

    @GetMapping("checkout")
    public String checkout(Model model) {
        OrderDTO order = new OrderDTO();
        model.addAttribute("order", order);
        return "carts/checkout";
    }

    @PostMapping("checkout")
    public String createCheckout(@Valid @ModelAttribute("order") OrderDTO dto,
            BindingResult result,
            RedirectAttributes redirAttrs) {

        if (result.hasErrors()) {
            System.out.print("loi");
            // đẩy lại view và đưa ra thông báo lỗi
            return "carts/checkout";
        }
        // save Order first
        Order order = new Order();
        BeanUtils.copyProperties(dto, order);
        String username = (String) httpSession.getAttribute("username");
        Account account = accountService.getById(username);
        order.setAccount(account);
        CartDTO cart = (CartDTO)httpSession.getAttribute("cart");
        order.setTotal(cart.total());
        Order orderCreated = orderService.save(order);
        for (ItemDTO item: cart.getCarts()) {
            OrderDetail orderDetail = new OrderDetail();
            OrderDetailKey key = new OrderDetailKey(order.getId(), item.getMaSP());
            orderDetail.setId(key);
            orderDetail.setOrder(order);
            Product product = new Product();
            product.setId(item.getMaSP());
            orderDetail.setProduct(product);
            orderDetail.setPrice(item.getPrice());
            orderDetail.setQuantity(item.getSoLuong());
            orderDetail.setTotal(item.getPrice()*item.getSoLuong());
            orderDetailService.save(orderDetail);
            
        }
        return "carts/checkout";
    }

    @GetMapping("add/{productId}")
    public String add(Model model,
            @PathVariable("productId") Long productId,
            RedirectAttributes redirAttrs
    ) {
        if (productId != null) {
            /**
             * step 1: lấy detail product step 2: kiểm tra xem cart session có
             * tồn tại chưa + chưa thì tạo mới step 3: tao itemDTO và add vào
             * cart step 4: reset cart -> cart list
             */
            CartDTO carts = (CartDTO) httpSession.getAttribute("cart");
            Optional<Product> detail = productService.findById(productId);
            ItemDTO item = new ItemDTO();
            if (detail != null) {
                item.setMaSP(detail.get().getId());
                item.setSoLuong(1);
                item.setPrice(detail.get().getPrice());
                item.setTitle(detail.get().getName());
                item.setImage(detail.get().getImage());
                if (carts != null) {
                    // có cart
                    carts.add(item);
                    redirAttrs.addFlashAttribute("success", "đã cập nhật vào giỏ hàng");
                } else {
                    carts = new CartDTO();
                    carts.add(item);
                    // tạo mới cart
                    redirAttrs.addFlashAttribute("success", "đã thêm vào giỏ hàng");
                }
                httpSession.setAttribute("cart", carts);

            }

        }
        return "redirect:/cart";
    }

    @GetMapping("remove/{productId}")
    public String remove(Model model,
            @PathVariable("productId") Long productId,
            RedirectAttributes redirAttrs
    ) {
        if (productId != null) {
            /**
             * step 1: lấy detail product step 2: kiểm tra xem cart session có
             * tồn tại chưa + chưa thì tạo mới step 3: tao itemDTO và add vào
             * cart step 4: reset cart -> cart list
             */
            CartDTO carts = (CartDTO) httpSession.getAttribute("cart");
            Optional<Product> detail = productService.findById(productId);
            ItemDTO item = new ItemDTO();
            if (detail != null) {
                item.setMaSP(detail.get().getId());
                item.setSoLuong(1);
                item.setPrice(detail.get().getPrice());
                item.setTitle(detail.get().getName());
                item.setImage(detail.get().getImage());
                if (carts != null) {
                    // có cart
                    carts.remove(item);
                }
                httpSession.setAttribute("cart", carts);
                redirAttrs.addFlashAttribute("success", "đã xóa vào giỏ hàng");
            }

        }
        return "redirect:/cart";
    }

}
