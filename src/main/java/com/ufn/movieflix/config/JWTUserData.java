package com.ufn.movieflix.config;

import lombok.Builder;

//Dados do usuário que ficam dentro do token JWT, é o que o filtro coloca como usuário autenticado na requisição
@Builder
public record JWTUserData(Long id, String nome, String email) {
}
