package dev.otthon.hyperprof.api.auth.services;

import dev.otthon.hyperprof.api.auth.dtos.LoginRequest;
import dev.otthon.hyperprof.api.auth.dtos.LoginResponse;
import dev.otthon.hyperprof.api.auth.dtos.RefreshRequest;
import dev.otthon.hyperprof.core.exceptions.ProfessorNotFoundException;
import dev.otthon.hyperprof.core.models.AuthenticatedUser;
import dev.otthon.hyperprof.core.repositories.ProfessorRepository;
import dev.otthon.hyperprof.core.services.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final ProfessorRepository professorRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        var authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var professor = ((AuthenticatedUser) authentication.getPrincipal()).getProfessor();

        return LoginResponse.builder()
                .token(tokenService.gerarAccessToken(professor.getEmail()))
                .refreshToken(tokenService.gerarRefreshToken(professor.getEmail()))
                .build();
    }

    @Override
    public LoginResponse refresh(RefreshRequest refreshRequest) {
        var subject = tokenService.getSubjectDoRefreshToken(refreshRequest.getRefreshToken()); // Associando o email do usaário ao refresh
        if (!professorRepository.existsByEmail(subject)) {
            // Caso o email não exista no banco de dados dispara um erro
            throw new ProfessorNotFoundException();
        }
        tokenService.invalidarTokens(refreshRequest.getRefreshToken()); // Invalida o token para que o mesmo não seja utilizado mais de uma vez
        return LoginResponse.builder()
                .token(tokenService.gerarAccessToken(subject))
                .refreshToken(tokenService.gerarRefreshToken(subject))
                .build();
    }

    @Override
    public void logout(String token, RefreshRequest refreshRequest) {
        tokenService.invalidarTokens(token, refreshRequest.getRefreshToken());
    }
}
