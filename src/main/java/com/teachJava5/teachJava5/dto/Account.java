/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

/**
 *
 * @author tomnyson
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Account {
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 6)
    private String password;
    @NotEmpty
    private String role;
    
}
