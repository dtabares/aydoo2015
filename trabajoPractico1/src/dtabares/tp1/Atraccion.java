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

    public String getNombre() {
        return nombre;
    }

    public double getCosto() {
        return costo;
    }

    public int getDuracionPromedioDeVisitaEnMins() {
        return duracionPromedioDeVisitaEnMins;
    }

    public int getCupoDeVisitantesDiarios() {
        return cupoDeVisitantesDiarios;
    }

    public TipoDeAtraccion getTipoDeAtraccion() {
        return tipoDeAtraccion;
    }

    public Posicion obtenerPosicion() {
        return posicion;
    }

}