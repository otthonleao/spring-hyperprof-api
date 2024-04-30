package dev.otthon.hyperprof.core.services.token.providers.jjwt;

import dev.otthon.hyperprof.core.models.TokenInvalido;
import dev.otthon.hyperprof.core.repositories.TokenInvalidoRepository;
import dev.otthon.hyperprof.core.services.token.TokenService;
import dev.otthon.hyperprof.core.services.token.TokenServiceException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class JjwtTokenService implements TokenService {

    private final JjwtConfigProperties configProperties;
    private final TokenInvalidoRepository tokenInvalidoRepository;

    @Override
    public String gerarAccessToken(String subject) {
        return gerarToken(
                subject,
                configProperties.getAccessTokenExpirationInSeconds(),
                configProperties.getAccessTokenSiginKey()
        );
    }

    @Override
    public String getSubjectDoAccessToken(String accessToken) {
        return getClaims(accessToken, configProperties.getAccessTokenSiginKey()).getSubject();
    }

    @Override
    public String gerarRefreshToken(String subject) {
        return gerarToken(
                subject,
                configProperties.getRefreshTokenExpirationInSeconds(),
                configProperties.getRefreshTokenSiginKey()
        );
    }

    @Override
    public String getSubjectDoRefreshToken(String refreshToken) {
        return getClaims(refreshToken, configProperties.getRefreshTokenSiginKey()).getSubject();
    }

    @Override
    public void invalidarTokens(String... tokens) {
        // Transformando as Strings em Token inválido
        var tokensInvalidos = Stream.of(tokens)
                .map(token -> TokenInvalido.builder().token(token).build())
                .toList();

        // Salvando os tokes invalidos da lista no banco
        tokenInvalidoRepository.saveAll(tokensInvalidos);
    }

    private String gerarToken(String subject, Long expirationInSeconds, String siginKey) {
        var dataHoraAtual = Instant.now();
        var dataHoraExpiracao = dataHoraAtual.plusSeconds(expirationInSeconds);

        return Jwts.builder()
                .setClaims(new HashMap<String, Object>())
                .setSubject(subject)
                .setIssuedAt(Date.from(dataHoraAtual))
                .setExpiration(Date.from(dataHoraExpiracao))
                .signWith(Keys.hmacShaKeyFor(siginKey.getBytes()))
                .compact();
    }

    private Claims getClaims(String token, String sigingKey) {
        // Verifica se o token está inválido antes de dar permissão
        if (tokenInvalidoRepository.existsByToken(token)) {
            throw new TokenServiceException("ERROR: TOKEN INVÁLIDO");
        }
        try {
            return Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(sigingKey.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new TokenServiceException(e.getLocalizedMessage());
        }
    }
}
