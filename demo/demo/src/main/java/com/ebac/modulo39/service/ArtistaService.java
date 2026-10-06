package com.ebac.modulo39.service;

import com.ebac.modulo39.dto.Album;
import com.ebac.modulo39.dto.Artista;
import com.ebac.modulo39.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ArtistaService {

    @Autowired
    ArtistaRepository artistaRepository;
    public Artista crearArtista(Artista artista) throws Exception {
            if (artista.getAlbums() != null) {
                for (Album album : artista.getAlbums()) {
                    album.setArtista(artista);
                }
            }
            return artistaRepository.save(artista);
    }

    public Optional<Artista> obtenereArtistaPorId(Long idArtista) {
        return artistaRepository.findById(idArtista);
    }

    public List<Artista> obtenerArtistas() {
        return artistaRepository.findAll();
    }

    public Artista actualizarArtista(Artista artista) throws Exception {
        Optional<Artista> resultado = artistaRepository.findById((long) artista.getIdArtista());
        if (resultado.isEmpty()) {
            throw new Exception("El artista no existe.");
        }
        Artista artistaExistente = resultado.get();
        artistaExistente.setNombre(artista.getNombre());
        Set<Integer> idsRecibidos = new HashSet<>();

        if (artista.getAlbums() != null) {
            for (Album album : artista.getAlbums()) {

                if (album.getIdAlbum() > 0) {
                    idsRecibidos.add(album.getIdAlbum());
                }
            }
        }

        /*
         * Eliminar teléfonos que ya no vienen del formulario.
         */
        artistaExistente.getAlbums().removeIf(album -> album.getIdAlbum() > 0 && !idsRecibidos.contains(album.getIdAlbum()));

        /*
         * Actualizar existentes y agregar nuevos.
         */
        if (artista.getAlbums() != null) {
            for (Album albumRecibido : artista.getAlbums()) {
                if (albumRecibido.getIdAlbum() == 0) {
                    // Album nuevo
                    albumRecibido.setArtista(artistaExistente);
                    artistaExistente.getAlbums().add(albumRecibido);
                } else {
                    // Album existente
                    artistaExistente.getAlbums().stream()
                            .filter(t -> t.getIdAlbum() == albumRecibido.getIdAlbum())
                            .findFirst()
                            .ifPresent(albumExistente -> {
                                albumExistente.setNombre(albumRecibido.getNombre());
                                albumExistente.setLanzamiento(albumRecibido.getLanzamiento());
                            });
                }
            }
        }

        return artistaRepository.save(artistaExistente);
    }


    public void eliminarArtista(Long id) {
        artistaRepository.deleteById(id);
    }
}
