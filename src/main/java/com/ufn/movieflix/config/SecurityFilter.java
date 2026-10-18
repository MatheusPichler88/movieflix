package com.ufn.movieflix.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

//Filtro que roda uma vez a cada requisição, lê o token do header e, se for válido, autentica o usuário
@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest requisicao, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //O token vem no header Authorization no formato "Bearer <token>"
        String authorizationHeader = requisicao.getHeader("Authorization");

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());

            //Se o token for válido, devolve os dados do usuário que estavam dentro dele
            Optional<JWTUserData> jwtUserData = tokenService.validarToken(token);
            if (jwtUserData.isPresent()) {
                JWTUserData userData = jwtUserData.get();

                //Marca o usuário como autenticado nessa requisição (sem credenciais e sem perfis/permissões por enquanto)
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userData, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        //Segue para o próximo filtro, se não autenticou a rota protegida vai ser barrada pelo SecurityConfig
        filterChain.doFilter(requisicao, response);
    }
}
