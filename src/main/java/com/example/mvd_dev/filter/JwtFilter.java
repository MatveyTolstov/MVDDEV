package com.example.mvd_dev.filter;

import com.example.mvd_dev.service.JwtService;
import com.example.mvd_dev.service.UserService;
import com.example.mvd_dev.service.UserServiceImpl;
import io.jsonwebtoken.JwtException;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserServiceImpl userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // 1. Проверка наличия токена
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.debug("No Bearer token found in Authorization header");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String token = authHeader.substring(7);
            log.debug("Processing JWT token: {}", token);

            // 2. Извлечение username из токена
            final String username = jwtService.extractUsername(token);
            if (username == null) {
                log.warn("Failed to extract username from token");
                sendError(response, "Invalid token structure", HttpStatus.UNAUTHORIZED);
                return;
            }

            // 3. Загрузка UserDetails
            UserDetails userDetails = userService.loadUserByUsername(username);
            log.debug("Loaded user details: {}", userDetails.getUsername());

            // 4. Валидация токена
            if (!jwtService.isValid(token, userDetails)) {
                log.warn("Token validation failed for user: {}", username);
                sendError(response, "Token validation failed", HttpStatus.UNAUTHORIZED);
                return;
            }

            // 5. Установка аутентификации
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);
            log.debug("Successfully authenticated user: {} with roles: {}",
                    userDetails.getUsername(), userDetails.getAuthorities());

            // 6. Продолжение цепочки фильтров
            filterChain.doFilter(request, response);

        } catch (JwtException e) {
            log.error("JWT processing error", e);
            sendError(response, "Token processing error: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (UsernameNotFoundException e) {
            log.error("User not found", e);
            sendError(response, "User not found", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error("Unexpected error during authentication", e);
            sendError(response, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void sendError(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.getWriter().write(
                String.format("{\"error\": \"%s\", \"message\": \"%s\"}", status.getReasonPhrase(), message)
        );
        log.debug("Sent error response: {} - {}", status.value(), message);
    }
}