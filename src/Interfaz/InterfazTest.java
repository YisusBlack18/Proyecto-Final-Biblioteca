package Interfaz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import Interfaz.Interfaz;
import Interfaz.UsuarioImpl;
class InterfazTest {
//comit
    private Interfaz interfaz;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;

    @BeforeEach
    void setUp() {
        interfaz = new Interfaz();
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    void menuPrincipal() {
        setInputStream("1\n");
        System.setOut(new PrintStream(outputStream));
        interfaz.menuPrincipal();
        assertEquals("Bienvenido a la librería\n1. Iniciar sesión\n2. Registrarse\n3. Salir\n", outputStream.toString());
    }

    @Test
    void menuAdmin() {
        setInputStream("1\n");
        System.setOut(new PrintStream(outputStream));
        UsuarioImpl usuario = new UsuarioImpl("John", "Doe", "john.doe@example.com", "password", "admin");
        interfaz.menuAdmin(usuario);
        assertEquals("Bienvenido John Doe\n1. Administrar libros\n2. Administrar préstamos\n3. Administrar usuarios\n4. Salir\n", outputStream.toString());
    }
    @Test
    void menuLibros() {
        setInputStream("1\n");
        System.setOut(new PrintStream(outputStream));
        interfaz.menuLibros();
        assertEquals("1. Agregar libro\n2. Eliminar libro\n3. Listar libros\n4. Salir\n", outputStream.toString());
    }
    @Test
    void menuPrestamos() {
        setInputStream("1\n");
        System.setOut(new PrintStream(outputStream));
        interfaz.menuPrestamos();
        assertEquals("1. Prestar libro\n2. Devolver libro\n3. Listar préstamos\n4. Salir\n", outputStream.toString());
    }
    @Test
    void menuUsuarios() {
        setInputStream("1\n");
        System.setOut(new PrintStream(outputStream));
        interfaz.menuUsuarios();
        assertEquals("1. Agregar usuario\n2. Eliminar usuario\n3. Listar usuarios\n4. Salir\n", outputStream.toString());
    }
    @Test
    void menuUsuario() {
        setInputStream("1\n");
        System.setOut(new PrintStream(outputStream));
        UsuarioImpl usuario = new UsuarioImpl("John", "Doe", "john.doe@example.com", "password", "admin");
        interfaz.menuUsuario(usuario);
        assertEquals("Bienvenido John Doe\n1. Listar libros\n2. Prestar libro\n3. Devolver libro\n4. Listar préstamos\n5. Salir\n", outputStream.toString());
    }
    private void setInputStream(String input) {
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
}
