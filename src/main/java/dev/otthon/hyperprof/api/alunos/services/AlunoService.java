package dev.otthon.hyperprof.api.alunos.services;

import dev.otthon.hyperprof.api.alunos.dtos.AlunoRequest;
import dev.otthon.hyperprof.api.alunos.dtos.AlunoResponse;

public interface AlunoService {

    AlunoResponse cadastrarAluno(AlunoRequest alunoRequest, Long professorId);

}
