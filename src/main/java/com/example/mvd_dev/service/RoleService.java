package com.example.mvd_dev.service;

import com.example.mvd_dev.mapper.RoleMapper;
import com.example.mvd_dev.model.Role;
import com.example.mvd_dev.model.Roles;
import com.example.mvd_dev.modeldto.RoleDto;
import com.example.mvd_dev.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository _roleRepository;
    private final RoleMapper _roleMapper;

    public RoleService(RoleRepository roleCrudRepository, RoleMapper roleMapper) {
        _roleRepository = roleCrudRepository;
        _roleMapper = roleMapper;
    }

    public Role findByRoleName(String roleName) {
        var roleEnum = Roles.valueOf(roleName);
        return _roleRepository.findRoleByRoleName(roleEnum)
                .orElse(null);
    }

    public Role findByIdRole(long id) {
        return _roleRepository.findById(id).orElse(null);
    }

    public List<Role> allRoles() {
        return _roleRepository.findAll();
    }

    public void deleteRoleById(long id) {
        _roleRepository.deleteById(id);
    }

    public Role save(RoleDto roleDto) {
        var role = _roleMapper.toEntity(roleDto);

        return _roleRepository.save(role);
    }

    public Role update(RoleDto role, long id) {
        var existingRole = _roleRepository.findById(id).orElse(null);
        var newRole = _roleMapper.toEntity(role);
        existingRole.setRoleName(newRole.getRoleName());

        return _roleRepository.save(existingRole);
    }

}
