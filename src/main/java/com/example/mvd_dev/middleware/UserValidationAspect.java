package com.example.mvd_dev.middleware;

import com.example.mvd_dev.modeldto.UserDto;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserValidationAspect {

    @Pointcut("execution(* com.example.mvd_dev.service.UserService.*(..))")
    public void userServiceMethods() {
    }

    @Before("userServiceMethods() && args(userDto)")
    public void validateUser (UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("Пользователь не может быть пустым!");
        }
        if (userDto.getLogin() == null || userDto.getLogin().isEmpty()) {
            throw new IllegalArgumentException("Логин не может быть пустым!");
        }
        if (userDto.getNumber() == null || userDto.getNumber().isEmpty()) {
            throw new IllegalArgumentException("Номер телефона не может быть пустым!");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Пароль не может быть пустым!");
        }
    }

    @Before("userServiceMethods() && args(id)")
    public void validateUserId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID пользователя должен быть больше 0");
        }
    }
}
