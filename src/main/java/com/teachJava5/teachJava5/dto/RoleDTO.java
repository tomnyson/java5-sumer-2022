/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author tomnyson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private long roleId;
    @NotEmpty(message = "Không được để trông name")
    private String name;
}
