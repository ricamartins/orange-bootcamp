package com.orange.bootcamp.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.orange.bootcamp.controllers.dto.TokenResponse;
import com.orange.bootcamp.models.Aluno;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${orange.jwt.expiration}")
	private String expiration;
	
	@Value("${orange.jwt.secret}")
	private String secret;
	
	public TokenResponse gerarToken(Authentication authentication) {
		Aluno aluno = (Aluno) authentication.getPrincipal();
		Date agora = new Date();
		Date dataExpiracao = new Date(agora.getTime() + Long.parseLong(expiration));
		
		String token = Jwts.builder()
				.setIssuer("Orange Bootcamp")
				.setSubject(aluno.getId().toString())
				.setIssuedAt(agora)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		
		return new TokenResponse("Bearer", token);
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			return true;			
		} catch (Exception e) {
			return false;
		}		
	}

	public Long getAlunoId(String token) {
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}
	
	

}
