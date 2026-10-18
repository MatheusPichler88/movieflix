package com.ufn.movieflix.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
//UserDetails é a interface que o Spring Security usa para representar o usuário que está logando
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;


    //Perfis/permissões do usuário (por enquanto sem nenhuma)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    //Senha que o Spring Security compara no login (TODO: ainda retorna vazio, deve retornar o campo senha)
    @Override
    public @Nullable String getPassword() {
        return "";
    }

    //Identificador do usuário no login (TODO: ainda retorna vazio, deve retornar o email)
    @Override
    public String getUsername() {
        return "";
    }

    //Os 4 métodos abaixo controlam o estado da conta, por enquanto a conta nunca expira, nunca bloqueia e está sempre ativa
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
