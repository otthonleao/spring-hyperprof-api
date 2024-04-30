package dev.otthon.hyperprof.api.professores.controllers;

import dev.otthon.hyperprof.api.common.routes.ApiRoutes;
import dev.otthon.hyperprof.api.professores.dtos.ProfessorResponse;
import dev.otthon.hyperprof.api.professores.services.MeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeRestController {

    private final MeService meService;

    @GetMapping(ApiRoutes.PROFESSOR_LOGADO)
    @PreAuthorize("isAuthenticated()")
    public ProfessorResponse buscarProfessorLogado() {
        return meService.buscarProfessorLogado();
    }
}
