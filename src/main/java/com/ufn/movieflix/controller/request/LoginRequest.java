package com.ufn.movieflix.controller.request;

//Dados enviados no login (email e senha em texto, que o Spring Security compara com o hash do banco)
public record LoginRequest(String email, String senha) {
}
