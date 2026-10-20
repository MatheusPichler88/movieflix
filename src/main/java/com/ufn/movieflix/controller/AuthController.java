package com.ufn.movieflix.controller;

import com.ufn.movieflix.config.TokenService;
import com.ufn.movieflix.controller.request.LoginRequest;
import com.ufn.movieflix.controller.request.UsuarioRequest;
import com.ufn.movieflix.controller.response.LoginResponse;
import com.ufn.movieflix.controller.response.UsuarioResponse;
import com.ufn.movieflix.entity.Usuario;
import com.ufn.movieflix.exception.UsernameOrPasswordInvalidException;
import com.ufn.movieflix.mapper.UsuarioMapper;
import com.ufn.movieflix.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
@SecurityRequirements
@Tag(name = "Autenticação", description = "Recurso responsável pelo cadastro e login de usuários")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Operation(summary = "Registrar usuário", description = "Cadastra um novo usuário. A senha é armazenada criptografada")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Email já cadastrado", content = @Content)
    })
    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponse> registrar(@Valid @RequestBody UsuarioRequest requisicao){
        Usuario salvo = usuarioService.salvar(UsuarioMapper.paraUsuario(requisicao));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.paraUsuarioResponse(salvo));

    }

    @Operation(summary = "Realizar login", description = "Autentica o usuário com email e senha e retorna o token JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos ou usuário/senha inválido", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest requisicao){

        try {
            //Cria o objeto com as credenciais enviadas (ainda não autenticado)
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(requisicao.email(), requisicao.senha());
            //O AuthenticationManager busca o usuário pelo AuthService e compara a senha com o hash do banco, se estiver errada lança exceção
            Authentication autenticacao = authenticationManager.authenticate(userAndPass);

            //Se a autenticação passou, o principal é o usuário que acabou de logar
            Usuario usuario = (Usuario) autenticacao.getPrincipal();

            String token = tokenService.gerarToken(usuario);

            return ResponseEntity.ok(new LoginResponse(token));

        } catch (BadCredentialsException e) {
            throw new UsernameOrPasswordInvalidException("Usuário ou senha inválido");
        }
    }
}
