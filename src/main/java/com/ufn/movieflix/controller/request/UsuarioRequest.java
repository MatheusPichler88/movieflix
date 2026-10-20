package com.ufn.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UsuarioRequest(@Schema(description = "Nome do usuário", example = "Matheus")
                             String nome,
                             @Schema(description = "Email do usuário, usado no login", example = "usuario@email.com")
                             String email,
                             @Schema(description = "Senha do usuário, armazenada criptografada", example = "senha123")
                             String senha) {
}
