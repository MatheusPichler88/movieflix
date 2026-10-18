package com.ufn.movieflix.mapper;

import com.ufn.movieflix.controller.request.UsuarioRequest;
import com.ufn.movieflix.controller.response.UsuarioResponse;
import com.ufn.movieflix.entity.Usuario;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsuarioMapper {

    public static Usuario paraUsuario(UsuarioRequest usuarioRequest){
        return Usuario.builder()
                .nome(usuarioRequest.nome())
                .email(usuarioRequest.email())
                .senha(usuarioRequest.senha())
                .build();
    }

    public static UsuarioResponse paraUsuarioRequest(Usuario usuario){
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();

    }
}
