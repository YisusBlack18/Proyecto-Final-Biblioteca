public class LiberiaSingleton {
    private static LiberiaSingleton instance;

    private LiberiaSingleton() {
        // Constructor privado para evitar la instanciación directa
    }

    public static LiberiaSingleton getInstance() {
        if (instance == null) {
            instance = new LiberiaSingleton();
        }
        return instance;
    }

    // Métodos y propiedades de la biblioteca
}
