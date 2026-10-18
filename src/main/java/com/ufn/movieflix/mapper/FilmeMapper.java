package com.ufn.movieflix.mapper;
import com.ufn.movieflix.controller.request.FilmeRequest;
import com.ufn.movieflix.controller.response.CategoriaResponse;
import com.ufn.movieflix.controller.response.FilmeResponse;
import com.ufn.movieflix.controller.response.StreamingResponse;
import com.ufn.movieflix.entity.Categoria;
import com.ufn.movieflix.entity.Filme;
import com.ufn.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass //torna a classe final e deixa os métodos static
public class FilmeMapper {

    //Converte uma requisição para um objeto Filme
    public static Filme paraFilme(FilmeRequest filmeRequest) {

        //Pega a lista de IDs das categorias vindas da requisição e converte para uma lista de Categoria
        List<Categoria> categorias = filmeRequest.categorias().stream()
                .map(categoriaId -> Categoria.builder().id(categoriaId).build())
                .toList();
        List<Streaming> streamings = filmeRequest.streamings().stream()
                .map(streamingsId -> Streaming.builder().id(streamingsId).build())
                .toList();

        return Filme
                .builder()
                .titulo(filmeRequest.titulo())
                .descricao(filmeRequest.descricao())
                .avaliacao(filmeRequest.avaliacao())
                .lancamento(filmeRequest.lancamento())
                .categorias(categorias)
                .streamings(streamings)
                .build();
    }


    public static FilmeResponse paraFilmeResponse(Filme filme) {

        List<CategoriaResponse> categoria = filme.getCategorias()
                .stream()
                .map(categorias -> CategoriaMapper.paraCategoriaResponse(categorias))
                .toList();

        List<StreamingResponse> streamings = filme.getStreamings()
                .stream()
                .map(streaming -> StreamingMapper.paraStreamingResponse(streaming))
                .toList();

        return FilmeResponse
                .builder()
                .id(filme.getId())
                .titulo(filme.getTitulo())
                .descricao(filme.getDescricao())
                .avaliacao(filme.getAvaliacao())
                .lancamento(filme.getLancamento())
                .categorias(categoria)
                .streamings(streamings)
                .build();
    }
}
