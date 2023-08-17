package com.example.demo.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${app.jwt.secret}")
	private String jwtSecret;
	
	@Value("${app.jwt.expiration.ms}")
	private int jwtExpiration;
	
	public String generateJwtToken(Authentication authentication, String nombre) {
		//Es lo que va en el payload, el Subject
		LOG.info("semilla: "+jwtSecret + " tiempo: " + jwtExpiration);
		return Jwts.builder()
				.setSubject(nombre)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+this.jwtExpiration))
				.signWith(SignatureAlgorithm.HS512, this.jwtSecret)
				.compact();
	}
}
