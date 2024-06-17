package br.com.erudio.apijavaservice.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.expiration}")
    private Long expration;

    @Value("${jwt.secret}")
    private String key;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expration))
                .signWith(SignatureAlgorithm.HS512, key.getBytes())
                .compact();
    }
}
