package com.example.mvd_dev.repository;

import com.example.mvd_dev.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserDetailsRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);
    Optional<UserEntity> findByNumber(String number);


}
