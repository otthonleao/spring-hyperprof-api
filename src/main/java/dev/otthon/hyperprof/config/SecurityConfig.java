package dev.otthon.hyperprof.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(customizer -> customizer.anyRequest().permitAll()) // PERMITE O ACESSO EM TODAS AS ROTAS SEM ESTÁ AUTENTICADO
                .csrf(customizer -> customizer.disable())
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // CONFIG DE GERENCIAMENTO DE SESSÃO DA APLICAÇÃO
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(authenticationEntryPoint)); // CUSTOMIZAR OS ERROS
        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return (AuthenticationEntryPoint) authenticationConfiguration.getAuthenticationManager();
    }

}
