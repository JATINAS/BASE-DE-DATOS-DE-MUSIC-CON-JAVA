package com.ebac.modulo63.service;

import com.ebac.modulo63.dto.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
}
