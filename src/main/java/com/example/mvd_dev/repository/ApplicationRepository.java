package com.example.mvd_dev.repository;

import com.example.mvd_dev.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с сущностью Application
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    
    List<Application> findApplicationsByStatus(String status);


}