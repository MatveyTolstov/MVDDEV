package com.example.mvd_dev.repository;

import com.example.mvd_dev.model.Role;
import com.example.mvd_dev.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий представляющий методы для работы с ролью
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByRoleName(Roles roleName);
}
