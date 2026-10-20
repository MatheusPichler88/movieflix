package com.ufn.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StreamingResponse(@Schema(description = "Identificador do streaming", example = "1")
                                Long id,
                                @Schema(description = "Nome do serviço de streaming", example = "Netflix")
                                String nome) {
}
