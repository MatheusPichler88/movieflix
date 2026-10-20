package com.ufn.movieflix.controller;

import com.ufn.movieflix.controller.request.CategoriaRequest;
import com.ufn.movieflix.controller.response.CategoriaResponse;
import com.ufn.movieflix.entity.Categoria;
import com.ufn.movieflix.mapper.CategoriaMapper;
import com.ufn.movieflix.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/categorias")
@RequiredArgsConstructor
@Tag(name = "Categoria", description = "Recurso responsável pelo gerenciamento das categorias de filmes")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Listar categorias", description = "Retorna todas as categorias cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categorias listadas com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoriaResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listarCategorias()
                .stream()
                .map(CategoriaMapper::paraCategoriaResponse)
                .toList());
    }

    @Operation(summary = "Buscar categoria por ID", description = "Retorna os dados de uma categoria a partir do seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria encontrada",
                    content = @Content(schema = @Schema(implementation = CategoriaResponse.class))),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscarCategoriaPorId(@Parameter(description = "Identificador da categoria") @PathVariable Long id) {
        return categoriaService.buscarCategoriaPorId(id)
                .map(categoria -> ResponseEntity.ok(CategoriaMapper.paraCategoriaResponse(categoria)))
                .orElse(ResponseEntity.notFound().build());

    }

    @Operation(summary = "Salvar categoria", description = "Salva uma nova categoria no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoria salva com sucesso",
                    content = @Content(schema = @Schema(implementation = CategoriaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CategoriaResponse> salvarCategoria(@Valid @RequestBody CategoriaRequest requisicao){
        Categoria categoria = CategoriaMapper.paraCategoria(requisicao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoriaMapper.paraCategoriaResponse(categoriaService.salvarCategoria(categoria)));
    }

    @Operation(summary = "Atualizar categoria", description = "Atualiza os dados de uma categoria existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = CategoriaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> atualizarCategoria(@Parameter(description = "Identificador da categoria") @PathVariable Long id, @Valid @RequestBody CategoriaRequest requisicao){
        return categoriaService.atualizarCategoria(id, CategoriaMapper.paraCategoria(requisicao))
                .map(categoria -> ResponseEntity.ok(CategoriaMapper.paraCategoriaResponse(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar categoria", description = "Remove uma categoria a partir do seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@Parameter(description = "Identificador da categoria") @PathVariable Long id){
        categoriaService.deletarCategoria(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
