package com.example.mvd_dev.repository;

import com.example.mvd_dev.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью Citizen
 */
@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    @Query("SELECT c FROM Citizen c WHERE c.name = :name AND c.surname = :surname")
    Optional<Citizen> findByNameAndSurname(String name, String surname);
}