package com.example.mvd_dev.service;

import com.example.mvd_dev.model.Role;
import com.example.mvd_dev.model.Roles;
import com.example.mvd_dev.model.Token;
import com.example.mvd_dev.model.UserEntity;
import com.example.mvd_dev.modeldto.AuthenticationResponseDto;
import com.example.mvd_dev.modeldto.SignInRequest;
import com.example.mvd_dev.modeldto.SignUpRequest;
import com.example.mvd_dev.repository.RoleRepository;
import com.example.mvd_dev.repository.TokenRepository;
import com.example.mvd_dev.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;

    private final RoleRepository roleRepository;


    public void register(SignUpRequest request) {

        Role role = roleRepository.findRoleByRoleName(Roles.ROLE_USER)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setRoleName(Roles.ROLE_USER);
                    return roleRepository.save(newRole);
                });


        UserEntity user = new UserEntity();
        user.setLogin(request.getLogin());
        user.setNumber(request.getNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        userRepository.save(user);
    }

    private void revokeAllToken(UserEntity user) {

        List<Token> validTokens = tokenRepository.findAllAccessTokenByUser(user.getIdUser());

        if(!validTokens.isEmpty()){
            validTokens.forEach(t ->{
                t.setLoggedOut(true);
            });
        }

        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(String accessToken, String refreshToken, UserEntity user) {

        Token token = new Token();

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);

        tokenRepository.save(token);
    }

    public AuthenticationResponseDto authenticate(SignInRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        UserEntity user = userRepository.findByLogin(request.getLogin())
                .orElseThrow();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllToken(user);

        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponseDto(accessToken, refreshToken);
    }

    public ResponseEntity<AuthenticationResponseDto> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authorizationHeader.substring(7);
        String login = jwtService.extractUsername(token);

        UserEntity user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));

        if (jwtService.isValidRefresh(token, user)) {

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllToken(user);

            saveUserToken(accessToken, refreshToken, user);

            return new ResponseEntity<>(new AuthenticationResponseDto(accessToken, refreshToken), HttpStatus.OK);

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
