package com.ebac.modulo63.controller;

import com.ebac.modulo63.dto.Pelicula;
import com.ebac.modulo63.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @GetMapping("/peliculas")
    public List<Pelicula> obtenerPeliculas() {
        return peliculaService.obtenerPeliculas();
    }

    @GetMapping("/peliculas/{id}")
    public ResponseEntity<Pelicula> obtenerPeliculaPorId(@PathVariable Long id) {
        Optional<Pelicula> peliculaOptional = peliculaService.obtenerPeliculaPorId(id);
        return peliculaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/peliculas")
    public ResponseEntity<Pelicula> crearPelicula(@RequestBody Pelicula pelicula) throws URISyntaxException {
        Pelicula peliculaCreada = peliculaService.crearPelicula(pelicula);
        return ResponseEntity.created(new URI("http://localhost/peliculas")).build();
    }

    @PutMapping("/peliculas/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Long id, @RequestBody Pelicula peliculaActualizada) {
        Optional<Pelicula> peliculaOptional = peliculaService.obtenerPeliculaPorId(id);

        if (peliculaOptional.isPresent()) {
            peliculaActualizada.setIdPelicula(peliculaOptional.get().getIdPelicula());
            peliculaService.actualizarPelicula(peliculaActualizada);

            return ResponseEntity.ok(peliculaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/peliculas/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long id) {
        peliculaService.eliminarPelicula(id);
        return ResponseEntity.noContent().build();
    }
}
