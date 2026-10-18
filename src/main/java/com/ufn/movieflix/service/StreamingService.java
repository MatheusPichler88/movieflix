package com.ufn.movieflix.service;

import com.ufn.movieflix.entity.Streaming;
import com.ufn.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public List<Streaming> listarStreamings(){
        return streamingRepository.findAll();
    }

    public Optional<Streaming> buscarStreamingPorId(Long id){
        return streamingRepository.findById(id);
    }

    public Streaming salvarStreaming(Streaming streaming){
        return streamingRepository.save(streaming);
    }

    public Optional<Streaming> atualizarStreaming(Long id, Streaming streamingAtualizado){
        Optional<Streaming> optStreaming = streamingRepository.findById(id);
        if(optStreaming.isPresent()){
            Streaming streaming = optStreaming.get();
            streaming.setNome(streamingAtualizado.getNome());
            streamingRepository.save(streaming);
            return Optional.of(streaming);
        }
        return Optional.empty();
    }

    public void deletarStreaming(Long id){
        streamingRepository.deleteById(id);
    }


}
