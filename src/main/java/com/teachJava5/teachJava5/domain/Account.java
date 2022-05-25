/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author tomnyson
 */
@Data
@Entity
@Table(name = "accounts")
public class Account implements Serializable {
    @Id
    @Column(length = 50, nullable = false)
    private String username;
    @Column(length = 200, nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
}