package Interfaz;

import Interfaz.Interfaz;

public class App {
    public static void main(String[] args) throws Exception {
        Libreria libreria = Libreria.getInstance();
        Interfaz interfaz = new Interfaz();
        interfaz.sistema(libreria);
    }
}
