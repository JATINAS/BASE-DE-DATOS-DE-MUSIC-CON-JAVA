package com.ebac.modulo39.repository;

import com.ebac.modulo39.dto.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
