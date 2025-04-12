package com.example.mvd_dev.service;

import com.example.mvd_dev.mapper.ApplicationMapper;
import com.example.mvd_dev.model.Citizen;
import com.example.mvd_dev.model.DocumentType;
import com.example.mvd_dev.modeldto.ApplicationDto;
import com.example.mvd_dev.repository.ApplicationRepository;
import com.example.mvd_dev.repository.CitizenRepository;
import com.example.mvd_dev.repository.DocumentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mvd_dev.service.ErrorMessages.*;

@Service
@AllArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final CitizenRepository citizenRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public ApplicationDto save(ApplicationDto applicationDto) {
        var application = applicationMapper.toEntity(applicationDto);
        applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    public ApplicationDto findById(Long id) {
        return applicationRepository.findById(id)
                .map(applicationMapper::toDto)
                .orElseThrow(() -> new RuntimeException(APPLICATION_NOT_FOUND));
    }

    public ApplicationDto update(long id, ApplicationDto applicationDto) {
        var existingApplication = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(APPLICATION_NOT_FOUND));

        existingApplication.setCompletionDate(applicationDto.getCompletionDate());
        existingApplication.setDescription(applicationDto.getDescription());
        existingApplication.setNameApplication(applicationDto.getNameApplication());
        existingApplication.setStatus(applicationDto.getStatus());
        existingApplication.setSubmissionDate(applicationDto.getSubmissionDate());

        return applicationMapper.toDto(applicationRepository.save(existingApplication));
    }

    public void delete(long id) {
        var existingApplication = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(APPLICATION_NOT_FOUND));

        applicationRepository.delete(existingApplication);
    }

    public List<ApplicationDto> findAll() {
        return applicationMapper.toDtoList(applicationRepository.findAll());
    }


    public List<ApplicationDto> findApplicationsByCitizenId(Long citizenId) {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new RuntimeException(CITIZEN_NOT_FOUND));
        return applicationMapper.toDtoList(applicationRepository.findApplicationsByCitizen(citizen));
    }


    public List<ApplicationDto> findApplicationsByDocumentTypeId(Long documentTypeId) {
        DocumentType documentType = documentTypeRepository.findById(documentTypeId)
                .orElseThrow(() -> new RuntimeException(DOCUMENT_TYPE_NOT_FOUND));
        return applicationMapper.toDtoList(applicationRepository.findApplicationsByDocumentType(documentType));
    }








}