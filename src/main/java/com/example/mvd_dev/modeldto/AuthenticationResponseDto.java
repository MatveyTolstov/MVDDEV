package com.example.mvd_dev.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AuthenticationResponseDto {
    private final String accessToken;

    private final String refreshToken;
}
