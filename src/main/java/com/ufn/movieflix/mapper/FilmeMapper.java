package com.ufn.movieflix.mapper;
import com.ufn.movieflix.controller.request.FilmeRequest;
import com.ufn.movieflix.controller.response.FilmeResponse;
import com.ufn.movieflix.entity.Filme;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class FilmeMapper {

    public static Filme paraFilme(FilmeRequest filmeRequest) {
        return Filme
                .builder()
                .titulo(filmeRequest.titulo())
                .descricao(filmeRequest.descricao())
                .avaliacao(filmeRequest.avaliacao())
                .dataLancamento(LocalDate.parse(filmeRequest.dataLancamento()))
                .criadoEm(filmeRequest.criadoEm())
                .atualizadoEm(filmeRequest.atualizadoEm())
                .build();
    }


    public static FilmeResponse paraFilmeResponse(Filme filme) {
        return FilmeResponse
                .builder()
                .id(filme.getId())
                .titulo(filme.getTitulo())
                .descricao(filme.getDescricao())
                .avaliacao(filme.getAvaliacao())
                .dataLancamento(filme.getDataLancamento())
                .criadoEm(filme.getCriadoEm())
                .atualizadoEm(filme.getAtualizadoEm())
                .build();
    }
}