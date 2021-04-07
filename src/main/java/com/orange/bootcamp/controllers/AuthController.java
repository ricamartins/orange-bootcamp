package com.orange.bootcamp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.bootcamp.controllers.dto.LoginDTO;
import com.orange.bootcamp.controllers.dto.TokenResponse;
import com.orange.bootcamp.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthenticationManager authManager;
	private JwtService jwt;
	
	public AuthController(AuthenticationManager authManager, JwtService jwt) {
		super();
		this.authManager = authManager;
		this.jwt = jwt;
	}

	@PostMapping
	public ResponseEntity<TokenResponse> autenticar(@RequestBody LoginDTO login) {
		UsernamePasswordAuthenticationToken credenciais = login.converter();
	
		try {
			Authentication authentication = authManager.authenticate(credenciais);
			TokenResponse token = jwt.gerarToken(authentication);
			return ResponseEntity.ok(token);
		} catch(AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
