package com.ufn.movieflix.repository;

import com.ufn.movieflix.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamingInterface extends JpaRepository<Streaming, Long> {
}
