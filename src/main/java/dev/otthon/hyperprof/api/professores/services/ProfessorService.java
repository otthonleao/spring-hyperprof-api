package dev.otthon.hyperprof.api.professores.services;

import dev.otthon.hyperprof.api.professores.dtos.ProfessorResponse;

import java.util.List;

public interface ProfessorService {

    List<ProfessorResponse> buscarProfessores(String descricao);
}
