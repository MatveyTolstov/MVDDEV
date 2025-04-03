package com.example.mvd_dev.modeldto;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long idUser ;

    @NotBlank(message = "Number cannot be empty")
    private String number;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    private long roleId; // ID роли
}