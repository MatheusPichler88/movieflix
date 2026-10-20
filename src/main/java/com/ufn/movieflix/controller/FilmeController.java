package com.ufn.movieflix.controller;

import com.ufn.movieflix.controller.request.FilmeRequest;
import com.ufn.movieflix.controller.response.FilmeResponse;
import com.ufn.movieflix.entity.Filme;
import com.ufn.movieflix.mapper.FilmeMapper;
import com.ufn.movieflix.service.FilmeService;
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
@RequestMapping("/movieflix/filmes")
@RequiredArgsConstructor
@Tag(name = "Filme", description = "Recurso responsável pelo gerenciamento dos filmes")
public class FilmeController {

    private final FilmeService filmeService;


    @Operation(summary = "Listar filmes", description = "Retorna todos os filmes cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filmes listados com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FilmeResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<FilmeResponse>> listarFilmes(){
        return ResponseEntity.ok(filmeService.listarFilmes()
                .stream()
                .map(FilmeMapper::paraFilmeResponse)
                .toList()
        );
    }

    @Operation(summary = "Buscar filme por ID", description = "Retorna os dados de um filme a partir do seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filme encontrado",
                    content = @Content(schema = @Schema(implementation = FilmeResponse.class))),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponse> buscarFilmePorId(@Parameter(description = "Identificador do filme") @PathVariable Long id){
        return filmeService.buscarFilmePorId(id)
                .map(filme -> ResponseEntity.ok(FilmeMapper.paraFilmeResponse(filme)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar filmes por categoria", description = "Retorna os filmes vinculados a uma categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filmes listados com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FilmeResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    @GetMapping("/procurar")
    public ResponseEntity<List<FilmeResponse>> filmesPorCategoria(@Parameter(description = "Identificador da categoria") @RequestParam Long categoria){
        return ResponseEntity.ok(filmeService.filmesPorCategoria(categoria)
                .stream()
                .map(FilmeMapper::paraFilmeResponse)
                .toList()
        );
    }

    @Operation(summary = "Salvar filme", description = "Salva um novo filme no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso",
                    content = @Content(schema = @Schema(implementation = FilmeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<FilmeResponse> salvarFilme(@Valid @RequestBody FilmeRequest requisicao){
        Filme filmeSalvo = filmeService.salvarFilme(FilmeMapper.paraFilme(requisicao));
        return ResponseEntity.status(HttpStatus.CREATED).body(FilmeMapper.paraFilmeResponse(filmeSalvo));
    }

    @Operation(summary = "Atualizar filme", description = "Atualiza os dados de um filme existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = FilmeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<FilmeResponse> atualizarFilme(@Parameter(description = "Identificador do filme") @PathVariable Long id, @Valid @RequestBody FilmeRequest requisicao){
        return filmeService.atualizarFilme(id, FilmeMapper.paraFilme(requisicao))
                .map(filme -> ResponseEntity.ok(FilmeMapper.paraFilmeResponse(filme)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar filme", description = "Remove um filme a partir do seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@Parameter(description = "Identificador do filme") @PathVariable Long id){
        filmeService.deletarFilme(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
