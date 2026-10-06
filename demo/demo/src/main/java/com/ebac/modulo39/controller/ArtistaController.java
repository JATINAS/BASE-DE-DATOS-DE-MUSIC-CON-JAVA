package com.ebac.modulo39.controller;

import com.ebac.modulo39.dto.Artista;
import com.ebac.modulo39.service.ArtistaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin (origins = "http://localhost:3000")
@RestController         //PERMITE RETORNAR LA INFORMACION EN JSON O XML, JSON POR DEFAULT
public class ArtistaController {

    @Autowired
    ArtistaService artistaService;

    @GetMapping("/artistas")
    public ResponseWrapper<List<Artista>> obtenerArtista() {
        log.info("Obteniendo artistas");
        List<Artista> artistaList = artistaService.obtenerArtistas();

        return new ResponseWrapper<>(true, "Listado de Artistas", artistaList);
    }


    @GetMapping("/artistas/{id}")
    public ResponseWrapper<Artista> obtenerArtistaPorId(@PathVariable Long id) {
        log.info("Obteniendo artista por id {}", id);
        Optional<Artista> artistaOptional = artistaService.obtenereArtistaPorId(id);

        if (artistaOptional.isPresent()) {
            return new ResponseWrapper<>(true, "Información del artista " + id, artistaOptional.get());
        } else {
            return new ResponseWrapper<>(false, "El artista indicado no existe", null);
        }
    }

    @PostMapping("/artistas")
    public ResponseWrapper<Artista> crearArtista(@RequestBody Artista artista) {
        log.info("Artista recibido: {}", artista);
        log.info("Albums recibidos: {}", artista.getAlbums());
        try {
            Artista artistaCreado = artistaService.crearArtista(artista);
            return new ResponseWrapper<>(true, "Artista creado exitosamente", artistaCreado);

        } catch (Exception e) {
            return new ResponseWrapper<>(false, e.getMessage(), null);
        }
    }


    @PutMapping("/artistas/{id}")
    public ResponseWrapper<Artista> actualizarArtista(@PathVariable Long id, @RequestBody Artista artistaActualizado) throws Exception {
        Optional<Artista> artistaOptional = artistaService.obtenereArtistaPorId(id);

        if (artistaOptional.isPresent()) {
            artistaActualizado.setIdArtista(artistaOptional.get().getIdArtista());
            Artista artista = artistaService.actualizarArtista(artistaActualizado);
            return new ResponseWrapper<>(true, "Artista actualizado correctamente", artista);
        } else {
            return new ResponseWrapper<>(false, "El artista indicado no existe", null);
        }
    }


    @DeleteMapping("/artistas/{id}")
    public ResponseWrapper<Void> eliminarArtista(@PathVariable Long id) {
        Optional<Artista> artista = artistaService.obtenereArtistaPorId(id);
        if (artista.isEmpty()) {
            return new ResponseWrapper<>(false, "El artista indicado no existe", null);
        }
        artistaService.eliminarArtista(id);
        return new ResponseWrapper<>(true, "Artista eliminado correctamente", null);
    }

}
