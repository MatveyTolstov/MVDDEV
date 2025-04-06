package com.example.mvd_dev.modeldto;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {
    private long idApplication;

    @NotBlank(message = "Application name cannot be empty")
    private String nameApplication;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotBlank(message = "Status cannot be empty")
    private String status;

    @NotNull(message = "Submission date cannot be null")
    private Date submissionDate;

    @NotNull(message = "Completion date cannot be null")
    private Date completionDate;

    @Positive(message = "Citizen ID must be positive")
    private long citizenId;

    @Positive(message = "Document Type ID must be positive")
    private long documentTypeId;
}