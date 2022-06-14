package com.example.mentorsstudents.service;

import com.example.mentorsstudents.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtTokenUtil {

    private String jwtAccessSecret;
    private String jwtRefreshSecret;
    private Integer jwtExpirationAccessTokenInMin;
    private Integer jwtExpirationRefreshTokenInMin;

    public JwtTokenUtil(@Value("${jwt.secret.access}") String jwtAccessSecret,
                        @Value("${jwt.secret.refresh}") String jwtRefreshSecret,
                        @Value("${jwt.expiration.access}") int jwtExpirationAccessTokenInMin,
                        @Value("${jwt.expiration.refresh}") int jwtExpirationRefreshTokenInMin) {
        this.jwtAccessSecret = jwtAccessSecret;
        this.jwtRefreshSecret = jwtRefreshSecret;
        this.jwtExpirationAccessTokenInMin = jwtExpirationAccessTokenInMin;
        this.jwtExpirationRefreshTokenInMin = jwtExpirationRefreshTokenInMin;
    }

    public String generateAccessToken(@NonNull User user) {
        final Instant accessExpirationInstant = LocalDateTime
                .now()
                .plusMinutes(jwtExpirationAccessTokenInMin)
                .atZone(ZoneId.systemDefault())
                .toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        final String accessToken = Jwts.builder()
                .setSubject(String.format("%s", user.getEmail()))
                .setExpiration(accessExpiration)
                .signWith(SignatureAlgorithm.HS512, jwtAccessSecret)
                .claim("User role", user.getUserRole())
                .compact();
       return accessToken;
    }

    public String generateRefreshToken(@NonNull User user) {
        final Instant refreshExpirationInstant = LocalDateTime
                .now()
                .plusMinutes(jwtExpirationRefreshTokenInMin)
                .atZone(ZoneId.systemDefault())
                .toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        final String refreshToken = Jwts.builder()
                .setSubject(String.format("%s", user.getEmail()))
                .setExpiration(refreshExpiration)
                .signWith(SignatureAlgorithm.HS512, jwtRefreshSecret)
                .claim("User role", user.getUserRole())
                .compact();
        return refreshToken;
    }

    public boolean validateAccessToken(@NonNull String token) {
        return validateToken(token, jwtAccessSecret);
    }

    public boolean validateRefreshToken(@NonNull String token) {
        return validateToken(token, jwtRefreshSecret);
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtAccessSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String getLoginFromRefreshToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtRefreshSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, jwtAccessSecret);
    }

    public Claims getRefreshClaims(@NonNull String token) {
        return getClaims(token, jwtRefreshSecret);
    }

    private boolean validateToken(@NonNull String token, @NonNull String secret) {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return true;
    }

    private Claims getClaims(@NonNull String token, @NonNull String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
