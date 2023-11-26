interface Libro {
    void prestar();
    void devolver();
    boolean prestado();
    String titulo();
    String autor();
    int anio();
}

class LibroImpl implements Libro {
    private String titulo;
    private String autor;
    private int anio;
    private boolean prestado;

    public LibroImpl(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.prestado = false;
    }

    @Override
    public void prestar() {
        this.prestado = true;
    }

    @Override
    public void devolver() {
        this.prestado = false;
    }

    @Override
    public boolean prestado() {
        return this.prestado;
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