package com.example.mvd_dev.service;

import com.example.mvd_dev.model.Role;
import com.example.mvd_dev.repository.RoleCrudRepository;

public class RoleCrudService{
    private final RoleCrudRepository _roleCrudRepository;

    public RoleCrudService(RoleCrudRepository roleCrudRepository) {
        _roleCrudRepository = roleCrudRepository;
    }

    public Role findByRoleName(String roleName) {
        Role role = _roleCrudRepository.findByRoleName(roleName);
        if (role == null) {
            throw new IllegalArgumentException("Role not found");
        }
        return role;
    }

    public Role findByIdRole(long id) {
        Role role = _roleCrudRepository.findById(id).orElse(null);
        if (role == null) {
            throw new IllegalArgumentException("Role not found");
        }
        return role;
    }

}
