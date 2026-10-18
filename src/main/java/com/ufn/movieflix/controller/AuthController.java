package com.ufn.movieflix.controller;

import com.ufn.movieflix.config.TokenService;
import com.ufn.movieflix.controller.request.LoginRequest;
import com.ufn.movieflix.controller.request.UsuarioRequest;
import com.ufn.movieflix.controller.response.LoginResponse;
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
    private final TokenService tokenService;

    //Cadastra um novo usuário
    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponse> registrar(@RequestBody UsuarioRequest requisicao){
        //A service criptografa a senha antes de salvar, e o response devolve o usuário sem a senha
        Usuario salvo = usuarioService.salvar(UsuarioMapper.paraUsuario(requisicao));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.paraUsuarioResponse(salvo));

    }

    //Autentica o usuário com email e senha
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest requisicao){
        //Cria o objeto com as credenciais enviadas (ainda não autenticado)
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(requisicao.email(), requisicao.senha());
        //O AuthenticationManager busca o usuário pelo AuthService e compara a senha com o hash do banco, se estiver errada lança exceção
        Authentication autenticacao = authenticationManager.authenticate(userAndPass);

        //Se a autenticação passou, o principal é o usuário que acabou de logar
        Usuario usuario = (Usuario) autenticacao.getPrincipal();

        String token = tokenService.geradorToken(usuario);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
