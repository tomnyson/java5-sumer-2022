/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.dto;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author tomnyson
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    ArrayList<ItemDTO> carts = new ArrayList<>();
    
   public boolean add(ItemDTO item) {
       try {
           // update
           if(carts.contains(item)) {
             ItemDTO current = carts.get(carts.indexOf(item));
             current.setSoLuong(current.getSoLuong()+1);
           } else {
               // tao moi
           carts.add(item);
           }
           return true;
       } catch (Exception e) {
            e.printStackTrace();
           return false;
       }
   }
   
   public boolean remove(ItemDTO item) {
       try {
           // kt tồn tại
           if(carts.contains(item)) {
               int vt = carts.indexOf(item);
               carts.remove(vt);
                return true;
           }
          return false;
       } catch (Exception e) {
           e.printStackTrace();
              return false;
       }
   }
   // tính tiền
   public double total() {
      double sum =0;
      for (ItemDTO cart : carts) {
          sum+=cart.getPrice()*cart.getSoLuong();
       }
      return sum;
   }
   // lấy số lượng item trong cart
   public int size() {
      return carts!=null ? carts.size(): 0;
   }
}
