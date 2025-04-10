package com.example.mvd_dev.service;


import com.example.mvd_dev.model.UserEntity;
import com.example.mvd_dev.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Locale;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${security.jwt.secret_key}")
    private String secretKey;

    @Value("${security.jwt.access_token_expiration}")
    private long accessTokenExpiration;

    @Value("${security.jwt.refresh_token_expiration}")
    private long refreshTokenExpiration;

    private final TokenRepository tokenRepository;

    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    public boolean isValid(String token, UserDetails user) {

        String username = extractUsername(token);

        boolean isValidToken = tokenRepository.findByAccessToken(token)
                .map(t -> !t.isLoggedOut()).orElse(false);

        return username.equals(user.getUsername())
                && isAccessTokenExpired(token)
                && isValidToken;
    }


    public boolean isValidRefresh(String token, UserEntity user) {

        String username = extractUsername(token);

        boolean isValidRefreshToken = tokenRepository.findByRefreshToken(token)
                .map(t -> !t.isLoggedOut()).orElse(false);

        return username.equals(user.getUsername())
                && isAccessTokenExpired(token)
                && isValidRefreshToken;
    }


    private boolean isAccessTokenExpired(String token) {
        return !extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }


    private Claims extractAllClaims(String token) {

        JwtParserBuilder parser = Jwts.parser();

        parser.verifyWith(getSgningKey());

        return parser.build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public String generateAccessToken(UserEntity user) {

        return generateToken(user, accessTokenExpiration);
    }


    public String generateRefreshToken(UserEntity user) {

        return generateToken(user, refreshTokenExpiration);
    }


    private String generateToken(UserEntity user, long expiryTime) {
        JwtBuilder builder = Jwts.builder()
                .subject(user.getUsername())
                .claim("role", user.getRole().getRoleName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiryTime))
                .signWith(getSgningKey());

        return builder.compact();
    }


    private SecretKey getSgningKey() {

        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
