package com.orange.bootcamp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.orange.bootcamp.repositories.AlunoRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private AuthenticationService service;
	private AlunoRepository repository;
	private JwtService jwt;
	
	public SecurityConfiguration(AuthenticationService service, AlunoRepository repository,
			JwtService jwt) {
		this.service = service;
		this.repository = repository;
		this.jwt = jwt;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/alunos").permitAll()
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
			.antMatchers(HttpMethod.GET, "/alunos").permitAll()
			.antMatchers(HttpMethod.POST, "/turmas").permitAll()
			.antMatchers(HttpMethod.PUT, "/turmas/*").permitAll()
			.antMatchers(HttpMethod.GET, "/turmas").permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(new JwtAuthenticationFilter(jwt, repository), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
}
