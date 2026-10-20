package com.ufn.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record StreamingRequest (@Schema(description = "Nome do serviço de streaming", example = "Netflix")
                                @NotEmpty(message = "O nome do serviço de streaming é obrigatório")
                                String nome) {
}
