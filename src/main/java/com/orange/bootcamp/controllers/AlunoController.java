package com.orange.bootcamp.controllers;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.orange.bootcamp.controllers.dto.AlunoDTO;
import com.orange.bootcamp.models.Aluno;
import com.orange.bootcamp.repositories.AlunoRepository;
import com.orange.bootcamp.services.EmailServiceInterface;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	private AlunoRepository alunoRepository;
	private PasswordEncoder encoder;
	private EmailServiceInterface emailService;
	
	public AlunoController(AlunoRepository alunoRepository, EmailServiceInterface emailService) {
		super();
		this.alunoRepository = alunoRepository;
		this.encoder = new BCryptPasswordEncoder();
		this.emailService = emailService;
	}

	@GetMapping
	public ResponseEntity<Page<AlunoDTO>> listarAlunos(Pageable paginacao) {
		Page<Aluno> alunos = alunoRepository.findAll(paginacao);
		emailService.enviar("Valeu pela busca!");
		
		return ResponseEntity.ok(alunos.map(AlunoDTO::new));
	}
	
	@GetMapping("/{id}")
	@Cacheable("detalhesAluno")
	public ResponseEntity<AlunoDTO> detalhesAluno(@PathVariable Long id) {
		Optional<Aluno> aluno = alunoRepository.findById(id);
		if (aluno.isPresent())
			return ResponseEntity.ok(new AlunoDTO(aluno.get()));
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AlunoDTO> cadastrarAluno(@RequestBody @Valid AlunoDTO alunoDTO, UriComponentsBuilder uriBuilder) {
		Aluno aluno = alunoRepository.save(new Aluno(alunoDTO));
		aluno.setSenha(encoder.encode(aluno.getSenha()));
		URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).body(new AlunoDTO(aluno));
	}
}
