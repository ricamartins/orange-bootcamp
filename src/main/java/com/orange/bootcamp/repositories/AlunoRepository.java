package com.orange.bootcamp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orange.bootcamp.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	Optional<Aluno> findByEmail(String email);
}
