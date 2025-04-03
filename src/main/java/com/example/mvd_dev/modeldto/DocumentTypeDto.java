package com.example.mvd_dev.modeldto;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeDto {
    private long documentTypeId;

    @NotBlank(message = "Document type cannot be empty")
    private String documentType;
}