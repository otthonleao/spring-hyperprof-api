package dev.otthon.hyperprof.api.common.filters;

import dev.otthon.hyperprof.api.common.utils.JwtBearerDefaults;
import dev.otthon.hyperprof.core.services.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AccessTokenRequestFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = "";
        var email = "";
        var authorizationHeader = request.getHeader("Authorization"); // Acessando o Header da requisição e pegano o valor de Auth

        var isTokenPresente = authorizationHeader != null && authorizationHeader.startsWith(JwtBearerDefaults.TOKEN_TYPE);
        if (isTokenPresente) {
            token = authorizationHeader.substring(JwtBearerDefaults.TOKEN_TYPE.length());
            email = tokenService.getSubjectDoAccessToken(token);
        }

        var isEmailNotInContext = email != null && !email.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null;
        if (isEmailNotInContext) {
            var userDetails = userDetailsService.loadUserByUsername(email);
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
