package dev.otthon.hyperprof.core.repositories;

import dev.otthon.hyperprof.core.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findByDescricaoContaining(String descricao);
    boolean existsByEmail(String email);
    Optional<Professor> findByEmail(String email); //Buscar professor com base no email

}
