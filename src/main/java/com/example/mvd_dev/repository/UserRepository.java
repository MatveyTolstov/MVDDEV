package com.example.mvd_dev.repository;

import com.example.mvd_dev.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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
}