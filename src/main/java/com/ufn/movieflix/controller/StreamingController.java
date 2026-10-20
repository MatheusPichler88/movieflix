package com.ufn.movieflix.controller;

import com.ufn.movieflix.controller.request.StreamingRequest;
import com.ufn.movieflix.controller.response.StreamingResponse;
import com.ufn.movieflix.entity.Streaming;
import com.ufn.movieflix.mapper.StreamingMapper;
import com.ufn.movieflix.service.StreamingService;
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
@RequestMapping("/movieflix/streamings")
@RequiredArgsConstructor
@Tag(name = "Streaming", description = "Recurso responsável pelo gerenciamento dos serviços de streaming")
public class StreamingController {

    private final StreamingService streamingService;

    @Operation(summary = "Listar streamings", description = "Retorna todos os serviços de streaming cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Streamings listados com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = StreamingResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<StreamingResponse>> listarStreamings(){
        return ResponseEntity.ok(streamingService.listarStreamings()
                .stream()
                .map(StreamingMapper::paraStreamingResponse)
                .toList());

    }

    @Operation(summary = "Buscar streaming por ID", description = "Retorna os dados de um serviço de streaming a partir do seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Streaming encontrado",
                    content = @Content(schema = @Schema(implementation = StreamingResponse.class))),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Streaming não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> buscarStreamingPorId(@Parameter(description = "Identificador do streaming") @PathVariable Long id){
        return streamingService.buscarStreamingPorId(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.paraStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salvar streaming", description = "Salva um novo serviço de streaming no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Streaming salvo com sucesso",
                    content = @Content(schema = @Schema(implementation = StreamingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<StreamingResponse> salvarStreaming(@Valid @RequestBody StreamingRequest requisicao){
        Streaming streaming = StreamingMapper.paraStreaming(requisicao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper.paraStreamingResponse(streamingService.salvarStreaming(streaming)));
    }

    @Operation(summary = "Atualizar streaming", description = "Atualiza os dados de um serviço de streaming existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Streaming atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = StreamingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Streaming não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<StreamingResponse> atualizarStreaming(@Parameter(description = "Identificador do streaming") @PathVariable Long id, @Valid @RequestBody StreamingRequest requisicao){
        return streamingService.atualizarStreaming(id, StreamingMapper.paraStreaming(requisicao))
                .map(streaming -> ResponseEntity.ok(StreamingMapper.paraStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar streaming", description = "Remove um serviço de streaming a partir do seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Streaming deletado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarStreaming(@Parameter(description = "Identificador do streaming") @PathVariable Long id){
        streamingService.deletarStreaming(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
