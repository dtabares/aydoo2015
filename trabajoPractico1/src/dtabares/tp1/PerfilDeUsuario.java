package dtabares.tp1;

public class PerfilDeUsuario {

    private double presupuestoDisponible;
    private double tiempoDisponibleParaVisitas;
    private double velocidadDeTraslado;
    private TipoDeAtraccion tipoDeAtraccionFavorita;
    private Posicion posicionActual;
    private Posicion domicilio;

    public PerfilDeUsuario(double presupuestoDisponible, double tiempoDisponibleParaVisitas, double velocidadDeTraslado, TipoDeAtraccion tipoDeAtraccionFavorita, Posicion posicionActual, Posicion domicilio){

        this.presupuestoDisponible = presupuestoDisponible;
        this.tiempoDisponibleParaVisitas = tiempoDisponibleParaVisitas;
        this.velocidadDeTraslado = velocidadDeTraslado;
        this.tipoDeAtraccionFavorita = tipoDeAtraccionFavorita;
        this.posicionActual = posicionActual;
        this.domicilio = domicilio;
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
}