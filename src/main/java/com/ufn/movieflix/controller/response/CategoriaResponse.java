package com.ufn.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CategoriaResponse (@Schema(description = "Identificador da categoria", example = "1")
                                 Long id,
                                 @Schema(description = "Nome da categoria", example = "Ação")
                                 String nome){
}
