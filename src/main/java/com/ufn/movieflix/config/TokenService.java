package com.ufn.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ufn.movieflix.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

//Responsável por gerar e validar os tokens JWT da aplicação
@Component
public class TokenService {

    //Chave usada para assinar o token, vem do .env (JWT_SECRET) pelo application.yaml
    @Value("${movieflix.security.secretKey}")
    private String secretKey;

    //Gera o token do usuário logado, com id, nome e email, válido por 24 horas
    public String gerarToken(Usuario usuario){
        Algorithm algoritmo = Algorithm.HMAC256(secretKey);


        return JWT.create()
                .withSubject(usuario.getEmail())
                .withClaim("id", usuario.getId())
                .withClaim("nome", usuario.getNome())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("MovieFlix")
                .sign(algoritmo);
    }

    //Valida o token (assinatura, emissor e expiração) e devolve os dados do usuário, ou vazio se o token for inválido
    public Optional<JWTUserData> validarToken(String token){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secretKey);

            DecodedJWT jwt = JWT.require(algoritmo)
                    .withIssuer("MovieFlix")
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData
                    .builder()
                    .id(jwt.getClaim("id").asLong())
                    .nome(jwt.getClaim("nome").asString())
                    .email(jwt.getSubject())
                    .build());
        } catch (JWTVerificationException e){
            return Optional.empty();
        }
    }
}
