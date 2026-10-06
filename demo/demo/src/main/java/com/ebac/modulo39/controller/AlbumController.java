package com.ebac.modulo39.controller;

import com.ebac.modulo39.dto.Album;
import com.ebac.modulo39.service.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AlbumController {

    @Autowired
    AlbumService albumService;

//    @GetMapping("/albums")
//    public ResponseWrapper<List<Album>> obtenerAlbums() {
//        log.info("Obteniendo albums");
//        List<Album> albums = albumService.obtenerAlbums();
//        return new ResponseWrapper<>(true, "Listado de albums", albums);
//    }

    @GetMapping("/albums")
    public ResponseWrapper<List<Album>> obtenerAlbums(@RequestParam(required = false) String artista) {
        List<Album> albums = (artista == null || artista.isBlank()) ? albumService.obtenerAlbums() : albumService.buscarPorArtista(artista);

        return new ResponseWrapper<>(true, "Listado de albums", albums);
    }
    @GetMapping("/albums/{id}")
    public ResponseWrapper<Album> obtenerAlbumPorId(@PathVariable Long id) {
        log.info("Obteniendo album por id {}", id);
        Optional<Album> albumOptional = albumService.obtenerAlbumPorId(id);

        if (albumOptional.isPresent()) {
            return new ResponseWrapper<>(true, "Información del album " + id, albumOptional.get());
        } else {
            return new ResponseWrapper<>(false, "El album indicado no existe", null);
        }
    }

    @PostMapping("/albums")
    public ResponseWrapper<Album> crearAlbum(@RequestBody Album album) {
        log.info("Album recibido: {}", album);
        try {
            Album albumCreado = albumService.crearAlbum(album);
            return new ResponseWrapper<>(true, "Album creado exitosamente", albumCreado);
        } catch (Exception e) {
            return new ResponseWrapper<>(false, e.getMessage(), null);
        }
    }

    @PutMapping("/albums/{id}")
    public ResponseWrapper<Album> actualizarAlbum(@PathVariable Long id, @RequestBody Album albumActualizado) {
        log.info("Actualizando album con id {}", id);
        Optional<Album> albumOptional = albumService.obtenerAlbumPorId(id);
        if (albumOptional.isPresent()) {
            albumActualizado.setIdAlbum(albumOptional.get().getIdAlbum());
            Album album = albumService.actualizarAlbum(albumActualizado);

            return new ResponseWrapper<>(true, "Album actualizado correctamente", album);
        } else {
            return new ResponseWrapper<>(false, "El album indicado no existe", null);
        }
    }

    @DeleteMapping("/albums/{id}")
    public ResponseWrapper<Void> eliminarAlbum(@PathVariable Long id) {
        log.info("Eliminando album con id {}", id);
        Optional<Album> albumOptional = albumService.obtenerAlbumPorId(id);
        if (albumOptional.isEmpty()) {
            return new ResponseWrapper<>(false, "El album indicado no existe", null);
        }
        albumService.eliminarAlbum(id);
        return new ResponseWrapper<>(true, "Album eliminado exitosamente", null);
    }
}

