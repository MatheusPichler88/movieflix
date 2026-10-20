package com.ufn.movieflix.controller;

import com.ufn.movieflix.controller.request.FilmeRequest;
import com.ufn.movieflix.controller.response.FilmeResponse;
import com.ufn.movieflix.entity.Filme;
import com.ufn.movieflix.mapper.FilmeMapper;
import com.ufn.movieflix.service.FilmeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/filmes")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeService filmeService;


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

    @GetMapping("/procurar")
    public ResponseEntity<List<FilmeResponse>> filmesPorCategoria(@RequestParam Long categoria){
        return ResponseEntity.ok(filmeService.filmesPorCategoria(categoria)
                .stream()
                .map(FilmeMapper::paraFilmeResponse)
                .toList()
        );
    }

    @PostMapping
    public ResponseEntity<FilmeResponse> salvarFilme(@Valid @RequestBody FilmeRequest requisicao){
        Filme filmeSalvo = filmeService.salvarFilme(FilmeMapper.paraFilme(requisicao));
        return ResponseEntity.ok(FilmeMapper.paraFilmeResponse(filmeSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeResponse> atualizarFilme(@PathVariable Long id, @Valid @RequestBody FilmeRequest requisicao){
        return filmeService.atualizarFilme(id, FilmeMapper.paraFilme(requisicao))
                .map(filme -> ResponseEntity.ok(FilmeMapper.paraFilmeResponse(filme)))
                .orElse(ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id){
        filmeService.deletarFilme(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
