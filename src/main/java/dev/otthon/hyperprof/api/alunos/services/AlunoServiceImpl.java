package dev.otthon.hyperprof.api.alunos.services;

import dev.otthon.hyperprof.api.alunos.dtos.AlunoRequest;
import dev.otthon.hyperprof.api.alunos.dtos.AlunoResponse;
import dev.otthon.hyperprof.api.alunos.mappers.AlunoMapper;
import dev.otthon.hyperprof.core.exceptions.ProfessorNotFoundException;
import dev.otthon.hyperprof.core.repositories.AlunoRepository;
import dev.otthon.hyperprof.core.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService{

    private final AlunoMapper alunoMapper;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public AlunoResponse cadastrarAluno(AlunoRequest alunoRequest, Long professorId) {
        var professor = professorRepository.findById(professorId).orElseThrow(ProfessorNotFoundException::new);
        var alunoParaCadastrar = alunoMapper.toAluno(alunoRequest);
        alunoParaCadastrar.setProfessor(professor); // Associando Professor ao Aluno
        var alunoCadastrado = alunoRepository.save(alunoParaCadastrar);

        return alunoMapper.toAlunoResponse(alunoCadastrado);
    }
}
