package Interfaz;

class UsuarioImpl implements Usuario {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String tipo;

    public UsuarioImpl(String nombre, String apellido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.tipo = "usuario";
    }

    public UsuarioImpl(String nombre, String apellido, String email, String password, String tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
    }

    @Override
    public String nombre() {
        return this.nombre;
    }

    @Override
    public String apellido() {
        return this.apellido;
    }

    @Override
    public String email() {
        return this.email;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public String tipo() {
        return this.tipo;
    }

    @Override
    public String toString() {
        return this.nombre + " - " + this.apellido + " - " + this.email;
    }
}
