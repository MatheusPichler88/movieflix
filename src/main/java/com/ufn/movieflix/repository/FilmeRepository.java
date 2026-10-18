package com.ufn.movieflix.repository;

import com.ufn.movieflix.entity.Categoria;
import com.ufn.movieflix.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findFilmeByCategoria(List<Categoria> categorias);
}
