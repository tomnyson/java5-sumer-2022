/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teachJava5.teachJava5.serviceImp;

import com.teachJava5.teachJava5.domain.Role;
import com.teachJava5.teachJava5.repository.RoleRepository;
import com.teachJava5.teachJava5.service.RoleService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tomnyson
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public <S extends Role> S save(S entity) {
        return roleRepository.save(entity);
    }

    public Optional<Role> findById(String id) {
        return roleRepository.findById(id);
    }

    public void delete(Role entity) {
        roleRepository.delete(entity);
    }
    
}
