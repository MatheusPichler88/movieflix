package com.ufn.movieflix.controller.request;

import java.time.LocalDateTime;

public record FilmeRequest(String titulo, String descricao, String dataLancamento, double avaliacao, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
}
