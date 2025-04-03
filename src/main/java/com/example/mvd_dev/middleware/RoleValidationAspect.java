package com.example.mvd_dev.middleware;

import com.example.mvd_dev.model.Role;
import com.example.mvd_dev.modeldto.RoleDto;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RoleValidationAspect {
    @Pointcut("execution(* com.example.mvd_dev.service.RoleService.*(..))")
    public void roleServiceMethods() {
    }

    @Before("roleServiceMethods() && args(roleName)")
    public void validateRoleName(String roleName) {
        if (roleName == null) {
            throw new IllegalArgumentException("Role name cannot be null");
        }
        if (roleName.isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be empty");
        }
    }

    @Before("roleServiceMethods() && args(id)")
    public void validateRoleId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Role id must be greater than 0");
        }
    }

    @Before("roleServiceMethods() && args(roleDto)")
    public void validateRole(RoleDto roleDto) {
        if (roleDto == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
    }

}
