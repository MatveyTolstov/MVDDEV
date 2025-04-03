package com.example.mvd_dev.modeldto;

import lombok.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {
    private long idApplication;

    @NotBlank(message = "Application name cannot be empty")
    private String nameApplication;

    private String description;

    private String status;

    private Date submissionDate;

    private Date completionDate;

    private long citizenId; // ID гражданина

    private long documentTypeId; // ID типа документа
}