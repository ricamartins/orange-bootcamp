package com.orange.bootcamp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.bootcamp.controllers.dto.TurmaRequest;
import com.orange.bootcamp.controllers.dto.TurmaResponse;
import com.orange.bootcamp.repositories.TurmaRepository;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

	private TurmaRepository repository;

	public TurmaController(TurmaRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	public ResponseEntity<TurmaResponse> post(@RequestBody @Valid TurmaRequest request) {
		TurmaResponse response = new TurmaResponse(repository.save(request.convert()));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<TurmaResponse>> getAll() {
		return ResponseEntity.ok(repository.findAll().stream().map(TurmaResponse::new).collect(Collectors.toList()));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TurmaResponse> put(@PathVariable Long id, @RequestBody @Valid TurmaRequest request) {
		TurmaResponse response = new TurmaResponse(repository.save(request.convert(id)));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
}
