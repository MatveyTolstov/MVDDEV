package com.example.mvd_dev.modeldto;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private long idEmployee;

    private long departmentId;

    private long userId;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    private String surname;

    @NotBlank(message = "Midname cannot be empty")
    private String midname;
}