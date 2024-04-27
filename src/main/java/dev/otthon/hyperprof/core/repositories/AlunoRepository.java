package dev.otthon.hyperprof.core.repositories;

import dev.otthon.hyperprof.core.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
