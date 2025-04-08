package com.example.mvd_dev.modeldto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @Size(min = 5, max = 50, message = "Логин должен содержать от 5 до 50 символов")
    @NotBlank(message = "Логин не может быть пустыми")
    private String login;

    @Size(min = 11, message = "Телефон должен содержать 11 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Pattern(regexp = "^\\+?[0-9]{1,3}?[-.\\s]?\\(?[0-9]{1,4}?\\)?[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,9}$",
            message = "Неверный формат номера телефона")
    private String number;

    @Size(max = 255, message = "Длина пароля должна быть не более 255 символов")
    private String password;
}
