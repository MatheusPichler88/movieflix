package com.ufn.movieflix.controller.response;

import lombok.Builder;

@Builder
public record CategoriaResponse (Long id, String nome){
}
