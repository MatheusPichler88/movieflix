package com.ufn.movieflix.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    //Define as regras de segurança da aplicação (quais rotas são públicas e quais exigem autenticação)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) //Desabilita o CSRF, pois a API é stateless e não usa cookies de sessão
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Garante que a aplicação seja stateless (sem sessão)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/movieflix/auth/registro").permitAll() //Libera a rota de cadastro para qualquer usuário
                        .requestMatchers(HttpMethod.POST, "/movieflix/auth/login").permitAll() //Libera a rota de login para qualquer usuário
                        .anyRequest().authenticated() // Todas outras rotas precisam estar autenticadas
                )
                .build();
    }

    //Expõe o AuthenticationManager para ser usado no login (ele valida o email e a senha)
    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Define o BCrypt para criptografar a senha no cadastro e comparar no login
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
