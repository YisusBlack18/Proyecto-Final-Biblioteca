package Interfaz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Autor: Luis Romo
 */

public class PrestamoTest {

    private PrestamosRepository prestamosRepository;
    private LibroRepository libroRepository;
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        prestamosRepository = new PrestamosRepositoryImpl();
        libroRepository = new LibroRepositoryImpl();
        usuarioRepository = new UsuarioRepositoryImpl();
    }

    @Test
    void testPrestarLibro() {
        Libro libro = libroRepository.obtenerLibro("1954");
        Usuario usuario = usuarioRepository.buscarUsuario("admin@gmail.com");
        prestamosRepository.prestarLibro(libro, usuario);

        List<Prestamo> prestamos = prestamosRepository.obtenerPrestamos();
        Prestamo prestamoTest = prestamos.get(prestamos.size() - 1);

        assertEquals(libro, prestamoTest.libro());
    }

    @Test
    void testDevolverLibro() {
        Libro libro = libroRepository.obtenerLibro("1954");
        Usuario usuario = usuarioRepository.buscarUsuario("admin@gmail.com");

        prestamosRepository.prestarLibro(libro, usuario);

        List<Prestamo> prestamos = prestamosRepository.obtenerPrestamos();
        Prestamo prestamoTest = prestamos.get(prestamos.size() - 1);

        prestamosRepository.devolverLibro(libro, usuario);


        assertEquals(libro,prestamoTest.libro());
    }
}
