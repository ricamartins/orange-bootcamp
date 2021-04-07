package com.orange.bootcamp.controllers.dto;

import java.time.LocalDateTime;

import com.orange.bootcamp.models.Turma;

public class TurmaResponse {

	private Long id;
	
	private String nome;
	
	private String turno;
	
	private LocalDateTime criadoEm;

	public TurmaResponse(Turma turma) {
		this.id = turma.getId();
		this.nome = turma.getNome();
		this.turno = turma.getTurno();
		this.criadoEm = turma.getCriadoEm();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getTurno() {
		return turno;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

}
