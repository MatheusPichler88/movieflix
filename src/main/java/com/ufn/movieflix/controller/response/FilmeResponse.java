package com.ufn.movieflix.controller.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record FilmeResponse(Long id, String titulo, String descricao, LocalDate dataLancamento, double avaliacao, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
}
