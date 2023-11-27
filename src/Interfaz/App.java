package Interfaz;

import javax.swing.SwingUtilities;

public class App {

    public void ejecutarEnTerminal() {
        Libreria libreria = Libreria.getInstance();
        Interfaz interfaz = new Interfaz();
        interfaz.sistema(libreria);
    }

    public void ejecutarEnInterfazGrafica() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazG().setVisible(true);
            }
        });
    }
    public static void main(String[] args) throws Exception {
        App app = new App();
        // Ejecutar en terminal
        // app.ejecutarEnTerminal();

        // Ejecutar en interfaz Grafica
        app.ejecutarEnInterfazGrafica();
    }
}
