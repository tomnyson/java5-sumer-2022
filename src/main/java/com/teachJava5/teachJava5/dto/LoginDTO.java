/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teachJava5.teachJava5.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
public class LoginDTO {
    @NotEmpty(message = "Không được để trông name")
    private String username;
    @NotEmpty(message = "Không được để trông name")
    @Size(min = 3, max = 200)
    private String password;
}
