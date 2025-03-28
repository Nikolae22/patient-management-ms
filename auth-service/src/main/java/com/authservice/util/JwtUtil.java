package com.authservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


//coponent server per autowired le classi
@Component
public class JwtUtil {


    private final Key securityKey;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes= Base64.getDecoder().decode(
                secret.getBytes(StandardCharsets.UTF_8)
        );
        this.securityKey= Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email,String role){
        return Jwts.builder()
                // e il sogetto a cui si riferisce
                .subject(email)
                //custom properti che si aggiunge a jwt
                .claim("role",role)
                //determ if is valid or not
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 *60*10))//10 hourse
                .signWith(securityKey)
                //crea il jwt token
                .compact();
    }
}
