package com.ufn.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UsuarioRequest(@Schema(description = "Nome do usuário", example = "Matheus")
                             @NotBlank(message = "O nome do usuário é obrigatório")
                             String nome,
                             @Schema(description = "Email do usuário, usado no login", example = "usuario@email.com")
                             @NotBlank(message = "O email do usuário é obrigatório")
                             @Email(message = "Email inválido")
                             String email,
                             @Schema(description = "Senha do usuário, armazenada criptografada", example = "senha123")
                             @NotBlank(message = "A senha do usuário é obrigatória")
                             @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
                             String senha) {
}
