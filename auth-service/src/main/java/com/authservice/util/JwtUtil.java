package com.authservice.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


//coponent server per autowired le classi
@Component
public class JwtUtil {


    private final Key securityKey;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes = Base64.getDecoder().decode(
                secret.getBytes(StandardCharsets.UTF_8)
        );
        this.securityKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email, String role) {
        return Jwts.builder()
                // e il sogetto a cui si riferisce
                .subject(email)
                //custom properti che si aggiunge a jwt
                .claim("role", role)
                //determ if is valid or not
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))//10 hourse
                .signWith(securityKey)
                //crea il jwt token
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) securityKey)
                    .build()
                    .parseSignedClaims(token);
//        } catch (SignatureException e) {
//            throw new JwtException("Invalid JWT signature");

        } catch (JwtException e) {
            throw new JwtException("Invalid JWT");

        }
    }
}
