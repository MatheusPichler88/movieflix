package com.ufn.movieflix.controller;

import com.ufn.movieflix.controller.request.FilmeRequest;
import com.ufn.movieflix.controller.response.FilmeResponse;
import com.ufn.movieflix.entity.Filme;
import com.ufn.movieflix.mapper.FilmeMapper;
import com.ufn.movieflix.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/movieflix/filme")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }


    @GetMapping
    public ResponseEntity<List<FilmeResponse>> listarFilmes(){
        return ResponseEntity.ok(filmeService.listarFilmes()
                .stream()
                .map(FilmeMapper::paraFilmeResponse)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponse> buscarFilmePorId(@PathVariable Long id){
        return filmeService.buscarFilmePorId(id)
                .map(filme -> ResponseEntity.ok(FilmeMapper.paraFilmeResponse(filme)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FilmeResponse> salvarFilme(@RequestBody FilmeRequest requisicao){
        Filme filme = FilmeMapper.paraFilme(requisicao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(FilmeMapper.paraFilmeResponse(filmeService.salvarFilme(filme)));
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id){
        filmeService.deletarFilme(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
