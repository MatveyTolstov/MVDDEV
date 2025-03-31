package com.example.mvd_dev.repository;

import com.example.mvd_dev.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий представляющий методы для работы с ролью
 */
@Repository
public interface RoleCrudRepository extends JpaRepository<Role, Long> {

    /**
     * Нахождение роли по названию
     * @param roleName
     * @return Role
     */
    Role findByRoleName(String roleName);

    /**
     * Нахождение роли по id
     * @param id
     * @return Role
     */
    Role findByIdRole(long id);

    /**
     * Сохранение роли в систему
     * @param role
     * @return Role
     */
    Role save(Role role);

    /**
     * Удаление роли
     * @param id
     */
    void deleteRoleByIdRole(long id);


    /**
     * Получения всех ролей
     * @return List Role
     */
    @Override
    List<Role> findAll();
}
