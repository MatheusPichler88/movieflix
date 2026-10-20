package com.ufn.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

//Resposta do login com o token JWT que o cliente deve enviar no header Authorization das próximas requisições
public record LoginResponse(@Schema(description = "Token JWT para autenticação nas demais rotas")
                            String token) {
}
