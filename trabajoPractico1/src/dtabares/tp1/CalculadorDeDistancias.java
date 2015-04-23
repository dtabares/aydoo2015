package dtabares.tp1;

import java.lang.Math;

public class CalculadorDeDistancias {
/*    private static CalculadorDeDistancias instancia = null;
    protected CalculadorDeDistancias() {
    }
    public static ClassicSingleton obtenerInstancia() {
        if(instancia == null) {
            instancia = new CalculadorDeDistancias();
        }
        return instancia;
    }*/

    public static double calcularDistanciaEntreDosPosiciones(Posicion posicionOrigen, Posicion posicionDestino){
        double distancia;
        double latitudOrigen = posicionOrigen.obtenerLatitud();
        double longitudOrigen = posicionOrigen.obtenerLongitud();
        double latitudDestino = posicionDestino.obtenerLatitud();
        double longitudDestino = posicionDestino.obtenerLongitud();

        distancia = Math.sqrt(Math.pow(latitudDestino - latitudOrigen,2) + Math.pow(longitudDestino - longitudOrigen,2));

        return distancia;
    }
}