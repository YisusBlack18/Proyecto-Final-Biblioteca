package Interfaz;

import java.util.ArrayList;
import java.util.List;

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