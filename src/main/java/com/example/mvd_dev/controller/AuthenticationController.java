package com.example.mvd_dev.controller;

import com.example.mvd_dev.modeldto.AuthenticationResponseDto;
import com.example.mvd_dev.modeldto.SignInRequest;
import com.example.mvd_dev.modeldto.SignUpRequest;
import com.example.mvd_dev.service.AuthenticationService;
import com.example.mvd_dev.service.UserService;
import com.example.mvd_dev.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final UserServiceImpl userService;

    @PostMapping("/registration")
    public ResponseEntity<String> register(
            @RequestBody SignUpRequest registrationDto) {

        var user = userService.existsByUsername(registrationDto.getLogin());
        if (user) {
            return ResponseEntity.badRequest().body("Это логин уже используется");
        }


        authenticationService.register(registrationDto);

        return ResponseEntity.ok("Регистрация прошла успешно");
    }

    @PostMapping("/singin")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody SignInRequest request) {

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<AuthenticationResponseDto> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {

        return authenticationService.refreshToken(request, response);
    }
}
