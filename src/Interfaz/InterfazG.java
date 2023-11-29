package Interfaz;
import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class InterfazG extends JFrame {
    private JTextField emailField, passwordField, nombreField, apellidoField, isbnField, tituloField, autorField, anioField;
    private Libreria libreria;
    private Usuario usuario;

    public InterfazG() {
        libreria = Libreria.getInstance();

        setTitle("Librería");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new GridLayout(3, 1));

        mostrarPanelLogin();
    }

    private void mostrarPanelLogin() {
        // Panel de inicio de sesión y registro
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(5, 2));

        emailField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = passwordField.getText();
                usuario = libreria.login(email, password);
                if (usuario != null) {
                    if (usuario.tipo().equals("admin")) {
                        mostrarPanelAdmin();
                    } else if (usuario.tipo().equals("usuario")) {
                        mostrarPanelUsuario();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                }
            }
        });

        JButton registerButton = new JButton("Registrarse");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelRegistro();
            }
        });

        loginPanel.add(new JLabel("Email:"));
        loginPanel.add(emailField);
        loginPanel.add(new JLabel("Contraseña:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        setContentPane(loginPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelRegistro() {
        // Lógica para mostrar el panel de registro
        JPanel registroPanel = new JPanel();
        registroPanel.setLayout(new GridLayout(5, 2));

        nombreField = new JTextField();
        apellidoField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();

        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                libreria.agregarUsuario(nombre, apellido, email, password);
                JOptionPane.showMessageDialog(null, "Registro exitoso");
                usuario = libreria.login(email, password);
                if (usuario != null) {
                    mostrarPanelUsuario();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error al iniciar sesión");
                }
            }
        });

        JButton cancelarRegistroButton = new JButton("Cancelar");
        cancelarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelLogin();
            }
        });

        registroPanel.add(new JLabel("Nombre:"));
        registroPanel.add(nombreField);
        registroPanel.add(new JLabel("Apellido:"));
        registroPanel.add(apellidoField);
        registroPanel.add(new JLabel("Email:"));
        registroPanel.add(emailField);
        registroPanel.add(new JLabel("Contraseña:"));
        registroPanel.add(passwordField);
        registroPanel.add(registrarButton);
        registroPanel.add(cancelarRegistroButton);

        setContentPane(registroPanel);
        revalidate();
        repaint();
    }
    
    private void mostrarPanelAdmin() {
        // Lógica para mostrar el panel de administración
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new GridLayout(5, 1));
    
        JButton adminBooksButton = new JButton("Administrar libros");
        adminBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelLibros();
            }
        });
    
        JButton adminLoansButton = new JButton("Administrar préstamos");
        adminLoansButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelPrestamos();
            }
        });
    
        JButton adminUsersButton = new JButton("Administrar usuarios");
        adminUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelUsuarios();
            }
        });
    
        JButton adminExitButton = new JButton("Salir");
        adminExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelLogin();
            }
        });
    
        JLabel bienvenidoLabel = new JLabel("Bienvenido " + usuario.nombre() + " " + usuario.apellido());
        bienvenidoLabel.setHorizontalAlignment(JLabel.CENTER);

        adminPanel.add(bienvenidoLabel);
        adminPanel.add(adminBooksButton);
        adminPanel.add(adminLoansButton);
        adminPanel.add(adminUsersButton);
        adminPanel.add(adminExitButton);
    
        setContentPane(adminPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelUsuarios() {
        // Lógica para mostrar el panel de administración de usuarios
        JPanel usuariosPanel = new JPanel();
        usuariosPanel.setLayout(new GridLayout(5, 2));

        JButton agregarUsuarioButton = new JButton("Agregar usuario");
        agregarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para agregar un usuario
                mostrarPanelAgregarUsuario();
            }
        });

        JButton eliminarUsuarioButton = new JButton("Eliminar usuario");
        eliminarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para eliminar un usuario
                mostrarPanelEliminarUsuario();
            }
        });

        JButton listarUsuariosButton = new JButton("Listar usuarios");
        listarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para listar usuarios
                mostrarPanelListarUsuarios();
            }
        });

        JButton salirUsuariosButton = new JButton("Salir");
        salirUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelAdmin();  // Puedes cambiar esto según tu flujo de la aplicación
            }
        });
        
        usuariosPanel.add(agregarUsuarioButton);
        usuariosPanel.add(eliminarUsuarioButton);
        usuariosPanel.add(listarUsuariosButton);
        usuariosPanel.add(salirUsuariosButton);

        setContentPane(usuariosPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelAgregarUsuario() {
        // Lógica para mostrar el panel de agregar usuario
        JPanel agregarUsuarioPanel = new JPanel();
        agregarUsuarioPanel.setLayout(new GridLayout(6, 2));

        nombreField = new JTextField();
        apellidoField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();

        JComboBox<String> tipoComboBox = new JComboBox<String>(new String[] {"usuario", "admin"});
        String tipo = (String) tipoComboBox.getSelectedItem();

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para agregar un usuario
                if (nombreField.getText().equals("") || apellidoField.getText().equals("") || emailField.getText().equals("") || passwordField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                    return;
                } else if (tipo.equals("admin")) {
                    libreria.agregarAdmin(nombreField.getText(), apellidoField.getText(), emailField.getText(), passwordField.getText());
                    JOptionPane.showMessageDialog(null, "Administrador agregado exitosamente");
                } else if (tipo.equals("usuario")) {
                    libreria.agregarUsuario(nombreField.getText(), apellidoField.getText(), emailField.getText(), passwordField.getText());
                    JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente");
                }
            }
        });

        JButton cancelarAgregarButton = new JButton("Cancelar");
        cancelarAgregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelUsuarios();
            }
        });

        agregarUsuarioPanel.add(new JLabel("Nombre:"));
        agregarUsuarioPanel.add(nombreField);
        agregarUsuarioPanel.add(new JLabel("Apellido:"));
        agregarUsuarioPanel.add(apellidoField);
        agregarUsuarioPanel.add(new JLabel("Email:"));
        agregarUsuarioPanel.add(emailField);
        agregarUsuarioPanel.add(new JLabel("Contraseña:"));
        agregarUsuarioPanel.add(passwordField);
        agregarUsuarioPanel.add(new JLabel("Tipo:"));
        agregarUsuarioPanel.add(tipoComboBox);
        agregarUsuarioPanel.add(agregarButton);
        agregarUsuarioPanel.add(cancelarAgregarButton);

        setContentPane(agregarUsuarioPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelEliminarUsuario() {
        // Lógica para mostrar el panel de eliminar usuario
        JPanel eliminarUsuarioPanel = new JPanel();
        eliminarUsuarioPanel.setLayout(new GridLayout(3, 2));

        emailField = new JTextField();

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para eliminar un usuario
                if (emailField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                    return;
                } else {
                    libreria.eliminarUsuario(emailField.getText());
                    JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
                }
            }
        });

        JButton cancelarEliminarButton = new JButton("Cancelar");
        cancelarEliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelUsuarios();
            }
        });

        eliminarUsuarioPanel.add(new JLabel("Email:"));
        eliminarUsuarioPanel.add(emailField);
        eliminarUsuarioPanel.add(eliminarButton);
        eliminarUsuarioPanel.add(cancelarEliminarButton);

        setContentPane(eliminarUsuarioPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelListarUsuarios() {
        // Lógica para mostrar el panel de listar usuarios
        JPanel listarUsuariosPanel = new JPanel();
        listarUsuariosPanel.setLayout(new GridLayout(5, 1));

        for (Usuario usuario : libreria.obtenerUsuarios()) {
            JLabel usuarioLabel = new JLabel(usuario.nombre() + " " + usuario.apellido() + " - " + usuario.email() + " - " + usuario.tipo());
            usuarioLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    StringSelection stringSelection = new StringSelection(usuario.email());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                    JOptionPane.showMessageDialog(null, "Email copiado al portapapeles");
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    usuarioLabel.setForeground(Color.BLUE);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    usuarioLabel.setForeground(Color.BLACK);
                }
            });
            listarUsuariosPanel.add(usuarioLabel);
        }

        JButton salirListarUsuariosButton = new JButton("Salir");
        salirListarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelUsuarios();
            }
        });

        listarUsuariosPanel.add(salirListarUsuariosButton);

        setContentPane(listarUsuariosPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelUsuario() {
        // Lógica para mostrar el panel de usuario
        JPanel usuarioPanel = new JPanel();
        usuarioPanel.setLayout(new GridLayout(6, 1));
    
        JButton listarLibrosButton = new JButton("Listar libros");
        listarLibrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para listar libros
                mostrarPanelListarLibrosUsuario();
            }
        });
    
        JButton prestarLibroButton = new JButton("Prestar libro");
        prestarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para prestar un libro
                mostrarPanelPrestarLibroUsuario();
            }
        });
    
        JButton devolverLibroButton = new JButton("Devolver libro");
        devolverLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para devolver un libro
                mostrarPanelDevolverLibroUsuario();
            }
        });
    
        JButton listarPrestamosButton = new JButton("Listar préstamos");
        listarPrestamosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para listar préstamos
                mostrarPanelListarPrestamosUsuario();
            }
        });
    
        JButton salirUsuarioButton = new JButton("Salir");
        salirUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelLogin();
            }
        });

        JLabel bienvenidoLabel = new JLabel("Bienvenido " + usuario.nombre() + " " + usuario.apellido());
        bienvenidoLabel.setHorizontalAlignment(JLabel.CENTER);
    
        usuarioPanel.add(bienvenidoLabel);
        usuarioPanel.add(listarLibrosButton);
        usuarioPanel.add(prestarLibroButton);
        usuarioPanel.add(devolverLibroButton);
        usuarioPanel.add(listarPrestamosButton);
        usuarioPanel.add(salirUsuarioButton);
    
        setContentPane(usuarioPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelLibros() {
        // Lógica para mostrar el panel de administración de libros
        JPanel librosPanel = new JPanel();
        librosPanel.setLayout(new GridLayout(5, 1));
    
        JButton addBookButton = new JButton("Agregar libro");
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para agregar un libro
                mostrarPanelAgregarLibro();
            }
        });
    
        JButton deleteBookButton = new JButton("Eliminar libro");
        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para eliminar un libro
                mostrarPanelEliminarLibro();
            }
        });
    
        JButton listBooksButton = new JButton("Listar libros");
        listBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para listar libros
                mostrarPanelListarLibros();
            }
        });
    
        JButton exitBooksButton = new JButton("Salir");
        exitBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelAdmin();
            }
        });
    
        librosPanel.add(addBookButton);
        librosPanel.add(deleteBookButton);
        librosPanel.add(listBooksButton);
        librosPanel.add(exitBooksButton);
    
        setContentPane(librosPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelAgregarLibro() {
        // Lógica para mostrar el panel de agregar libro
        JPanel agregarLibroPanel = new JPanel();
        agregarLibroPanel.setLayout(new GridLayout(5, 2));

        nombreField = new JTextField();
        apellidoField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para agregar un libro
                if (tituloField.getText().equals("") || autorField.getText().equals("") || anioField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                    return;
                } else if (Integer.parseInt(anioField.getText()) < 0) {
                    JOptionPane.showMessageDialog(null, "El año debe ser mayor a 0");
                    return;
                } else {
                    libreria.agregarLibro(tituloField.getText(), autorField.getText(), Integer.parseInt(anioField.getText()));
                    JOptionPane.showMessageDialog(null, "Libro agregado exitosamente");
                }
                
            }
        });

        JButton cancelarAgregarButton = new JButton("Cancelar");
        cancelarAgregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelLibros();
            }
        });

        agregarLibroPanel.add(new JLabel("Título:"));
        agregarLibroPanel.add(tituloField);
        agregarLibroPanel.add(new JLabel("Autor:"));
        agregarLibroPanel.add(autorField);
        agregarLibroPanel.add(new JLabel("Año:"));
        agregarLibroPanel.add(anioField);
        agregarLibroPanel.add(agregarButton);
        agregarLibroPanel.add(cancelarAgregarButton);

        setContentPane(agregarLibroPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelEliminarLibro() {
        // Lógica para mostrar el panel de eliminar libro
        JPanel eliminarLibroPanel = new JPanel();
        eliminarLibroPanel.setLayout(new GridLayout(3, 2));

        isbnField = new JTextField();

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para eliminar un libro
                if (isbnField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                    return;
                } else {
                    libreria.eliminarLibro(isbnField.getText());
                    JOptionPane.showMessageDialog(null, "Libro eliminado exitosamente");
                }
            }
        });

        JButton cancelarEliminarButton = new JButton("Cancelar");
        cancelarEliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelLibros();
            }
        });

        eliminarLibroPanel.add(new JLabel("ISBN:"));
        eliminarLibroPanel.add(isbnField);
        eliminarLibroPanel.add(eliminarButton);
        eliminarLibroPanel.add(cancelarEliminarButton);

        setContentPane(eliminarLibroPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelListarLibros() {
        // Lógica para mostrar el panel de listar libros
        JPanel listarLibrosPanel = new JPanel();
        listarLibrosPanel.setLayout(new GridLayout(5, 1));

        for (Libro libro : libreria.obtenerLibros()) {
            JLabel libroLabel = new JLabel(libro.isbn() + " - " + libro.titulo() + " - " + libro.autor() + " - " + libro.anio());
            libroLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    StringSelection stringSelection = new StringSelection(libro.isbn());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                    JOptionPane.showMessageDialog(null, "ISBN copiado al portapapeles");
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    libroLabel.setForeground(Color.BLUE);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    libroLabel.setForeground(Color.BLACK);
                }
            });
            listarLibrosPanel.add(libroLabel);
        }

        JButton salirListarLibrosButton = new JButton("Salir");
        salirListarLibrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelLibros();
            }
        });

        listarLibrosPanel.add(salirListarLibrosButton);

        setContentPane(listarLibrosPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelListarLibrosUsuario() {
        // Lógica para mostrar el panel de listar libros
        JPanel listarLibrosPanel = new JPanel();
        listarLibrosPanel.setLayout(new GridLayout(5, 1));

        for (Libro libro : libreria.obtenerLibros()) {
            JLabel libroLabel = new JLabel(libro.isbn() + " - " + libro.titulo() + " - " + libro.autor() + " - " + libro.anio());
            libroLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    StringSelection stringSelection = new StringSelection(libro.isbn());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                    JOptionPane.showMessageDialog(null, "ISBN copiado al portapapeles");
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    libroLabel.setForeground(Color.BLUE);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    libroLabel.setForeground(Color.BLACK);
                }
            });
            listarLibrosPanel.add(libroLabel);
        }

        JButton salirListarLibrosButton = new JButton("Salir");
        salirListarLibrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelUsuario();
            }
        });

        listarLibrosPanel.add(salirListarLibrosButton);

        setContentPane(listarLibrosPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelPrestamos() {
        // Lógica para mostrar el panel de administración de préstamos
        JPanel prestamosPanel = new JPanel();
        prestamosPanel.setLayout(new GridLayout(5, 1));

        JButton prestarLibroButton = new JButton("Prestar libro");
        prestarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para prestar un libro
                mostrarPanelPrestarLibroAdmin();
            }
        });

        JButton devolverLibroButton = new JButton("Devolver libro");
        devolverLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para devolver un libro
                mostrarPanelDevolverLibroAdmin();
            }
        });

        JButton listarPrestamosButton = new JButton("Listar préstamos");
        listarPrestamosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para listar préstamos
                mostrarPanelListarPrestamosAdmin();
            }
        });

        JButton salirPrestamosButton = new JButton("Salir");
        salirPrestamosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelAdmin();  // Puedes cambiar esto según tu flujo de la aplicación
            }
        });

        prestamosPanel.add(prestarLibroButton);
        prestamosPanel.add(devolverLibroButton);
        prestamosPanel.add(listarPrestamosButton);
        prestamosPanel.add(salirPrestamosButton);

        setContentPane(prestamosPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelPrestarLibroAdmin() {
        // Lógica para mostrar el panel de prestar libro
        JPanel prestarLibroPanel = new JPanel();
        prestarLibroPanel.setLayout(new GridLayout(5, 2));

        isbnField = new JTextField();
        emailField = new JTextField();

        JButton prestarButton = new JButton("Prestar");
        prestarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para prestar un libro
                String isbn = isbnField.getText();
                String email = emailField.getText();
                if (isbn.equals("") || email.equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                    return;
                }

                Libro libro = libreria.obtenerLibro(isbn);
                Usuario usuario = libreria.obtenerUsuario(email);
                if (libro != null && usuario != null) {
                    Prestamo nuevoPrestamo = new Prestamo(libro, usuario);
                    if (libreria.obtenerPrestamos().contains(nuevoPrestamo)) {
                        JOptionPane.showMessageDialog(null, "El libro ya está prestado");
                    } else {
                        libreria.prestarLibro(isbn, email);
                        if (libreria.obtenerPrestamos().contains(nuevoPrestamo)) {
                            JOptionPane.showMessageDialog(null, "Libro prestado exitosamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "Ocurrió un error al prestar el libro");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El libro o el usuario no existen");
                    return;
                }
            }
        });

        JButton cancelarPrestarButton = new JButton("Cancelar");
        cancelarPrestarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelPrestamos();
            }
        });

        prestarLibroPanel.add(new JLabel("ISBN:"));
        prestarLibroPanel.add(isbnField);
        prestarLibroPanel.add(new JLabel("Email:"));
        prestarLibroPanel.add(emailField);
        prestarLibroPanel.add(prestarButton);
        prestarLibroPanel.add(cancelarPrestarButton);

        setContentPane(prestarLibroPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelPrestarLibroUsuario() {
        // Lógica para mostrar el panel de prestar libro
        JPanel prestarLibroPanel = new JPanel();
        prestarLibroPanel.setLayout(new GridLayout(5, 2));

        isbnField = new JTextField();

        JButton prestarButton = new JButton("Prestar");
        prestarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para prestar un libro
                String isbn = isbnField.getText();
                if (isbn.equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                    return;
                }

                Libro libro = libreria.obtenerLibro(isbn);
                if (libro != null) {
                    Prestamo nuevoPrestamo = new Prestamo(libro, usuario);
                    if (libreria.obtenerPrestamos().contains(nuevoPrestamo)) {
                        JOptionPane.showMessageDialog(null, "El libro ya está prestado");
                    } else {
                        libreria.prestarLibro(isbn, usuario.email());
                        if (libreria.obtenerPrestamos().contains(nuevoPrestamo)) {
                            JOptionPane.showMessageDialog(null, "Libro prestado exitosamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "Ocurrió un error al prestar el libro");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El libro no existe");
                    return;
                }
            }
        });


        JButton cancelarPrestarButton = new JButton("Cancelar");
        cancelarPrestarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelUsuario();
            }
        });

        prestarLibroPanel.add(new JLabel("ISBN:"));
        prestarLibroPanel.add(isbnField);
        prestarLibroPanel.add(prestarButton);
        prestarLibroPanel.add(cancelarPrestarButton);

        setContentPane(prestarLibroPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelDevolverLibroAdmin() {
        // Lógica para mostrar el panel de devolver libro
        JPanel devolverLibroPanel = new JPanel();
        devolverLibroPanel.setLayout(new GridLayout(5, 2));

        isbnField = new JTextField();
        emailField = new JTextField();

        JButton devolverButton = new JButton("Devolver");
        devolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para devolver un libro
                String isbn = isbnField.getText();
                String email = emailField.getText();
                if (isbn.equals("") || email.equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                    return;
                }

                Libro libro = libreria.obtenerLibro(isbn);
                Usuario usuario = libreria.obtenerUsuario(email);
                if (libro != null && usuario != null) {
                    Prestamo nuevoPrestamo = new Prestamo(libro, usuario);
                    if (!libreria.obtenerPrestamos().contains(nuevoPrestamo)) {
                        JOptionPane.showMessageDialog(null, "El libro no está prestado");
                    } else {
                        libreria.devolverLibro(isbn, email);
                        if (!libreria.obtenerPrestamos().contains(nuevoPrestamo)) {
                            JOptionPane.showMessageDialog(null, "Libro devuelto exitosamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "Ocurrió un error al devolver el libro");
                        }
                    }
                } else if (libro == null) {
                    JOptionPane.showMessageDialog(null, "El libro no existe");
                    return;
                } else if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "El usuario no existe");
                    return;
                }
            }
        });

        JButton cancelarDevolverButton = new JButton("Cancelar");
        cancelarDevolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelPrestamos();
            }
        });

        devolverLibroPanel.add(new JLabel("ISBN:"));
        devolverLibroPanel.add(isbnField);
        devolverLibroPanel.add(new JLabel("Email:"));
        devolverLibroPanel.add(emailField);
        devolverLibroPanel.add(devolverButton);
        devolverLibroPanel.add(cancelarDevolverButton);

        setContentPane(devolverLibroPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelDevolverLibroUsuario() {
        // Lógica para mostrar el panel de devolver libro
        JPanel devolverLibroPanel = new JPanel();
        devolverLibroPanel.setLayout(new GridLayout(5, 2));

        isbnField = new JTextField();

        JButton devolverButton = new JButton("Devolver");
        devolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para devolver un libro
                String isbn = isbnField.getText();
                if (isbn.equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                    return;
                }

                Prestamo prestamo = libreria.obtenerPrestamo(isbn, usuario.email());
                if (prestamo != null) {
                    libreria.devolverLibro(isbn, usuario.email());
                    if (!libreria.obtenerPrestamos().contains(prestamo)) {
                        JOptionPane.showMessageDialog(null, "Libro devuelto exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocurrió un error al devolver el libro");
                    }
                } else if (libreria.obtenerLibro(isbn) == null) {
                    JOptionPane.showMessageDialog(null, "El libro no existe");
                } else {
                    JOptionPane.showMessageDialog(null, "El libro no está prestado");
                }
            }
        });


        JButton cancelarDevolverButton = new JButton("Cancelar");
        cancelarDevolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelUsuario();
            }
        });

        devolverLibroPanel.add(new JLabel("ISBN:"));
        devolverLibroPanel.add(isbnField);
        devolverLibroPanel.add(devolverButton);
        devolverLibroPanel.add(cancelarDevolverButton);

        setContentPane(devolverLibroPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelListarPrestamosAdmin() {
        // Lógica para mostrar el panel de listar préstamos
        JPanel listarPrestamosPanel = new JPanel();
        listarPrestamosPanel.setLayout(new GridLayout(5, 2));

        for (Prestamo prestamo : libreria.obtenerPrestamos()) {
            JLabel prestamoLibroLabel = new JLabel(prestamo.libro().isbn() + " - " + prestamo.libro().titulo());
            prestamoLibroLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    StringSelection stringSelection = new StringSelection(prestamo.libro().isbn());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                    JOptionPane.showMessageDialog(null, "ISBN copiado al portapapeles");
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    prestamoLibroLabel.setForeground(Color.BLUE);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    prestamoLibroLabel.setForeground(Color.BLACK);
                }
            });
            JLabel prestamoUsuarioLabel = new JLabel(prestamo.usuario().nombre() + " " + prestamo.usuario().apellido() + " - " + prestamo.usuario().email());
            prestamoUsuarioLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    StringSelection stringSelection = new StringSelection(prestamo.usuario().email());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                    JOptionPane.showMessageDialog(null, "Email copiado al portapapeles");
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    prestamoUsuarioLabel.setForeground(Color.BLUE);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    prestamoUsuarioLabel.setForeground(Color.BLACK);
                }
            });
            listarPrestamosPanel.add(prestamoLibroLabel);
            listarPrestamosPanel.add(prestamoUsuarioLabel);
        }

        JButton salirListarPrestamosButton = new JButton("Salir");
        salirListarPrestamosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelPrestamos();
            }
        });

        listarPrestamosPanel.add(salirListarPrestamosButton);

        setContentPane(listarPrestamosPanel);
        revalidate();
        repaint();
    }

    private void mostrarPanelListarPrestamosUsuario() {
        // Lógica para mostrar el panel de listar préstamos
        JPanel listarPrestamosPanel = new JPanel();
        listarPrestamosPanel.setLayout(new GridLayout(5, 1));

        for (Prestamo prestamo : libreria.obtenerPrestamos()) {
            if (prestamo.usuario().email().equals(usuario.email())) {
                JLabel prestamoLabel = new JLabel(prestamo.libro().isbn() + " - " + prestamo.libro().titulo() + " - " + prestamo.usuario().nombre() + " " + prestamo.usuario().apellido() + " - " + prestamo.usuario().email());
                prestamoLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        StringSelection stringSelection = new StringSelection(prestamo.libro().isbn());
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(stringSelection, null);
                        JOptionPane.showMessageDialog(null, "ISBN copiado al portapapeles");
                    }

                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        prestamoLabel.setForeground(Color.BLUE);
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        prestamoLabel.setForeground(Color.BLACK);
                    }
                });
                listarPrestamosPanel.add(prestamoLabel);
            }
        }

        JButton salirListarPrestamosButton = new JButton("Salir");
        salirListarPrestamosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelUsuario();
            }
        });

        listarPrestamosPanel.add(salirListarPrestamosButton);

        setContentPane(listarPrestamosPanel);
        revalidate();
        repaint();
    }
}
