package com.ufn.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record FilmeRequest(@Schema(description = "Nome do filme", example = "Interestelar")
                           @NotEmpty(message = "O título do filme é obrigatório! ")
                           String titulo,
                           @Schema(description = "Descrição do filme", example = "Exploradores viajam através de um buraco de minhoca em busca de um novo lar para a humanidade")
                           String descricao,
                           @Schema(description = "Avaliação do filme", example = "8.7")
                           double avaliacao,
                           @JsonFormat(pattern = "dd/MM/yyyy")
                           @Schema(type = "string", format = "date", pattern = "dd/MM/yyyy", description = "Data de lançamento do filme", example = "07/11/2014")
                           LocalDate lancamento,
                           @Schema(description = "Lista de IDs das categorias do filme", example = "[1, 2]")
                           List<Long> categorias,
                           @Schema(description = "Lista de IDs dos serviços de streaming onde o filme está disponível", example = "[1]")
                           List<Long> streamings) {
}
