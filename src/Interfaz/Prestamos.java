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
        return Objects.equals(libro, prestamo.libro) &&
            Objects.equals(usuario, prestamo.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libro, usuario);
    }
}

interface PrestamosRepository {
    void prestarLibro(Libro libro, Usuario usuario);
    void devolverLibro(Libro libro, Usuario usuario);
    List<Prestamo> obtenerPrestamos();
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
        Prestamo prestamo = new Prestamo(libro, usuario);
        this.prestamos.remove(prestamo);
    }

    @Override
    public List<Prestamo> obtenerPrestamos() {
        return this.prestamos;
    }
}