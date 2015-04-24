package dtabares.tp1;

public class Usuario{

    private double presupuestoDisponible;
    private int tiempoDisponibleParaVisitas;
    private float velocidadDeTraslado;
    private TipoDeAtraccion tipoDeAtraccionFavorita;
    private Posicion posicionActual;

    public Usuario(double presupuestoDisponible, int tiempoDisponibleParaVisitas, float velocidadDeTraslado, TipoDeAtraccion tipoDeAtraccionFavorita, Posicion posicionActual){

        this.presupuestoDisponible = presupuestoDisponible;
        this.tiempoDisponibleParaVisitas = tiempoDisponibleParaVisitas;
        this.velocidadDeTraslado = velocidadDeTraslado;
        this.tipoDeAtraccionFavorita = tipoDeAtraccionFavorita;
        this.posicionActual = posicionActual;
    }

    public double obtenerPresupuestoDisponible() {
        return this.presupuestoDisponible;
    }

    public int obtenerTiempoDisponibleParaVisitas() {
        return this.tiempoDisponibleParaVisitas;
    }

    public float obtenerVelocidadDeTraslado() {
        return this.velocidadDeTraslado;
    }

    public TipoDeAtraccion obtenerTipoDeAtraccionFavorita() {
        return this.tipoDeAtraccionFavorita;
    }

    public Posicion obtenerPosicionActual(){
        return  this.posicionActual;
    }
}