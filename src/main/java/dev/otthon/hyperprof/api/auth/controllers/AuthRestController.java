package dev.otthon.hyperprof.api.auth.controllers;

import dev.otthon.hyperprof.api.auth.dtos.LoginRequest;
import dev.otthon.hyperprof.api.auth.dtos.LoginResponse;
import dev.otthon.hyperprof.api.auth.dtos.RefreshRequest;
import dev.otthon.hyperprof.api.auth.services.AuthService;
import dev.otthon.hyperprof.api.common.routes.ApiRoutes;
import dev.otthon.hyperprof.api.common.utils.JwtBearerDefaults;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping(ApiRoutes.LOGIN)
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping(ApiRoutes.REFRESH)
    public LoginResponse refresh(@RequestBody @Valid RefreshRequest refreshRequest) {
        return authService.refresh(refreshRequest);
    }

    @PostMapping(ApiRoutes.LOGOUT)
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void logout(@RequestHeader String authorization, @RequestBody @Valid RefreshRequest refreshRequest) {
        var token = authorization.substring(JwtBearerDefaults.TOKEN_TYPE.length());
        authService.logout(token, refreshRequest);
    }
}
