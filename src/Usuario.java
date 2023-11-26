import java.util.ArrayList;
import java.util.List;

interface Usuario {
    String nombre();
    String apellido();
    String email();
    String password();
    String tipo();
}

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


interface UsuarioFactory {
    Usuario crearUsuario(String nombre, String apellido, String email, String password);
    Usuario crearAdmin(String nombre, String apellido, String email, String password);
}

class UsuarioFactoryImpl implements UsuarioFactory {
    @Override
    public Usuario crearUsuario(String nombre, String apellido, String email, String password) {
        return new UsuarioImpl(nombre, apellido, email, password);
    }

    @Override
    public Usuario crearAdmin(String nombre, String apellido, String email, String password) {
        return new UsuarioImpl(nombre, apellido, email, password, "admin");
    }
}

interface UsuarioRepository {
    void agregarUsuario(Usuario usuario);
    void eliminarUsuario(Usuario usuario);
    Usuario buscarUsuario(String email);
    List<Usuario> listarUsuarios();
}

class UsuarioRepositoryImpl implements UsuarioRepository {
    private List<Usuario> usuarios;

    public UsuarioRepositoryImpl() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
    }

    @Override
    public Usuario buscarUsuario(String email) {
        for (Usuario usuario : this.usuarios) {
            if (usuario.email().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return this.usuarios;
    }
}