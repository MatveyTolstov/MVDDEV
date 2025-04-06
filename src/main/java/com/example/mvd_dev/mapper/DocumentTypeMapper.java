package com.example.mvd_dev.mapper;

import com.example.mvd_dev.model.DocumentType;
import com.example.mvd_dev.modeldto.DocumentTypeDto;
import org.springframework.stereotype.Component;

@Component
public class DocumentTypeMapper {

    public DocumentType toEntity(DocumentTypeDto documentTypeDto) {
        if (documentTypeDto == null) {
            return null;
        }
        DocumentType documentType = new DocumentType();
        documentType.setDocumentType(documentTypeDto.getDocumentType());
        return documentType;
    }

    public DocumentTypeDto toDto(DocumentType documentType) {
        if (documentType == null) {
            return null;
        }
        DocumentTypeDto documentTypeDto = new DocumentTypeDto();
        documentTypeDto.setDocumentType(documentType.getDocumentType());
        return documentTypeDto;
    }
}