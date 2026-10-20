package com.ufn.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoriaRequest (@NotEmpty(message = "Nome da categoria é obrigatório") String nome) {
}
