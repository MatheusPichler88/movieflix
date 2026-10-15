package com.ufn.movieflix.mapper;

import com.ufn.movieflix.controller.request.StreamingRequest;
import com.ufn.movieflix.controller.response.StreamingResponse;
import com.ufn.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming paraStreaming(StreamingRequest streamingRequest){
        return Streaming
                .builder()
                .nome(streamingRequest.nome())
                .build();
    }

    public static StreamingResponse paraStreamingResponse(Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .nome(streaming.getNome())
                .build();
    }

}
