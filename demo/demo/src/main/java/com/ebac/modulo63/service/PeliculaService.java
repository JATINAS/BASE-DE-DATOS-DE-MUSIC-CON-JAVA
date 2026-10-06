package com.ebac.modulo63.service;

import com.ebac.modulo63.dto.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    @Autowired
    PeliculaRepository peliculaRepository;

    public Pelicula crearPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public Optional<Pelicula> obtenerPeliculaPorId(Long idPelicula) {
        return peliculaRepository.findById(idPelicula);
    }

    public List<Pelicula> obtenerPeliculas() {
        return peliculaRepository.findAll();
    }

    public void actualizarPelicula(Pelicula pelicula) {
        peliculaRepository.save(pelicula);
    }

    public void eliminarPelicula(Long id) {
        peliculaRepository.deleteById(id);
    }
}
