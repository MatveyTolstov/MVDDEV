package com.example.mvd_dev.repository;

import com.example.mvd_dev.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью DocumentType
 */
@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

    Optional<DocumentType> findByDocumentType(String documentType);
}