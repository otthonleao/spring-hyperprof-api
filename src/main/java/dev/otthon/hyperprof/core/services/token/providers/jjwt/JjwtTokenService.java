package dev.otthon.hyperprof.core.services.token.providers.jjwt;

import dev.otthon.hyperprof.core.services.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JjwtTokenService implements TokenService {

    private final JjwtConfigProperties configProperties;

    @Override
    public String gerarAccessToken(String subject) {
        return "";
    }

    @Override
    public String getSubjectDoAccessToken(String accessToken) {
        return "";
    }

    @Override
    public String gerarRefreshToken(String subject) {
        return "";
    }

    @Override
    public String getSubjectDoRefreshToken(String refreshToken) {
        return "";
    }

    @Override
    public void invalidarTokens(String... tokens) {

    }
}
