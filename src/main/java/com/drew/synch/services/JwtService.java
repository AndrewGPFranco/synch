package com.drew.synch.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.drew.synch.entities.User;
import com.drew.synch.enums.RoleType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    @Value("${synch.secretkey.jwt}")
    private String secretKey;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("synch")
                    .withSubject(user.getNickname())
                    .withClaim("id", user.getId())
                    .withClaim("email", user.getEmail())
                    .withClaim("roles", user.getRoles().stream().map(RoleType::getRole).toList())
                    .withExpiresAt(tokenExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new JWTCreationException("Erro ao criar o token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("synch")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Ocorreu um erro ao validar o token", e);
        }
    }

    private Instant tokenExpirationDate() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }

}
