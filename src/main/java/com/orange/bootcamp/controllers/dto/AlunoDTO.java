package com.orange.bootcamp.controllers.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orange.bootcamp.models.Aluno;

public class AlunoDTO {

	private Long id;
	
	@NotEmpty @Size(max=30)
	private String nome;
	
	@NotEmpty @Size(max=30)
	private String email;
	
	@NotEmpty
	private String senha;
	
	@NotNull @Min(18)
	private Integer idade;

	public AlunoDTO() {}
	
	public AlunoDTO(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.email = aluno.getEmail();
		//this.senha = aluno.getSenha();
		this.idade = aluno.getIdade();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

}
