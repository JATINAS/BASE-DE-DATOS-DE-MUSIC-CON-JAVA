package com.ebac.modulo39.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArtista;
    private String nombre;
    @OneToMany(
            mappedBy = "artista",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Album> albums = new ArrayList<>();
}