package com.example.mvd_dev.repository;

import com.example.mvd_dev.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = """
    SELECT * FROM token t 
    INNER JOIN users u ON t.user_id = u.id_user
    WHERE u.id_user = :user_id AND t.is_logged_out = false
    """, nativeQuery = true)
    List<Token> findAllAccessTokenByUser (@Param("user_id") Long userId);

    Optional<Token> findByAccessToken(String accessToken);

    Optional<Token> findByRefreshToken(String refreshToken);
}
