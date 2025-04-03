package com.example.mvd_dev.modeldto;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private long idDepartment;

    @NotBlank(message = "Department name cannot be empty")
    private String name;

    @NotBlank(message = "Address cannot be empty")
    private String address;
}