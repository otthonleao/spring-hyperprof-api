package dev.otthon.hyperprof.api.alunos.mappers;

import dev.otthon.hyperprof.api.alunos.dtos.AlunoRequest;
import dev.otthon.hyperprof.api.alunos.dtos.AlunoResponse;
import dev.otthon.hyperprof.core.models.Aluno;

public interface AlunoMapper {

    AlunoResponse toAlunoResponse(Aluno aluno);
    Aluno toAluno(AlunoRequest alunoRequest);

}
