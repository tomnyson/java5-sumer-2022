/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author tomnyson
 */
@Data
@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @Column(length = 50, nullable = false)
    private String categoryId;
    @Column(length = 200, nullable = false)
    private String name;
    @OneToMany(mappedBy = "category",
            cascade = {CascadeType.ALL})
    Set<Post> posts;

}

