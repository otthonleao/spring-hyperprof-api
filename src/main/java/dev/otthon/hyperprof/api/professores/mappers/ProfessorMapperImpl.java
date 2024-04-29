package dev.otthon.hyperprof.api.professores.mappers;

import dev.otthon.hyperprof.api.professores.dtos.ProfessorRequest;
import dev.otthon.hyperprof.api.professores.dtos.ProfessorResponse;
import dev.otthon.hyperprof.core.models.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapperImpl implements ProfessorMapper {

    @Override
    public ProfessorResponse toProfessorResponse(Professor professor) {

        if (professor == null) {
            return null;
        }

        /* FORMA NORMAL DE SETAR OS DADOS */
//        var professorResponse = new ProfessorResponse();
//        professorResponse.setId(professor.getId());
//        professorResponse.setNome(professor.getNome());
//        professorResponse.setEmail(professor.getEmail());
//        professorResponse.setIdade(professor.getIdade());
//        professorResponse.setDescricao(professor.getDescricao());
//        professorResponse.setValorHora(professor.getValorHora());
//        professorResponse.setFotoPerfil(professor.getFotoPerfil());
//        professorResponse.setCreatedAt(professor.getCreatedAt());
//        professorResponse.setUpdatedAt(professor.getUpdatedAt());
//
//        return professorResponse;

        /* USANDO O PADRÃO BUILDER */
        return ProfessorResponse.builder()
                .id(professor.getId())
                .nome(professor.getNome())
                .email(professor.getEmail())
                .idade(professor.getIdade())
                .descricao(professor.getDescricao())
                .valorHora(professor.getValorHora())
                .fotoPerfil(professor.getFotoPerfil())
                .createdAt(professor.getCreatedAt())
                .updatedAt(professor.getUpdatedAt())
                .build();
    }

    @Override
    public Professor toProfessor(ProfessorRequest professorRequest) {
        if (professorRequest == null) {
            return null;
        }

        return Professor.builder()
                .nome(professorRequest.getNome())
                .email(professorRequest.getEmail())
                .idade(professorRequest.getIdade())
                .descricao(professorRequest.getDescricao())
                .valorHora(professorRequest.getValorHora())
                .password(professorRequest.getPassword())
                .build();
    }
}
