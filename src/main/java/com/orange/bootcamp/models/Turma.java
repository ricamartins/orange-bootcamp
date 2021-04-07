package com.orange.bootcamp.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="tb_turmas")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String turno;
	
	@CreationTimestamp
	private LocalDateTime criadoEm = LocalDateTime.now();

	public Turma() {}
	
	public Turma(String nome, String turno) {
		this.nome = nome;
		this.turno = turno;
	}

	public Turma(Long id, @NotBlank String nome, @NotBlank String turno) {
		this.id = id;
		this.nome = nome;
		this.turno = turno;
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
