package dev.otthon.hyperprof.api.professores.services;

import dev.otthon.hyperprof.api.professores.dtos.ProfessorRequest;
import dev.otthon.hyperprof.api.professores.dtos.ProfessorResponse;
import dev.otthon.hyperprof.api.professores.mappers.ProfessorMapper;
import dev.otthon.hyperprof.core.exceptions.ProfessorNotFoundException;
import dev.otthon.hyperprof.core.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorMapper professorMapper;
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;

    /* ESSE CONSTRUTOR PODE SER SUBSTITUÍDO PELA ANNOTATION @RequiredArgsConstructot SOBRE A CLASSE */
    public ProfessorServiceImpl(ProfessorMapper professorMapper, ProfessorRepository professorRepository, PasswordEncoder passwordEncoder) {
        this.professorMapper = professorMapper;
        this.professorRepository = professorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ProfessorResponse> buscarProfessores(String descricao) {
        return professorRepository.findByDescricaoContaining(descricao)
                .stream()
                .map(professor -> professorMapper.toProfessorResponse(professor))
                .toList();
    }

    @Override
    public ProfessorResponse buscarProfessorPorId(Long professorId) {
        return professorRepository.findById(professorId)
                .map(professorMapper::toProfessorResponse)
                .orElseThrow(ProfessorNotFoundException::new);
    }

    @Override
    public ProfessorResponse cadastrarProfessor(ProfessorRequest professorRequest) {
        var professorParaCadastrar = professorMapper.toProfessor(professorRequest);
        professorParaCadastrar.setPassword(passwordEncoder.encode(professorParaCadastrar.getPassword()));
        var professorCadastrado = professorRepository.save(professorParaCadastrar);
        return professorMapper.toProfessorResponse(professorCadastrado);
    }
}
