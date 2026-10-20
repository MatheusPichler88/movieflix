package com.ufn.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UsuarioResponse(@Schema(description = "Identificador do usuário", example = "1")
                              Long id,
                              @Schema(description = "Nome do usuário", example = "Matheus")
                              String nome,
                              @Schema(description = "Email do usuário", example = "usuario@email.com")
                              String email) {
}
