package com.ebac.modulo39.repository;

import com.ebac.modulo39.dto.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByArtista_NombreContainingIgnoreCase(String nombre);
}
