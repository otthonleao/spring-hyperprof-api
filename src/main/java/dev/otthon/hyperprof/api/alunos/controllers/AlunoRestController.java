package dev.otthon.hyperprof.api.alunos.controllers;

import dev.otthon.hyperprof.api.alunos.dtos.AlunoRequest;
import dev.otthon.hyperprof.api.alunos.dtos.AlunoResponse;
import dev.otthon.hyperprof.api.alunos.services.AlunoService;
import dev.otthon.hyperprof.api.common.routes.ApiRoutes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AlunoRestController {

    private final AlunoService alunoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(ApiRoutes.CADASTRAR_ALUNO)
    public AlunoResponse cadastrarAluno(@RequestBody @Valid AlunoRequest alunoRequest, @PathVariable Long professorId) {
        return alunoService.cadastrarAluno(alunoRequest, professorId);
    }
}
