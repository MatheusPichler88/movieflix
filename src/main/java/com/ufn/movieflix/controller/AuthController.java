package com.ufn.movieflix.controller;

import com.ufn.movieflix.controller.request.UsuarioRequest;
import com.ufn.movieflix.controller.response.UsuarioResponse;
import com.ufn.movieflix.entity.Usuario;
import com.ufn.movieflix.mapper.UsuarioMapper;
import com.ufn.movieflix.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponse> registro (@RequestBody UsuarioRequest requisicao){
        Usuario salvo = usuarioService.salvar(UsuarioMapper.paraUsuario(requisicao));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.paraUsuarioRequest(salvo));

    }
}
