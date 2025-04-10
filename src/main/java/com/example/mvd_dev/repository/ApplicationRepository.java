package com.example.mvd_dev.repository;

import com.example.mvd_dev.model.Application;
import com.example.mvd_dev.model.Citizen;
import com.example.mvd_dev.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findApplicationsByStatus(String status);

    List<Application> findApplicationsByCitizen(Citizen citizen);

    List<Application> findApplicationsByDocumentType(DocumentType documentType);
}