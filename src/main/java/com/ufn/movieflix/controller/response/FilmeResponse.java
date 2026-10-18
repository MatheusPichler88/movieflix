package com.ufn.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record FilmeResponse(Long id,
                            String titulo,
                            String descricao,
                            double avaliacao,
                            @JsonFormat(pattern = "dd/MM/yyyy")
                            LocalDate lancamento,
                            List<CategoriaResponse> categorias,
                            List<StreamingResponse> streamings) {
}
