package com.ufn.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

//Dados enviados no login (email e senha em texto, que o Spring Security compara com o hash do banco)
public record LoginRequest(@Schema(description = "Email do usuário", example = "usuario@email.com")
                           @NotBlank(message = "O email é obrigatório")
                           String email,
                           @Schema(description = "Senha do usuário", example = "senha123")
                           @NotBlank(message = "A senha é obrigatória")
                           String senha) {
}
