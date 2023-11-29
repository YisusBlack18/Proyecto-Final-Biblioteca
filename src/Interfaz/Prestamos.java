package Interfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Prestamo {
    private Libro libro;
    private Usuario usuario;

    public Prestamo(Libro libro, Usuario usuario) {
        this.libro = libro;
        this.usuario = usuario;
    }

    public Libro libro() {
        return this.libro;
    }

    public Usuario usuario() {
        return this.usuario;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Prestamo prestamo = (Prestamo) obj;
        return Objects.equals(libro, prestamo.libro()) &&
            Objects.equals(usuario, prestamo.usuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(libro, usuario);
    }
}

interface PrestamosRepository {
    void prestarLibro(Libro libro, Usuario usuario);
    void devolverLibro(Libro libro, Usuario usuario);
    Libro obtenerLibroPrestado(String isbn);
    List<Prestamo> obtenerPrestamos();
    Prestamo obtenerPrestamo(String isbn, String email);
    List<Libro> obtenerLibrosPrestados();
}

class PrestamosRepositoryImpl implements PrestamosRepository {
    private List<Prestamo> prestamos;

    public PrestamosRepositoryImpl() {
        this.prestamos = new ArrayList<>();
    }

    @Override
    public void prestarLibro(Libro libro, Usuario usuario) {
        Prestamo prestamo = new Prestamo(libro, usuario);
        this.prestamos.add(prestamo);
    }

    @Override
    public void devolverLibro(Libro libro, Usuario usuario) {
        Prestamo prestamo = obtenerPrestamo(libro.isbn(), usuario.email());
        if (prestamo != null) {
            this.prestamos.remove(prestamo);
        }
    }

    @Override
    public Prestamo obtenerPrestamo(String isbn, String email) {
        for (Prestamo prestamo : this.prestamos) {
            if (prestamo.libro().isbn().equals(isbn) && prestamo.usuario().email().equals(email)) {
                return prestamo;
            }
        }
        return null;
    }

    @Override
    public List<Prestamo> obtenerPrestamos() {
        return this.prestamos;
    }

    @Override
    public Libro obtenerLibroPrestado(String isbn) {
        for (Prestamo prestamo : this.prestamos) {
            if (prestamo.libro().isbn().equals(isbn)) {
                return prestamo.libro();
            }
        }
        return null;
    }

    @Override
    public List<Libro> obtenerLibrosPrestados() {
        List<Libro> libros = new ArrayList<>();
        for (Prestamo prestamo : this.prestamos) {
            libros.add(prestamo.libro());
        }
        return libros;
    }
}