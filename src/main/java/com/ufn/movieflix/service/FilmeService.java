package com.ufn.movieflix.service;

import com.ufn.movieflix.entity.Categoria;
import com.ufn.movieflix.entity.Filme;
import com.ufn.movieflix.entity.Streaming;
import com.ufn.movieflix.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final CategoriaService categoriaService;
    private final StreamingService streamingService;


    public List<Filme> listarFilmes(){
        return filmeRepository.findAll();
    }

    public Optional<Filme> buscarFilmePorId(Long id){
        return filmeRepository.findById(id);
    }

    public Filme salvarFilme(Filme filme){
        filme.setCategorias(listarCategorias(filme.getCategorias()));
        filme.setStreaming(listarStreaming(filme.getStreaming()));
        return filmeRepository.save(filme);
    }

    public void deletarFilme(Long id){
        filmeRepository.deleteById(id);
    }

    private List<Categoria> listarCategorias(List<Categoria> categorias){
        List<Categoria> CategoriasEncontradas = new ArrayList<>();
        for (Categoria categoria : categorias) {
            categoriaService.buscarCategoriaPorId(categoria.getId()).ifPresent(CategoriasEncontradas::add);
        }
        return CategoriasEncontradas;
    }

    private List<Streaming> listarStreaming(List<Streaming> streamings){
        List<Streaming> StreamingEncontrados = new ArrayList<>();
        for (Streaming streaming : streamings) {
            streamingService.buscarStreamingPorId(streaming.getId()).ifPresent(StreamingEncontrados::add);
        }
        return StreamingEncontrados;
    }

}
