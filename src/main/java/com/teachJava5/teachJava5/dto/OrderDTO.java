/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teachJava5.teachJava5.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author tomnyson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    @NotEmpty(message = "Không được để trông name")
    private String customerName;
    @NotEmpty(message = "Không được để trông name")
    private String address;
    @NotEmpty(message = "Không được để trông name")
    private String phone;
    @NotEmpty(message = "Không được để trông name")
    private String note;
}
