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
        filme.setCategorias(buscarCategorias(filme.getCategorias()));
        filme.setStreamings(buscarStreamings(filme.getStreamings()));
        return filmeRepository.save(filme);
    }

    public Optional<Filme> atualizarFilme(Long id, Filme filmeAtualizado){
        Optional<Filme> optFilme = filmeRepository.findById(id);
        if(optFilme.isPresent()){
            List<Categoria> categorias = buscarCategorias(filmeAtualizado.getCategorias());
            List<Streaming> streamings = buscarStreamings(filmeAtualizado.getStreamings());

            Filme filme = optFilme.get();
            filme.setTitulo(filmeAtualizado.getTitulo());
            filme.setDescricao(filmeAtualizado.getDescricao());
            filme.setAvaliacao(filmeAtualizado.getAvaliacao());
            filme.setLancamento(filmeAtualizado.getLancamento());

            filme.getCategorias().clear();
            filme.getCategorias().addAll(categorias);

            filme.getStreamings().clear();
            filme.getStreamings().addAll(streamings);
            filmeRepository.save(filme);
            return Optional.of(filme);
        }
        return Optional.empty();
    }

    public void deletarFilme(Long id){
        filmeRepository.deleteById(id);
    }

    public List<Filme> filmesPorCategoria(Long id){
        return filmeRepository.buscarPorCategorias(List.of(Categoria.builder()
                .id(id)
                .build()));
    }

    private List<Categoria> buscarCategorias(List<Categoria> categorias){
        List<Categoria> categoriasEncontradas = new ArrayList<>();
        for (Categoria categoria : categorias) {
            categoriaService.buscarCategoriaPorId(categoria.getId()).ifPresent(categoriasEncontradas::add);
        }
        return categoriasEncontradas;
    }

    private List<Streaming> buscarStreamings(List<Streaming> streamings){
        List<Streaming> streamingsEncontrados = new ArrayList<>();
        for (Streaming streaming : streamings) {
            streamingService.buscarStreamingPorId(streaming.getId()).ifPresent(streamingsEncontrados::add);
        }
        return streamingsEncontrados;
    }

}
