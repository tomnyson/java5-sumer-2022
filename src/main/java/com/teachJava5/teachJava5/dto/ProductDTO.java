/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.dto;

import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
public class ProductDTO {

    private long id;
    @NotEmpty(message = "Không được để trống name")
    private String name;
    private String desription;
    private String image;
    @NotBlank
    @Size(min = 1)
    private Double price;
    private String shortDesription;
    private Date publicationDate;
    @NotBlank
    @Size(min = 0, max = 100)
    private Double sale;
    private Boolean bestSeller;
    private Long categoryId;

}
