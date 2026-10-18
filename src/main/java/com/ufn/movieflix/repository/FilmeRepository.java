package com.ufn.movieflix.repository;

import com.ufn.movieflix.entity.Categoria;
import com.ufn.movieflix.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    @Query("SELECT f FROM Filme f JOIN f.categorias c WHERE c IN :categorias")
    List<Filme> buscarPorCategorias(@Param("categorias") List<Categoria> categorias);}
