/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author tomnyson
 */
@Data
@Entity
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String name;

    @Column(length = 1000, nullable = false)
    private String desription;

    @Column(length = 100, nullable = false)
    private String image;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    // @ManyToMany
    // @JoinTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id"),
    // inverseJoinColumns = @JoinColumn(name = "tag_id"))
    // private Set<Tag> tags = new HashSet<>();
    @OneToMany(mappedBy = "tag")
    private Set<PostTag> postTag = new HashSet<>();

}
