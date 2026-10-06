package com.ebac.modulo39.service;

import com.ebac.modulo39.dto.Album;
import com.ebac.modulo39.dto.Artista;
import com.ebac.modulo39.repository.AlbumRepository;
import com.ebac.modulo39.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ArtistaRepository artistaRepository;

    public Album crearAlbum(Album album) throws Exception {
        if (album.getArtista() == null || album.getArtista().getIdArtista() == null) {
            throw new Exception("El artista es obligatorio");
        }
        Long idArtista = album.getArtista().getIdArtista();
        Artista artista = artistaRepository.findById(idArtista).orElseThrow(() -> new Exception("El artista indicado no existe"));
        album.setArtista(artista);

        return albumRepository.save(album);
    }

    public Optional<Album> obtenerAlbumPorId(Long idAlbum) {
        return albumRepository.findById(idAlbum);
    }

    public List<Album> obtenerAlbums() {
        return albumRepository.findAll();
    }

    public Album actualizarAlbum(Album album) {
        if (album.getArtista() == null || album.getArtista().getIdArtista() == null) {
            throw new RuntimeException("El artista es obligatorio");
        }

        Long idArtista = album.getArtista().getIdArtista();
        Artista artista = artistaRepository.findById(idArtista).orElseThrow(() -> new RuntimeException("El artista indicado no existe"));
        album.setArtista(artista);

        return albumRepository.save(album);
    }

    public void eliminarAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    public List<Album> buscarPorArtista(String artista) {
        return albumRepository.findByArtista_NombreContainingIgnoreCase(artista);
    }
}

