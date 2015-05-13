package dtabares.tp1;

import java.lang.Math;

public class CalculadorDeDistancia {

    public double calcularDistanciaEntreDosPosiciones(Posicion posicionOrigen, Posicion posicionDestino){
        double distancia;
        double latitudOrigen = posicionOrigen.obtenerLatitud();
        double longitudOrigen = posicionOrigen.obtenerLongitud();
        double latitudDestino = posicionDestino.obtenerLatitud();
        double longitudDestino = posicionDestino.obtenerLongitud();

        distancia = Math.sqrt(Math.pow(latitudDestino - latitudOrigen,2) + Math.pow(longitudDestino - longitudOrigen,2));

        return distancia;
    }
}