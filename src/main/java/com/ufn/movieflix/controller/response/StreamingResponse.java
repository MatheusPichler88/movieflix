package com.ufn.movieflix.controller.response;

import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String nome) {
}
