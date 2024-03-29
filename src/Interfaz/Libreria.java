package Interfaz;

import java.util.List;

public class Libreria {
    private static Libreria instance;

    LibroFactory libroFactory = new LibroFactoryImpl();
    LibroRepository libroRepository = new LibroRepositoryImpl();
    UsuarioFactory usuarioFactory = new UsuarioFactoryImpl();
    UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();
    PrestamosRepository prestamosRepository = new PrestamosRepositoryImpl();

    private Libreria() {
        // Constructor privado para evitar la instanciación directa
        agregarLibro("El señor de las moscas", "William Golding", 1954);
        agregarLibro("El nombre de la rosa", "Umberto Eco", 1980);
        agregarLibro("El retrato de Dorian Gray", "Oscar Wilde", 1890);

        agregarUsuario("Juan", "Perez", "juan@gmail.com", "1234");
        agregarUsuario("Maria", "Gomez", "maria@gmail.com", "1234");
        agregarAdmin("Admin", "Admin", "admin@gmail.com", "1234");
    }

    public static Libreria getInstance() {
        if (instance == null) {
            instance = new Libreria();
        }
        return instance;
    }

    // Métodos y propiedades de la biblioteca
    
    // Administrar libros
    public void agregarLibro(String titulo, String autor, int anio) {
        Libro libro = libroFactory.crearLibro(titulo, autor, anio);
        libroRepository.agregarLibro(libro);
    }

    public void eliminarLibro(String isbn) {
        Libro libro = libroRepository.obtenerLibro(isbn);
        libroRepository.eliminarLibro(libro);
    }

    public Libro obtenerLibro(String isbn) {
        Libro libro = libroRepository.obtenerLibro(isbn);
        if (libro == null) {
            libro = prestamosRepository.obtenerLibroPrestado(isbn);
        }
        return libro;
    }

    public void listarLibros() {
        List<Libro> libros = libroRepository.obtenerLibros();
        for (Libro libro : libros) {
            System.out.println(libro.titulo() + " - " + libro.autor() + " - " + libro.anio());
        }
    }

    public List<Libro> obtenerLibros() {
        List<Libro> libros = libroRepository.obtenerLibros();
        return libros;
    }

    // Administrar préstamos
    public void prestarLibro(String isbn, String email) {
        Libro libro = obtenerLibro(isbn);
        Usuario usuario = obtenerUsuario(email);
        libroRepository.eliminarLibro(libro);
        prestamosRepository.prestarLibro(libro, usuario);
    }

    public void devolverLibro(String isbn, String email) {
        Libro libro = obtenerLibro(isbn);
        Usuario usuario = obtenerUsuario(email);
        prestamosRepository.devolverLibro(libro, usuario);
        libroRepository.agregarLibro(libro);
    }

    public void listarPrestamos() {
        List<Prestamo> prestamos = prestamosRepository.obtenerPrestamos();
        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo.libro().titulo() + " - " + prestamo.usuario().nombre() + " - " + prestamo.usuario().apellido());
        }
    }

    public Prestamo obtenerPrestamo(String isbn, String email) {
        Prestamo prestamo = prestamosRepository.obtenerPrestamo(isbn, email);
        return prestamo;
    }

    public List<Prestamo> obtenerPrestamos() {
        List<Prestamo> prestamos = prestamosRepository.obtenerPrestamos();
        return prestamos;
    }

    public List<Libro> obtenerLibrosPrestados() {
        List<Libro> libros = prestamosRepository.obtenerLibrosPrestados();
        return libros;
    }

    public List<Usuario> obtenerUsuariosPrestados() {
        List<Usuario> usuarios = prestamosRepository.obtenerUsuariosPrestados();
        return usuarios;
    }

    // Administrar usuarios
    public void agregarUsuario(String nombre, String apellido, String email, String password) {
        Usuario usuario = usuarioFactory.crearUsuario(nombre, apellido, email, password);
        usuarioRepository.agregarUsuario(usuario);
    }

    public void agregarAdmin(String nombre, String apellido, String email, String password) {
        Usuario usuario = usuarioFactory.crearAdmin(nombre, apellido, email, password);
        usuarioRepository.agregarUsuario(usuario);
    }

    public void eliminarUsuario(String email) {
        Usuario usuario = usuarioRepository.buscarUsuario(email);
        usuarioRepository.eliminarUsuario(usuario);
    }

    public void listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.listarUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.nombre() + " - " + usuario.apellido() + " - " + usuario.email());
        }
    }

    public Usuario obtenerUsuario(String email) {
        Usuario usuario = usuarioRepository.buscarUsuario(email);
        return usuario;
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.listarUsuarios();
        return usuarios;
    }

    public Usuario login(String email, String password) {
        Usuario usuario = usuarioRepository.buscarUsuario(email);
        if (usuario != null && usuario.password().equals(password)) {
            return usuario;
        }
        return null;
    }
}
