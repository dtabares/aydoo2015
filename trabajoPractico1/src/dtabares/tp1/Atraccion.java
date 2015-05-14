package dtabares.tp1;

public class Atraccion implements Comparable<Atraccion> {


    private String nombre;
    private double costoUnitario;
    private double costoTotal;
    private int cantidadDeEntradas;
    private int duracionPromedioDeVisitaEnMins;
    private int cupoDeVisitantesDiarios;
    private int numeroDeVisitantesActuales;
    private TipoDeAtraccion tipoDeAtraccion;
    private Posicion posicion;

    public Atraccion (String nombre, double costoUnitario, int duracionPromedioDeVisitaEnMins, int cupoDeVisitantesDiarios, TipoDeAtraccion tipoDeAtraccion, Posicion posicion){

        this.nombre = nombre;
        this.costoUnitario = costoUnitario;
        this.duracionPromedioDeVisitaEnMins = duracionPromedioDeVisitaEnMins;
        this.cupoDeVisitantesDiarios = cupoDeVisitantesDiarios;
        this.tipoDeAtraccion = tipoDeAtraccion;
        this.posicion = posicion;
        this.numeroDeVisitantesActuales = 0;
        this.costoTotal = 0;
        this.cantidadDeEntradas=0;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerNumeroDeVisitantesActuales(){
        return this.numeroDeVisitantesActuales;
    }

    public void setNumeroDeVisitantesActuales(int cantidadDeVisitantes){
        this.numeroDeVisitantesActuales = cantidadDeVisitantes;
    }

    public double obtenerCostoUnitario() {
        return costoUnitario;
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

    public void setearCantidadDeEntradasDeseadas(int cantidadDeEntradas){
        this.cantidadDeEntradas = cantidadDeEntradas;
    }

    public int obtenerCantidadDeEntradasDeseadas(){
        return this.cantidadDeEntradas;
    }

    public double obtenerCostoTotal(){
        return (this.cantidadDeEntradas * this.costoUnitario);
    }



    @Override
    public int compareTo(Atraccion otraAtraccion) {
        return this.nombre.compareTo(otraAtraccion.obtenerNombre());
    }

    @Override
    public boolean equals (Object o) {
        boolean result = true;

        if (o == null){
            result = false;
        }

        if (!(o instanceof Atraccion)){
            result = false;
        }

        if (o == this) {
            result = true;
        }

        Atraccion otraAtraccion = (Atraccion) o;

        if (! this.nombre.equals((otraAtraccion.obtenerNombre()))) {
            result = false;
        }

        return result;
    }

    @Override
    public int hashCode(){
        return this.nombre.hashCode();
    }
}