package com.example.mvd_dev.controller;

import com.example.mvd_dev.modeldto.ApplicationDto;
import com.example.mvd_dev.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> getAllApplications() {
        List<ApplicationDto> applications = applicationService.findAll();
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDto> getApplicationById(@PathVariable Long id) {
        ApplicationDto application = applicationService.findById(id);
        return ResponseEntity.ok(application);
    }

    @PostMapping
    public ResponseEntity<ApplicationDto> createApplication(@RequestBody ApplicationDto applicationDto) {
        ApplicationDto savedApplication = applicationService.save(applicationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedApplication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationDto> updateApplication(@PathVariable Long id, @RequestBody ApplicationDto applicationDto) {
        ApplicationDto updatedApplication = applicationService.update(id, applicationDto);
        return ResponseEntity.ok(updatedApplication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/citizen/{citizenId}")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByCitizenId(@PathVariable Long citizenId) {
        List<ApplicationDto> applications = applicationService.findApplicationsByCitizenId(citizenId);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/documentType/{documentTypeId}")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByDocumentTypeId(@PathVariable Long documentTypeId) {
        List<ApplicationDto> applications = applicationService.findApplicationsByDocumentTypeId(documentTypeId);
        return ResponseEntity.ok(applications);
    }
}