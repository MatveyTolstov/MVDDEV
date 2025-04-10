package com.example.mvd_dev.modeldto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private String errorCode;
    private LocalDateTime timestamp;
    private String path;
}
