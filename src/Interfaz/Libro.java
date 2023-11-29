package Interfaz;

import java.util.ArrayList;
import java.util.List;

interface Libro {
    String isbn();
    String titulo();
    String autor();
    int anio();
}

class LibroImpl implements Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int anio;

    public LibroImpl(String titulo, String autor, int anio) {
        this.isbn = randomString(10);
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    private String randomString(int i) {
        return "B"+Math.random() * 1000000000;
    }

    @Override
    public String isbn() {
        return this.isbn;
    }

    @Override
    public String titulo() {
        return this.titulo;
    }

    @Override
    public String autor() {
        return this.autor;
    }

    @Override
    public int anio() {
        return this.anio;
    }

    @Override
    public String toString() {
        return this.isbn + " - " + this.titulo + " - " + this.autor + " - " + this.anio;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Libro) {
            Libro libro = (Libro) obj;
            return this.isbn.equals(libro.isbn());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.isbn.hashCode();
    }
}

interface LibroFactory {
    Libro crearLibro(String titulo, String autor, int anio);
}

class LibroFactoryImpl implements LibroFactory {
    @Override
    public Libro crearLibro(String titulo, String autor, int anio) {
        return new LibroImpl(titulo, autor, anio);
    }
}

interface LibroRepository {
    void agregarLibro(Libro libro);
    void eliminarLibro(Libro libro);
    Libro obtenerLibro(String isbn);
    List<Libro> obtenerLibros();
}

class LibroRepositoryImpl implements LibroRepository {
    private List<Libro> libros;

    public LibroRepositoryImpl() {
        this.libros = new ArrayList<>();
    }

    @Override
    public void agregarLibro(Libro libro) {
        this.libros.add(libro);
    }

    @Override
    public void eliminarLibro(Libro libro) {
        this.libros.remove(libro);
    }

    @Override
    public Libro obtenerLibro(String isbn) {
        for (Libro libro : this.libros) {
            if (libro.isbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    @Override
    public List<Libro> obtenerLibros() {
        return this.libros;
    }
}