package dev.otthon.hyperprof.core.services.token.providers.jjwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "dev.otthon.hyperprof.core.services.token.jjwt")
public class JjwtConfigProperties {
    private String accessTokenSiginKey;
    private Long accessTokenExpirationInSeconds;
    private String refreshTokenSiginKey;
    private Long refreshTokenExpirationInSeconds;
}
