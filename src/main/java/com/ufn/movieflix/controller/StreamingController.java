package com.ufn.movieflix.controller;

import com.ufn.movieflix.controller.request.StreamingRequest;
import com.ufn.movieflix.controller.response.StreamingResponse;
import com.ufn.movieflix.entity.Streaming;
import com.ufn.movieflix.mapper.StreamingMapper;
import com.ufn.movieflix.service.StreamingService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    //Get
    @GetMapping
    public ResponseEntity<List<StreamingResponse>> listarStreaming(){
        return ResponseEntity.ok(streamingService.listarCategorias()
                .stream()
                .map(StreamingMapper::paraStreamingResponse)
                .toList());

    }


    //GetID

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> buscarStreamingPorId(@PathVariable Long id){
        return streamingService.buscarStreamingPorId(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.paraStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    //Post
    @PostMapping
    public ResponseEntity<StreamingResponse> salvarStreaming (@RequestBody StreamingRequest requisicao){
        Streaming streaming = StreamingMapper.paraStreaming(requisicao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper.paraStreamingResponse(streamingService.salvarStreaming(streaming)));
    }


    //Put
    @PutMapping("/{id}")
    public ResponseEntity<StreamingResponse> atualizarStreaming(@PathVariable Long id, @RequestBody StreamingRequest requisicao){
        return streamingService.atualizarStreaming(id, StreamingMapper.paraStreaming(requisicao))
                .map(streaming -> ResponseEntity.ok(StreamingMapper.paraStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarStreaming(@PathVariable Long id){
        streamingService.deletarStreaming(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
