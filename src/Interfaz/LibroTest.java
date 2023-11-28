package Interfaz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LibroTest {

    private LibroRepository libroRepository;
    private LibroFactory libroFactory;

    @BeforeEach
    void setUp() {
        libroRepository = new LibroRepositoryImpl();
        libroFactory = new LibroFactoryImpl();
    }

    @Test
    void testAgregarYObtenerLibro() {
        Libro libro = libroFactory.crearLibro("El Señor de los Anillos", "J.R.R. Tolkien", 1954);
        libroRepository.agregarLibro(libro);

        Libro libroObtenido = libroRepository.obtenerLibro(libro.isbn());

        assertEquals(libro, libroObtenido);
    }

    @Test
    void testEliminarLibro() {
        Libro libro = libroFactory.crearLibro("Harry Potter", "J.K. Rowling", 1997);
        libroRepository.agregarLibro(libro);

        libroRepository.eliminarLibro(libro);

        assertNull(libroRepository.obtenerLibro(libro.isbn()));
    }

    @Test
    void testObtenerLibros() {
        Libro libro1 = libroFactory.crearLibro("1984", "George Orwell", 1949);
        Libro libro2 = libroFactory.crearLibro("Cien años de soledad", "Gabriel García Márquez", 1967);

        libroRepository.agregarLibro(libro1);
        libroRepository.agregarLibro(libro2);

        List<Libro> libros = libroRepository.obtenerLibros();

        assertEquals(2, libros.size());
        assertTrue(libros.contains(libro1));
        assertTrue(libros.contains(libro2));
    }
}
