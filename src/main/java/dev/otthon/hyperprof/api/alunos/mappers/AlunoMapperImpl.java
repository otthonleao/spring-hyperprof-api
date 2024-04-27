package dev.otthon.hyperprof.api.alunos.mappers;

import dev.otthon.hyperprof.api.alunos.dtos.AlunoRequest;
import dev.otthon.hyperprof.api.alunos.dtos.AlunoResponse;
import dev.otthon.hyperprof.core.models.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapperImpl implements AlunoMapper {

    @Override
    public Aluno toAluno(AlunoRequest alunoRequest) {
        if (alunoRequest == null) {
            return null;
        }

        return Aluno.builder()
                .nome(alunoRequest.getNome())
                .email(alunoRequest.getEmail())
                .dataAula(alunoRequest.getDataAula())
                .build();
    }

    @Override
    public AlunoResponse toAlunoResponse(Aluno aluno) {
        if (aluno == null) {
            return null;
        }

        return AlunoResponse.builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .email(aluno.getEmail())
                .dataAula(aluno.getDataAula())
                .createdAt(aluno.getCreatedAt())
                .updatedAt(aluno.getUpdatedAt())
                .build();
    }
}
