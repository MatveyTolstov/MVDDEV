package com.example.mvd_dev.service;

import com.example.mvd_dev.mapper.DocumentTypeMapper;
import com.example.mvd_dev.model.DocumentType;
import com.example.mvd_dev.modeldto.DocumentTypeDto;
import com.example.mvd_dev.repository.DocumentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.mvd_dev.service.ErrorMessages.DOCUMENT_TYPE_NOT_FOUND;

@Service
@AllArgsConstructor
public class DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    public DocumentTypeDto save(DocumentTypeDto documentTypeDto) {
        DocumentType documentType = documentTypeMapper.toEntity(documentTypeDto);
        documentType = documentTypeRepository.save(documentType);
        return documentTypeMapper.toDto(documentType);
    }

    public DocumentTypeDto findById(Long id) {
        return documentTypeRepository.findById(id)
                .map(documentTypeMapper::toDto)
                .orElseThrow(() -> new RuntimeException(DOCUMENT_TYPE_NOT_FOUND));
    }

    public List<DocumentTypeDto> findAll() {
        return documentTypeRepository.findAll().stream()
                .map(documentTypeMapper::toDto)
                .toList();
    }

    public DocumentTypeDto update(Long id, DocumentTypeDto documentTypeDto) {
        DocumentType existingDocumentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(DOCUMENT_TYPE_NOT_FOUND));

        existingDocumentType.setDocumentType(documentTypeDto.getDocumentType());

        return documentTypeMapper.toDto(documentTypeRepository.save(existingDocumentType));
    }

    public void delete(Long id) {
        DocumentType existingDocumentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(DOCUMENT_TYPE_NOT_FOUND));
        documentTypeRepository.delete(existingDocumentType);
    }
}