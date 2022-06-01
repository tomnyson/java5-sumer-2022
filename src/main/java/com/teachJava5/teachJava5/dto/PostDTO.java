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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tomnyson
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PostDTO {
    private long id;
    @NotEmpty(message = "Không được để trông name")
    private String name;
    @NotEmpty
    private String desription;
    private MultipartFile image;
    @NotNull
    private Long categoryId;

}
