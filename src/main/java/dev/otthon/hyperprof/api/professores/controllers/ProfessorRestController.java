package dev.otthon.hyperprof.api.professores.controllers;

import dev.otthon.hyperprof.api.common.routes.ApiRoutes;
import dev.otthon.hyperprof.api.professores.dtos.ProfessorRequest;
import dev.otthon.hyperprof.api.professores.dtos.ProfessorResponse;
import dev.otthon.hyperprof.api.professores.services.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfessorRestController {

    private final ProfessorService professorService;

    @GetMapping(ApiRoutes.BUSCAR_PROFESSORES)
    public List<ProfessorResponse> buscarProfessores(@RequestParam(name = "q", required = false, defaultValue = "") String descricao) {
        return professorService.buscarProfessores(descricao);
    }

    @GetMapping(ApiRoutes.BUSCAR_PROFESSOR_POR_ID)
    public ProfessorResponse buscarProfessorPorId(@PathVariable Long professorId) {
        return professorService.buscarProfessorPorId(professorId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(ApiRoutes.CADASTRAR_PROFESSOR)
    public ProfessorResponse cadastrarProfessor(@RequestBody @Valid ProfessorRequest professorRequest) {
        return professorService.cadastrarProfessor(professorRequest);
    }
}
