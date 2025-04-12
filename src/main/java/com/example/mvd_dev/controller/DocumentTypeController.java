package com.example.mvd_dev.controller;

import com.example.mvd_dev.modeldto.DocumentTypeDto;
import com.example.mvd_dev.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document-types")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    @Autowired
    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping
    public ResponseEntity<DocumentTypeDto> createDocumentType(@RequestBody DocumentTypeDto documentTypeDto) {
        DocumentTypeDto savedDocumentType = documentTypeService.save(documentTypeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDocumentType);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTypeDto> getDocumentTypeById(@PathVariable Long id) {
        DocumentTypeDto documentType = documentTypeService.findById(id);
        return ResponseEntity.ok(documentType);
    }

    @GetMapping
    public List<DocumentTypeDto> getAllDocumentTypes() {
        return documentTypeService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentTypeDto> updateDocumentType(@PathVariable Long id, @RequestBody DocumentTypeDto documentTypeDto) {
        DocumentTypeDto updatedDocumentType = documentTypeService.update(id, documentTypeDto);
        return ResponseEntity.ok(updatedDocumentType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentType(@PathVariable Long id) {
        documentTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}