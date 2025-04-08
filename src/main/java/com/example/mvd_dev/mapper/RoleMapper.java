package com.example.mvd_dev.mapper;

import com.example.mvd_dev.model.Role;
import com.example.mvd_dev.model.Roles;
import com.example.mvd_dev.modeldto.RoleDto;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role toEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        var rolesEnum = Roles.valueOf(roleDto.getRoleName());

        Role role = new Role();
        role.setRoleName(rolesEnum);
        return role;
    }

    public RoleDto toDto(Role role) {
        if (role == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();

        roleDto.setRoleName(role.getRoleName().name());
        return roleDto;
    }

}
