package com.ufn.movieflix.controller.response;

//Resposta do login com o token JWT que o cliente deve enviar no header Authorization das próximas requisições
public record LoginResponse(String token) {
}
