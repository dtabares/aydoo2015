package dtabares.tp1;

public class Atraccion {


    private String nombre;
    private double costo;
    private int duracionPromedioDeVisitaEnMins;
    private  int cupoDeVisitantesDiarios;
    private TipoDeAtraccion tipoDeAtraccion;
    private Posicion posicion;

    public Atraccion (String nombre, double costo, int duracionPromedioDeVisitaEnMins, int cupoDeVisitantesDiarios, TipoDeAtraccion tipoDeAtraccion, Posicion posicion){

        this.nombre = nombre;
        this.costo = costo;
        this.duracionPromedioDeVisitaEnMins = duracionPromedioDeVisitaEnMins;
        this.cupoDeVisitantesDiarios = cupoDeVisitantesDiarios;
        this.tipoDeAtraccion = tipoDeAtraccion;
        this.posicion = posicion;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public double obtenerCosto() {
        return costo;
    }

    public int obtenerDuracionPromedioDeVisitaEnMins() {
        return duracionPromedioDeVisitaEnMins;
    }

    public int obtenerCupoDeVisitantesDiarios() {
        return cupoDeVisitantesDiarios;
    }

    public TipoDeAtraccion obtenerTipoDeAtraccion() {
        return tipoDeAtraccion;
    }

    public Posicion obtenerPosicion() {
        return posicion;
    }

}