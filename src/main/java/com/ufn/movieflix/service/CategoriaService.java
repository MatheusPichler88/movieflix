package com.ufn.movieflix.service;

import com.ufn.movieflix.entity.Categoria;
import com.ufn.movieflix.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarCategoriaPorId(Long id){
        return categoriaRepository.findById(id);
    }

    public Categoria salvarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> atualizarCategoria(Long id, Categoria categoriaAtualizada){
        Optional<Categoria> optCategoria = categoriaRepository.findById(id);
        if(optCategoria.isPresent()){
            Categoria categoria = optCategoria.get();
            categoria.setNome(categoriaAtualizada.getNome());
            categoriaRepository.save(categoria);
            return Optional.of(categoria);
        }
        return Optional.empty();
    }

    public void deletarCategoria(Long id){
        categoriaRepository.deleteById(id);
    }
}
