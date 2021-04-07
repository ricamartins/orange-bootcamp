package com.orange.bootcamp.controllers.dto;

import javax.validation.constraints.NotBlank;

import com.orange.bootcamp.models.Turma;

public class TurmaRequest {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String turno;

	public TurmaRequest(@NotBlank String nome, @NotBlank String turno) {
		this.nome = nome;
		this.turno = turno;
	}

	public Turma convert() {
		return new Turma(nome, turno);
	}

	public Turma convert(Long id) {
		return new Turma(id, nome, turno);
	}

	public String getNome() {
		return nome;
	}
	
	public String getTurno() {
		return turno;
	}
	
}
