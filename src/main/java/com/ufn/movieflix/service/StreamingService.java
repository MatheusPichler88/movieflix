package com.ufn.movieflix.service;

import com.ufn.movieflix.entity.Streaming;
import com.ufn.movieflix.repository.StreamingInterface;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StreamingService {

    private final StreamingInterface streamingInterface;

    public StreamingService(StreamingInterface streamingInterface) {
        this.streamingInterface = streamingInterface;
    }

    public List<Streaming> listarCategorias(){
        return streamingInterface.findAll();
    }

    public Optional<Streaming> buscarStreamingPorId(Long id){
        return streamingInterface.findById(id);
    }

    public Streaming salvarStreaming(Streaming streaming){
        return streamingInterface.save(streaming);
    }

    public void deletarStreaming(Long id){
        streamingInterface.deleteById(id);
    }


}
