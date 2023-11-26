import java.util.Scanner;

public class Interfaz {
    static Scanner scanner = new Scanner(System.in);

    public void menuPrincipal() {
        System.out.println("Bienvenido a la librería");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
    }

    public void menuAdmin(Usuario usuario) {
        System.out.println("Bienvenido " + usuario.nombre() + " " + usuario.apellido());
        System.out.println("1. Administrar libros");
        System.out.println("2. Administrar préstamos");
        System.out.println("3. Administrar usuarios");
        System.out.println("4. Salir");
    }

    public void menuLibros() {
        System.out.println("1. Agregar libro");
        System.out.println("2. Eliminar libro");
        System.out.println("3. Listar libros");
        System.out.println("4. Salir");
    }

    public void menuPrestamos() {
        System.out.println("1. Prestar libro");
        System.out.println("2. Devolver libro");
        System.out.println("3. Listar préstamos");
        System.out.println("4. Salir");
    }

    public void menuUsuarios() {
        System.out.println("1. Agregar usuario");
        System.out.println("2. Eliminar usuario");
        System.out.println("3. Listar usuarios");
        System.out.println("4. Salir");
    }

    public void menuUsuario(Usuario usuario) {
        System.out.println("Bienvenido " + usuario.nombre() + " " + usuario.apellido());
        System.out.println("1. Listar libros");
        System.out.println("2. Prestar libro");
        System.out.println("3. Devolver libro");
        System.out.println("4. Listar préstamos");
        System.out.println("5. Salir");
    }

    public void sistema(Libreria libreria) {
        Usuario usuario = null;
        int opcion = 0;
        while (opcion != 1 && opcion != 2) {
            menuPrincipal();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese su email");
                    String email = scanner.next();
                    System.out.println("Ingrese su contraseña");
                    String password = scanner.next();
                    usuario = libreria.login(email, password);
                    break;
                case 2:
                    System.out.println("Ingrese su nombre");
                    String nombre = scanner.next();
                    System.out.println("Ingrese su apellido");
                    String apellido = scanner.next();
                    System.out.println("Ingrese su email");
                    email = scanner.next();
                    System.out.println("Ingrese su contraseña");
                    password = scanner.next();
                    libreria.agregarUsuario(nombre, apellido, email, password);
                    usuario = libreria.login(email, password);
                    break;
                case 3:
                    System.out.println("Gracias por visitarnos");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
        if (usuario != null && usuario.tipo().equals("admin")) {
            sistemaAdmin(usuario);
        } else if (usuario != null && usuario.tipo().equals("usuario")) {
            sistemaUsuario(usuario);
        }
    }

    public void sistemaAdmin(Usuario usuario) {
        Libreria libreria = Libreria.getInstance();
        int opcion = 0;
        while (opcion != 4) {
            menuAdmin(usuario);
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuLibros();
                    opcion = scanner.nextInt();

                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese el título del libro");
                            String titulo = scanner.next();
                            System.out.println("Ingrese el autor del libro");
                            String autor = scanner.next();
                            System.out.println("Ingrese el año del libro");
                            int anio = scanner.nextInt();
                            libreria.agregarLibro(titulo, autor, anio);
                            break;
                        case 2:
                            System.out.println("Ingrese el ISBN del libro");
                            String isbn = scanner.next();
                            libreria.eliminarLibro(isbn);
                            break;
                        case 3:
                            libreria.listarLibros();
                            break;
                        case 4:
                            System.out.println("Gracias por visitarnos");
                            break;
                        default:
                            System.out.println("Opción inválida");
                            break;
                    }
                    break;
                case 2:
                    menuPrestamos();
                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese el ISBN del libro");
                            String isbn = scanner.next();
                            System.out.println("Ingrese el email del usuario");
                            String email = scanner.next();
                            libreria.prestarLibro(isbn, email);
                            break;
                        case 2:
                            System.out.println("Ingrese el ISBN del libro");
                            isbn = scanner.next();
                            System.out.println("Ingrese el email del usuario");
                            email = scanner.next();
                            libreria.devolverLibro(isbn, email);
                            break;
                        case 3:
                            libreria.listarPrestamos();
                            break;
                        case 4:
                            System.out.println("Gracias por visitarnos");
                            break;
                        default:
                            System.out.println("Opción inválida");
                            break;
                    }
                    break;
                case 3:
                    menuUsuarios();
                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese el nombre del usuario");
                            String nombre = scanner.next();
                            System.out.println("Ingrese el apellido del usuario");
                            String apellido = scanner.next();
                            System.out.println("Ingrese el email del usuario");
                            String email = scanner.next();
                            System.out.println("Ingrese la contraseña del usuario");
                            String password = scanner.next();
                            libreria.agregarUsuario(nombre, apellido, email, password);
                            break;
                        case 2:
                            System.out.println("Ingrese el email del usuario");
                            email = scanner.next();
                            libreria.eliminarUsuario(email);
                            break;
                        case 3:
                            libreria.listarUsuarios();
                            break;
                        case 4:
                            System.out.println("Gracias por visitarnos");
                            break;
                        default:
                            System.out.println("Opción inválida");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Gracias por visitarnos");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
        sistema(libreria);
    }

    public void sistemaUsuario(Usuario usuario) {
        Libreria libreria = Libreria.getInstance();
        int opcion = 0;
        while (opcion != 5) {
            menuUsuario(usuario);
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    libreria.listarLibros();
                    break;
                case 2:
                    System.out.println("Ingrese el ISBN del libro");
                    String isbn = scanner.next();
                    libreria.prestarLibro(isbn, usuario.email());
                    break;
                case 3:
                    System.out.println("Ingrese el ISBN del libro");
                    isbn = scanner.next();
                    libreria.devolverLibro(isbn, usuario.email());
                    break;
                case 4:
                    libreria.listarPrestamos();
                    break;
                case 5:
                    System.out.println("Gracias por visitarnos");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
        sistema(libreria);
    }
}
