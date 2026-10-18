package com.ufn.movieflix.controller.response;

import lombok.Builder;

@Builder
public record UsuarioResponse(Long id, String nome, String email) {
}
