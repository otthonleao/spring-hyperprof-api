package dev.otthon.hyperprof.api.common.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.otthon.hyperprof.api.common.dtos.ErrorResponse;
import dev.otthon.hyperprof.api.common.utils.JwtBearerDefaults;
import dev.otthon.hyperprof.core.services.token.TokenService;
import dev.otthon.hyperprof.core.services.token.TokenServiceException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AccessTokenRequestFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var token = "";
            var email = "";
            var authorizationHeader = request.getHeader("Authorization"); // Acessando o Header da requisição e pegano o valor de Auth

            if (isTokenPresente(authorizationHeader)) {
                token = authorizationHeader.substring(JwtBearerDefaults.TOKEN_TYPE.length());
                email = tokenService.getSubjectDoAccessToken(token);
            }

            if (isEmailNotInContext(email)) {
                setAuthentication(request, email);
            }

            filterChain.doFilter(request, response);
        } catch (TokenServiceException e) {
            var status = HttpStatus.UNAUTHORIZED;
            var body = ErrorResponse.builder()
                    .status(status.value())
                    .error(status.getReasonPhrase())
                    .timestamp(LocalDateTime.now())
                    .message(e.getLocalizedMessage())
                    .cause(e.getClass().getSimpleName())
                    .build();
            // Montando o Response Error
            var json = objectMapper.writeValueAsString(body);
            response.setStatus(status.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        }
    }

    private void setAuthentication(HttpServletRequest request, String email) {
        var userDetails = userDetailsService.loadUserByUsername(email);
        var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private static boolean isEmailNotInContext(String email) {
        var isEmailNotInContext = email != null && !email.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null;
        return isEmailNotInContext;
    }

    private static boolean isTokenPresente(String authorizationHeader) {
        var isTokenPresente = authorizationHeader != null && authorizationHeader.startsWith(JwtBearerDefaults.TOKEN_TYPE);
        return isTokenPresente;
    }
}
