package dev.otthon.hyperprof.core.repositories;

import dev.otthon.hyperprof.core.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
