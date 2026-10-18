package com.ufn.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ufn.movieflix.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenService {

    @Value("${movieflix.security.secretKey}")
    private String secretKey;

    public String geradorToken(Usuario usuario){
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
}
