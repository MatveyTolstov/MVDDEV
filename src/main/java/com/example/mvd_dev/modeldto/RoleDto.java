package com.example.mvd_dev.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    @NotBlank(message = "Role name cannot be empty")
    private String roleName;

}
