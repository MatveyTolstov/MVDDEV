package com.example.mvd_dev.mapper;

import com.example.mvd_dev.model.Application;
import com.example.mvd_dev.modeldto.ApplicationDto;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public Application toEntity(ApplicationDto applicationDto) {
        if (applicationDto == null) {
            return null;
        }
        Application application = new Application();
        application.setStatus(applicationDto.getStatus());
        application.setCitizenId(applicationDto.getCitizenId());
        application.setNameApplication(applicationDto.getNameApplication());
        application.setDescription(applicationDto.getDescription());
        application.setSubmissionDate(applicationDto.getSubmissionDate());
        application.setCompletionDate(applicationDto.getCompletionDate());
        application.setDocumentTypeId(applicationDto.getDocumentTypeId());
        return application;
    }

    public ApplicationDto toDto(Application application) {
        if (application == null) {
            return null;
        }
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setStatus(application.getStatus());
        applicationDto.setCitizenId(application.getCitizenId());
        applicationDto.setNameApplication(application.getNameApplication());
        applicationDto.setDescription(application.getDescription());
        applicationDto.setSubmissionDate(application.getSubmissionDate());
        applicationDto.setCompletionDate(application.getCompletionDate());
        applicationDto.setDocumentTypeId(application.getDocumentTypeId());
        return applicationDto;
    }
}