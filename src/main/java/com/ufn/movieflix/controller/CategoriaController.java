package com.ufn.movieflix.controller;

import com.ufn.movieflix.controller.request.CategoriaRequest;
import com.ufn.movieflix.controller.response.CategoriaResponse;
import com.ufn.movieflix.entity.Categoria;
import com.ufn.movieflix.mapper.CategoriaMapper;
import com.ufn.movieflix.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listarCategorias()
                .stream()
                .map(CategoriaMapper::paraCategoriaResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.buscarCategoriaPorId(id)
                .map(categoria -> ResponseEntity.ok(CategoriaMapper.paraCategoriaResponse(categoria)))
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> salvarCategoria(@Valid @RequestBody CategoriaRequest requisicao){
        Categoria categoria = CategoriaMapper.paraCategoria(requisicao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoriaMapper.paraCategoriaResponse(categoriaService.salvarCategoria(categoria)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> atualizarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaRequest requisicao){
        return categoriaService.atualizarCategoria(id, CategoriaMapper.paraCategoria(requisicao))
                .map(categoria -> ResponseEntity.ok(CategoriaMapper.paraCategoriaResponse(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id){
        categoriaService.deletarCategoria(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
