package com.example.mvd_dev.service;

import com.example.mvd_dev.mapper.RoleMapper;
import com.example.mvd_dev.model.Role;
import com.example.mvd_dev.modeldto.RoleDto;
import com.example.mvd_dev.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository _roleRepository;
    private final RoleMapper _roleMapper;


    public RoleDto findByRoleName(String roleName) {
        var role = _roleRepository.findRoleByRoleName(roleName).orElseThrow(() -> new NullPointerException("Такой роли нету!"));

        return _roleMapper.toDto(role);
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

//    public Role update(RoleDto role, long id) {
//        var existingRole = _roleRepository.findById(id).orElse(null);
//        var newRole = _roleMapper.toEntity(role);
//        existingRole.setRoleName(newRole.getRoleName());
//
//        return _roleRepository.save(existingRole);
//    }

}
