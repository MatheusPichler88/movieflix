package com.ufn.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record CategoriaRequest (@Schema(description = "Nome da categoria", example = "Ação")
                                @NotEmpty(message = "Nome da categoria é obrigatório")
                                String nome) {
}
