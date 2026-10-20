package com.ufn.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record FilmeResponse(@Schema(description = "Identificador do filme", example = "1")
                            Long id,
                            @Schema(description = "Nome do filme", example = "Interestelar")
                            String titulo,
                            @Schema(description = "Descrição do filme", example = "Exploradores viajam através de um buraco de minhoca em busca de um novo lar para a humanidade")
                            String descricao,
                            @Schema(description = "Avaliação do filme", example = "8.7")
                            double avaliacao,
                            @JsonFormat(pattern = "dd/MM/yyyy")
                            @Schema(type = "string", format = "date", pattern = "dd/MM/yyyy", description = "Data de lançamento do filme", example = "07/11/2014")
                            LocalDate lancamento,
                            @Schema(description = "Categorias do filme")
                            List<CategoriaResponse> categorias,
                            @Schema(description = "Serviços de streaming onde o filme está disponível")
                            List<StreamingResponse> streamings) {
}
