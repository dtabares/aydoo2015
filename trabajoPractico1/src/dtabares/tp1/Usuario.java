package dtabares.tp1;

public class Usuario {

    private double presupuestoDisponible;
    private double tiempoDisponibleParaVisitas;
    private double velocidadDeTraslado;
    private TipoDeAtraccion tipoDeAtraccionFavorita;
    private Posicion posicionActual;
    private Posicion domicilio;
    private int cantidadDeEntradasDeseadas;

    public Usuario(double presupuestoDisponible, double tiempoDisponibleParaVisitas, double velocidadDeTraslado, TipoDeAtraccion tipoDeAtraccionFavorita, Posicion posicionActual, Posicion domicilio, int cantidadDeEntradasDeseadas){

        this.presupuestoDisponible = presupuestoDisponible;
        this.tiempoDisponibleParaVisitas = tiempoDisponibleParaVisitas;
        this.velocidadDeTraslado = velocidadDeTraslado;
        this.tipoDeAtraccionFavorita = tipoDeAtraccionFavorita;
        this.posicionActual = posicionActual;
        this.domicilio = domicilio;
        this.cantidadDeEntradasDeseadas = cantidadDeEntradasDeseadas;
    }

    public double obtenerPresupuestoDisponible() {
        return this.presupuestoDisponible;
    }

    public double obtenerTiempoDisponibleParaVisitas() {
        return this.tiempoDisponibleParaVisitas;
    }

    public double obtenerVelocidadDeTraslado() {
        return this.velocidadDeTraslado;
    }

    public TipoDeAtraccion obtenerTipoDeAtraccionFavorita() {
        return this.tipoDeAtraccionFavorita;
    }

    public Posicion obtenerPosicionActual(){
        return  this.posicionActual;
    }

    public Posicion obtenerDomicilio() {
        return this.domicilio;
    }

    public int getCantidadDeEntradasDeseadas() {
        return cantidadDeEntradasDeseadas;
    }
}