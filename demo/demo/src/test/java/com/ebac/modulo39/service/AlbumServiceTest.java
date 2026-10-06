package com.ebac.modulo39.service;

import com.ebac.modulo39.dto.Album;
import com.ebac.modulo39.dto.Artista;
import com.ebac.modulo39.repository.AlbumRepository;
import com.ebac.modulo39.repository.ArtistaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private ArtistaRepository artistaRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    void obtenerAlbums() {
        Album album1 = new Album();
        album1.setIdAlbum(1);
        album1.setNombre("Don't Click Play");
        album1.setLanzamiento(2025);

        Album album2 = new Album();
        album2.setIdAlbum(2);
        album2.setNombre("Diamonds & Dancefloors");
        album2.setLanzamiento(2023);

        List<Album> albums = java.util.List.of(album1, album2);
        when(albumRepository.findAll()).thenReturn(albums);
        List<Album> resultado = albumService.obtenerAlbums();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Don't Click Play", resultado.get(0).getNombre());
        assertEquals("Diamonds & Dancefloors", resultado.get(1).getNombre());

        verify(albumRepository).findAll();
    }

    @Test
    void crearAlbum() throws Exception {
        Artista artista = new Artista();
        artista.setIdArtista(1L);
        artista.setNombre("Ava Max");

        Album album = new Album();
        album.setNombre("Don't Click Play");
        album.setLanzamiento(2025);
        album.setArtista(artista);

        when(artistaRepository.findById(1L)).thenReturn(Optional.of(artista));
        when(albumRepository.save(album)).thenReturn(album);

        Album resultado = albumService.crearAlbum(album);

        assertNotNull(resultado);
        assertEquals("Don't Click Play", resultado.getNombre());
        assertEquals(2025, resultado.getLanzamiento());
        assertEquals(artista, resultado.getArtista());

        verify(artistaRepository).findById(1L);
        verify(albumRepository).save(album);
    }

    @Test
    void crearAlbum_artistaNoExiste() {
        Artista artista = new Artista();
        artista.setIdArtista(99L);

        Album album = new Album();
        album.setNombre("Álbum de prueba");
        album.setLanzamiento(2025);
        album.setArtista(artista);

        when(artistaRepository.findById(99L)).thenReturn(java.util.Optional.empty());
        assertThrows(Exception.class, () -> albumService.crearAlbum(album));

        verify(artistaRepository).findById(99L);
    }

    @Test
    void obtenerAlbumPorId() {
        Long id = 1L;

        Artista artista = new Artista();
        artista.setIdArtista(1L);
        artista.setNombre("Ava Max");

        Album album = new Album();
        album.setIdAlbum(Math.toIntExact(id));
        album.setNombre("Don't Click Play");
        album.setLanzamiento(2025);
        album.setArtista(artista);

        when(albumRepository.findById(id)).thenReturn(Optional.of(album));
        java.util.Optional<Album> resultado = albumService.obtenerAlbumPorId(id);

        assertNotNull(resultado);
        assertTrue(resultado.isPresent());
        assertEquals("Don't Click Play", resultado.get().getNombre());
        assertEquals(2025, resultado.get().getLanzamiento());
        assertEquals(artista, resultado.get().getArtista());

        verify(albumRepository).findById(id);
    }

    @Test
    void obtenerAlbumPorId_albumNoExiste() {
        Long id = 99L;

        when(albumRepository.findById(id)).thenReturn(java.util.Optional.empty());
        Optional<Album> resultado = albumService.obtenerAlbumPorId(id);

        assertNotNull(resultado);
        assertFalse(resultado.isPresent());

        verify(albumRepository).findById(id);
    }

    @Test
    void actualizarAlbum() {
        Long idAlbum = 1L;

        Artista artista = new Artista();
        artista.setIdArtista(2L);
        artista.setNombre("Nuevo Artista");

        Album album = new Album();
        album.setIdAlbum(Math.toIntExact(idAlbum));
        album.setNombre("Álbum actualizado");
        album.setLanzamiento(2026);
        album.setArtista(artista);

        when(artistaRepository.findById(2L)).thenReturn(Optional.of(artista));
        when(albumRepository.save(album)).thenReturn(album);
        Album resultado = albumService.actualizarAlbum(album);

        assertNotNull(resultado);
        assertEquals(idAlbum, resultado.getIdAlbum());
        assertEquals("Álbum actualizado", resultado.getNombre());
        assertEquals(2026, resultado.getLanzamiento());
        assertEquals(artista, resultado.getArtista());

        verify(artistaRepository).findById(2L);
        verify(albumRepository).save(album);
    }

    @Test
    void eliminarAlbum() {
        Long idAlbum = 1L;
        albumService.eliminarAlbum(idAlbum);
        verify(albumRepository).deleteById(idAlbum);
    }

    @Test
    void buscarPorArtista() {
        String nombreArtista = "Ava";

        Album album1 = new Album();
        album1.setIdAlbum(1);
        album1.setNombre("Don't Click Play");
        album1.setLanzamiento(2025);

        Album album2 = new Album();
        album2.setIdAlbum(2);
        album2.setNombre("Diamonds & Dancefloors");
        album2.setLanzamiento(2023);

        List<Album> albums = List.of(album1, album2);
        when(albumRepository.findByArtista_NombreContainingIgnoreCase(nombreArtista)).thenReturn(albums);
        List<Album> resultado = albumService.buscarPorArtista(nombreArtista);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Don't Click Play", resultado.get(0).getNombre());
        assertEquals("Diamonds & Dancefloors", resultado.get(1).getNombre());

        verify(albumRepository).findByArtista_NombreContainingIgnoreCase(nombreArtista);
    }
}