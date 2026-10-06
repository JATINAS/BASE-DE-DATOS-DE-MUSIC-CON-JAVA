package com.ebac.modulo63.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PeliculaControllerTest {

//    @Mock
//    PeliculaService peliculaService;
//
//    @InjectMocks
//    PeliculaController peliculaController;
//
//    @Test
//    void obtenerPeliculas() {
//        int peliculas = 4;
//        List<Pelicula> peliculasListExpected = crearPeliculas(peliculas);
//
//        //Comportamiento del mock
//        when(peliculaService.obtenerPeliculas()).thenReturn(peliculasListExpected);
//
//        //Ejecutar metodo
//        List<Pelicula> peliculaListActual = peliculaController.obtenerPeliculas();
//
//        //Validar los resultados
//        assertEquals(peliculas, peliculaListActual.size());
//        assertEquals(peliculasListExpected, peliculaListActual);
//    }
//
//    @Test
//    void obtenerPeliculaPorId() {
//        long idPelicula = 1;
//        Optional<Pelicula> peliculaExpected = Optional.of(crearPeliculas(1).get(0));
//
//        //Comportamiento del mock
//        when(peliculaService.obtenerPeliculaPorId(idPelicula)).thenReturn(peliculaExpected);
//
//        //Ejecutar el metodo
//        ResponseEntity<Pelicula> peliculaResponseEntity = peliculaController.obtenerPeliculaPorId(idPelicula);
//        Pelicula peliculaActual = peliculaResponseEntity.getBody();
//
//        //Validar
//        assertEquals(200, peliculaResponseEntity.getStatusCode().value());
//        assertNotNull(peliculaActual);
//        assertEquals("Titulo1", peliculaActual.getTitulo());
//    }
//
//    @Test
//    void crearPelicula() throws URISyntaxException {
//        Pelicula peliculaExpected = crearPeliculas(1).get(0);
//
//        //Comportamiento del mock
//        when(peliculaService.crearPelicula(peliculaExpected)).thenReturn(peliculaExpected);
//
//        //Ejecutar metodos
//        ResponseEntity<Pelicula> peliculaResponseEntity = peliculaController.crearPelicula(peliculaExpected);
//        Pelicula peliculaActual = peliculaResponseEntity.getBody();
//
//        //Validar
//        assertEquals(201, peliculaResponseEntity.getStatusCode().value());
//        assertTrue(Objects.isNull(peliculaActual));
//    }
//
//    @Test
//    void actualizarPelicula() {
//        int idpelicula = 4;
//        String tituloActualizado = "Nuevo";
//        int estrenoActualizado = 2027;
//
//        Pelicula peliculaAntigua = new Pelicula();
//        peliculaAntigua.setIdPelicula(idpelicula);
//        peliculaAntigua.setTitulo("Viejo");
//        peliculaAntigua.setEstreno(2018);
//
//        Pelicula peliculaActualizada = new Pelicula();
//        peliculaActualizada.setTitulo(tituloActualizado);
//        peliculaActualizada.setEstreno(estrenoActualizado);
//
//        //Comportamiento del mock
//        when(peliculaService.obtenerPeliculaPorId((long) idpelicula)).thenReturn(Optional.of(peliculaAntigua));
//        doNothing().when(peliculaService).actualizarPelicula(peliculaActualizada);
//
//        //Ejecutar el metodo
//        ResponseEntity<Pelicula> peliculaResponseEntity = peliculaController.actualizarPelicula((long) idpelicula, peliculaActualizada);
//        Pelicula peliculaActual = peliculaResponseEntity.getBody();
//
//        //Validar
//        assertEquals(200, peliculaResponseEntity.getStatusCode().value());
//        assertNotNull(peliculaActual);
//        assertEquals(idpelicula, peliculaActual.getIdPelicula());
//        assertEquals(tituloActualizado, peliculaActual.getTitulo());
//        assertEquals(estrenoActualizado, peliculaActual.getEstreno());
//    }
//
//    @Test
//    void eliminarPelicula() {
//        long idPelicula = 2;
//
//        //Comportamiento del mock
//        doNothing().when(peliculaService).eliminarPelicula(idPelicula);
//
//        //Ejecutar metodos
//        ResponseEntity<Void> responseEntity = peliculaController.eliminarPelicula(idPelicula);
//
//        //Validar
//        assertEquals(204, responseEntity.getStatusCode().value());
//        verify(peliculaService, atLeastOnce()).eliminarPelicula(idPelicula);
//    }
//
//    private List<Pelicula> crearPeliculas (int elementos) {
//        return IntStream.range(1, elementos + 1)
//                .mapToObj(i -> {
//                    Pelicula pelicula = new Pelicula();
//                    pelicula.setIdPelicula(i);
//                    pelicula.setTitulo("Titulo" + i);
//                    pelicula.setEstreno(2020 + i);
//                    return pelicula;
//                }).collect(Collectors.toList());
//    }
}