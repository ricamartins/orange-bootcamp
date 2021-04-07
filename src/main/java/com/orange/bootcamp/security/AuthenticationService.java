package com.orange.bootcamp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.orange.bootcamp.models.Aluno;
import com.orange.bootcamp.repositories.AlunoRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private AlunoRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Aluno> aluno = repository.findByEmail(email);
		
		if (aluno.isPresent())
			return aluno.get();
		
		throw new UsernameNotFoundException("Usuário não encontrado");
	}

}
