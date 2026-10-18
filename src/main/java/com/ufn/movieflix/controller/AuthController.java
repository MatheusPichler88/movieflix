package com.ufn.movieflix.controller;

import com.ufn.movieflix.controller.request.LoginRequest;
import com.ufn.movieflix.controller.request.UsuarioRequest;
import com.ufn.movieflix.controller.response.UsuarioResponse;
import com.ufn.movieflix.entity.Usuario;
import com.ufn.movieflix.mapper.UsuarioMapper;
import com.ufn.movieflix.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponse> registrar(@RequestBody UsuarioRequest usuarioRequest){
        Usuario salvo = usuarioService.salvar(UsuarioMapper.paraUsuario(usuarioRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.paraUsuarioResponse(salvo));

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha());
        Authentication autenticacao = authenticationManager.authenticate(userAndPass);

        Usuario usuario = (Usuario) autenticacao.getPrincipal();
    }
}
