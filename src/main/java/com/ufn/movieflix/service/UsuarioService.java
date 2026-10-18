package com.ufn.movieflix.service;

import com.ufn.movieflix.entity.Usuario;
import com.ufn.movieflix.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvar(Usuario usuario){
        //Nunca salva a senha pura, grava apenas o hash gerado pelo BCrypt
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }


}
