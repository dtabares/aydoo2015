package dtabares.tp1;

import java.lang.Math;

public class CalculadorDeDistancia {
/*    private static CalculadorDeDistancia instancia = null;
    protected CalculadorDeDistancia() {
    }
    public static ClassicSingleton obtenerInstancia() {
        if(instancia == null) {
            instancia = new CalculadorDeDistancia();
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