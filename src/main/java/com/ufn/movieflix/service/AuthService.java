package com.ufn.movieflix.service;

import com.ufn.movieflix.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//O Spring Security usa essa classe para buscar o usuário no banco durante o login
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       //O "username" do Spring Security aqui é o email
       return usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha não encontrado"));
    }
}
