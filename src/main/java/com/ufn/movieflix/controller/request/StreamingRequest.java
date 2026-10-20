package com.ufn.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record StreamingRequest (@NotEmpty(message = "O nome do serviço de streaming é obrigatório")String nome) {
}
