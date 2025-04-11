package com.example.mvd_dev.mapper;

import com.example.mvd_dev.model.Role;
import com.example.mvd_dev.modeldto.RoleDto;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role toEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }

        Role role = new Role();
        role.setRoleName(role.getRoleName());
        return role;
    }

    public RoleDto toDto(Role role) {
        if (role == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();

        roleDto.setRoleName(role.getRoleName());
        return roleDto;
    }

}
