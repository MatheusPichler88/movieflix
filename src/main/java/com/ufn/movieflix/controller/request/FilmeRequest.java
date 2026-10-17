package com.ufn.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
public record FilmeRequest(String titulo,
                           String descricao,
                           double avaliacao,
                           @JsonFormat(pattern = "dd/MM/yyyy")
                           LocalDate lancamento,
                           List<Long> categorias,
                           List<Long> streaming) {
}
