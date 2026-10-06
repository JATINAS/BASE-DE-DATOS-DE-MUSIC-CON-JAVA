package com.ebac.modulo39.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
@Data
@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlbum;
    private String nombre;
    private int lanzamiento;

    @ManyToOne
    @JoinColumn(name = "idArtista")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Artista artista;

    @JsonProperty("nombreArtista")
    public String getNombreArtista() {
        return artista != null ? artista.getNombre() : null;
    }
}


