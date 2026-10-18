package com.ufn.movieflix.controller.request;

import lombok.Builder;

@Builder
public record UsuarioRequest(String nome, String email, String senha) {
}
