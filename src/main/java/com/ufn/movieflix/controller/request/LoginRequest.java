package com.ufn.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

//Dados enviados no login (email e senha em texto, que o Spring Security compara com o hash do banco)
public record LoginRequest(@Schema(description = "Email do usuário", example = "usuario@email.com")
                           String email,
                           @Schema(description = "Senha do usuário", example = "senha123")
                           String senha) {
}
