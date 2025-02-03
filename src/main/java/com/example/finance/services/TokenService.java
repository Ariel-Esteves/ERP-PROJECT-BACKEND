package com.example.finance.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.finance.models.entities.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
	@Value("${api.security.token.secret}")
	private String secret;

	public String generateToken(UserEntity user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create().withIssuer("Ariel").withSubject(user.getLogin()).withExpiresAt(generateExpirationDate()).sign(algorithm);
			return token;
		} catch(JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar token", e);
		}
	}

	public String ValidateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm).withIssuer("Ariel").build().verify(token).getSubject();

		} catch(JWTVerificationException e) {
			throw new RuntimeException("Token inválido", e);
		}
	}

	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);
	}
}
