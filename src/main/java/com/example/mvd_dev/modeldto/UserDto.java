package com.example.mvd_dev.modeldto;

import com.example.mvd_dev.model.Role;
import lombok.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "Number cannot be empty")
    private String number;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Login cannot be empty")
    private String login;

    private long roleId;
}