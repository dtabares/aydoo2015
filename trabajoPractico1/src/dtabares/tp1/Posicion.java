package dtabares.tp1;

public class Posicion {

    private double latitud;
    private double longitud;

    public Posicion(double latitud, double longitud){
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double obtenerLatitud(){
        return this.latitud;
    }

    public double obtenerLongitud(){
        return this.longitud;
    }

    public double calcularDistanciaHastaOtraPosicion(Posicion otraPosicion){
        double distancia;
        double latitudOrigen = this.obtenerLatitud();
        double longitudOrigen = this.obtenerLongitud();
        double latitudDestino = otraPosicion.obtenerLatitud();
        double longitudDestino = otraPosicion.obtenerLongitud();

        distancia = Math.sqrt(Math.pow(latitudDestino - latitudOrigen,2) + Math.pow(longitudDestino - longitudOrigen,2));

        return distancia;
    }
}