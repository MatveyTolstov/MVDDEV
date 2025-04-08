package com.example.mvd_dev.modeldto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignInRequest {

    @Size(min = 5, max = 50, message = "Логин должен содержать от 5 до 50 символов")
    @NotBlank(message = "Логин не может быть пустыми")
    private String login;

    @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;
}
