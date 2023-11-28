package Interfaz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuariosTest {
    private UsuarioFactoryImpl usuarioFactory;
    private UsuarioRepositoryImpl usuarioRepository;

    @BeforeEach
    void setUp() {
        usuarioFactory = new UsuarioFactoryImpl();
        usuarioRepository = new UsuarioRepositoryImpl();
    }

    @Test
    void testAgregarAdmin() {
        Usuario usuario = usuarioFactory.crearAdmin("Administrador", "Segundo", "admin2@gmail.com", "1234");
        usuarioRepository.agregarUsuario(usuario);
        List<Usuario> usuarios = usuarioRepository.listarUsuarios();
        assertTrue(usuarios.contains(usuario));
    }

    @Test
    void testAgregarUsuario() {
        Usuario usuariotest = usuarioFactory.crearUsuario("Alejandra", "Quirarte", "ale@gmail.com", "1234");
        usuarioRepository.agregarUsuario(usuariotest);

        List<Usuario> usuarios = usuarioRepository.listarUsuarios();

        assertEquals(1, usuarios.size());
        assertTrue(usuarios.contains(usuariotest));
    }

    @Test
    void testEliminarUsuario() {
        Usuario usuariotest = usuarioFactory.crearUsuario("Pedro", "Hernandez", "pedro@gmail.com", "1234");
        usuarioRepository.agregarUsuario(usuariotest);
        usuarioRepository.eliminarUsuario(usuariotest);

        List<Usuario> usuarios = usuarioRepository.listarUsuarios();
        assertFalse(usuarios.contains(usuariotest));

    }

    @Test
    void testVerificarTipoUsuario() {
        Usuario usuariotest = usuarioFactory.crearUsuario("Pedro", "Hernandez", "pedro@gmail.com", "1234");
        Usuario admin = usuarioFactory.crearAdmin("Administrador", "Segundo", "admin2@gmail.com", "1234");
        usuarioRepository.agregarUsuario(usuariotest);
        usuarioRepository.agregarUsuario(admin);

        assertEquals("usuario", usuariotest.tipo());
        assertEquals("admin", admin.tipo());
    }

    @Test
    void testBuscarUsuario() {
        Usuario usuarioBuscado = usuarioFactory.crearUsuario("Carlos", "Gonzalez", "carlos@gmail.com", "1234");
        usuarioRepository.agregarUsuario(usuarioBuscado);

        Usuario usuarioEncontrado = usuarioRepository.buscarUsuario("carlos@gmail.com");

        assertNotNull(usuarioEncontrado);
        assertEquals(usuarioBuscado.nombre(), usuarioEncontrado.nombre());
        assertEquals(usuarioBuscado.tipo(), usuarioEncontrado.tipo());
    }
}
