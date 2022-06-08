/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.dto;

import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tomnyson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private long id;
     @NotEmpty(message = "name không để trống")
    private String name;
    @NotEmpty(message = "desription không để trống")
    private String desription;
    @NotNull
    private MultipartFile image;
    @Min(1)
    private Double price;
    @NotEmpty(message = "shortDesription không để trống")
    private String shortDesription;
    private String publicationDate;
    @Min(1)
    @Max(100)
    private Double sale;
    private Boolean bestSeller;
    private Long categoryId;

}
