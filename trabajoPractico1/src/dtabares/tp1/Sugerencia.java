package dtabares.tp1;


public class Sugerencia{


    private Itinerario itinerario;
    private double reduccionGanadaEnPromociones;

    public Sugerencia(Itinerario itinerario, double reduccionGanadaEnPromociones) {
        this.itinerario = itinerario;
        this.reduccionGanadaEnPromociones = reduccionGanadaEnPromociones;
    }


    public Itinerario obtenerItinerario() {
        return itinerario;
    }

    public double obtenerReduccionGanadaEnPromociones() {
        return reduccionGanadaEnPromociones;
    }
}
