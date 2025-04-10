package com.example.mvd_dev.repository;

import com.example.mvd_dev.model.UserEntity;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью User
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    Optional<UserEntity> findByLogin(String login);

    boolean existsByLogin(String login);

    boolean existsByNumber(String number);


    @Query(value = """
        SELECT r.role_name FROM users u
        INNER JOIN roles r ON u.role_id = r.id_user
        WHERE u.id = :user_id
        """, nativeQuery = true)
    Optional<String> getUserRoleNameById(@Param("user_id") long userId);
}