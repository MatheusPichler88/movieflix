package com.ufn.movieflix.service;

import com.ufn.movieflix.entity.Filme;
import com.ufn.movieflix.repository.FilmeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> listarFilmes(){
        return filmeRepository.findAll();
    }

    public Optional<Filme> buscarFilmePorId(Long id){
        return filmeRepository.findById(id);
    }

    public Filme salvarFilme(Filme filme){
        return filmeRepository.save(filme);
    }

    public void deletarFilme(Long id){
        filmeRepository.deleteById(id);
    }
}
