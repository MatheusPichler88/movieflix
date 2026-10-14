package com.ufn.movieflix.mapper;

import com.ufn.movieflix.controller.request.CategoriaRequest;
import com.ufn.movieflix.controller.response.CategoriaResponse;
import com.ufn.movieflix.entity.Categoria;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoriaMapper {

    public static Categoria paraCategoria(CategoriaRequest categoriaRequest){
        return Categoria
                .builder()
                .nome(categoriaRequest.nome())
                .build();
    }

    public static CategoriaResponse paraCategoriaResponse (Categoria categoria){
        return CategoriaResponse
                .builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .build();
    }
}