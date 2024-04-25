package dev.otthon.hyperprof.api.professores.mappers;

import dev.otthon.hyperprof.api.professores.dtos.ProfessorResponse;
import dev.otthon.hyperprof.core.models.Professor;

public interface ProfessorMapper {

    ProfessorResponse toProfessorResponse(Professor professor);

}
