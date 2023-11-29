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

        Libro libro = new LibroImpl("El señor de las moscas", "William Golding", 1954);
        libroRepository.agregarLibro(libro);
        Usuario usuario = new UsuarioImpl("Juan", "Perez", "juan@gmail.com", "1234");
        usuarioRepository.agregarUsuario(usuario);
    }

    @Test
    void testPrestarLibro() {
        // prueba de prestamo de libro
        Libro libro = libroRepository.obtenerLibros().get(0);
        Usuario usuario = usuarioRepository.buscarUsuario("juan@gmail.com");

        prestamosRepository.prestarLibro(libro, usuario);
        List<Prestamo> prestamos = prestamosRepository.obtenerPrestamos();

        assertEquals(1, prestamos.size());
    }

    @Test
    void testDevolverLibro() {
        // prueba de devolucion de libro
        Libro libro = libroRepository.obtenerLibros().get(0);
        Usuario usuario = usuarioRepository.buscarUsuario("juan@gmail.com");

        prestamosRepository.prestarLibro(libro, usuario);
        prestamosRepository.devolverLibro(libro, usuario);
        List<Prestamo> prestamos = prestamosRepository.obtenerPrestamos();

        assertEquals(0, prestamos.size());
    }

    @Test
    void testObtenerPrestamos() {
        // prueba de obtener prestamos
        Libro libro = libroRepository.obtenerLibros().get(0);
        Usuario usuario = usuarioRepository.buscarUsuario("juan@gmail.com");

        prestamosRepository.prestarLibro(libro, usuario);
        List<Prestamo> prestamos = prestamosRepository.obtenerPrestamos();

        assertEquals(1, prestamos.size());
    }
}
