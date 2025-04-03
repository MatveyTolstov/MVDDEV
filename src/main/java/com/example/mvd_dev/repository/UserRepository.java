package com.example.mvd_dev.repository;

import com.example.mvd_dev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNumber(String number);
}