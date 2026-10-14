package com.ufn.movieflix.service;

import com.ufn.movieflix.entity.Categoria;
import com.ufn.movieflix.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias(){
       return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarCategoriaPorId(Long id){
        return categoriaRepository.findById(id);
    }
    public Categoria salvarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void deletarCategoria(Long id){
        categoriaRepository.deleteById(id);
    }
}
