package com.orange.bootcamp.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.orange.bootcamp.models.Aluno;
import com.orange.bootcamp.repositories.AlunoRepository;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private JwtService jwt;
	private AlunoRepository repository;
	
	public JwtAuthenticationFilter(JwtService jwt, AlunoRepository repository) {
		super();
		this.jwt = jwt;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		
		if (jwt.isTokenValido(token))
			autenticarCliente(token);
		
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long id = jwt.getAlunoId(token);
		Optional<Aluno> aluno = repository.findById(id);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(aluno.get(), null, aluno.get().getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (header == null || header.isBlank() || !header.startsWith("Bearer "))
			return null;
			
		return header.substring(7, header.length());
	}

}
