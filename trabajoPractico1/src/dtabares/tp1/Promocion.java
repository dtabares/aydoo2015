package dtabares.tp1;


import java.util.Set;

public abstract class Promocion {

    protected String nombre;
    protected double reduccionDeCostoTotal;
    protected PeriodoDeVigencia periodoDeVigencia;
    protected Set atracciocionesSujetasADescuento;



    public abstract double calcularReduccionDeCostoTotal(Set atraccionesQueElTuristaVisitara);

    public PeriodoDeVigencia obtenerPeriodoDeVigencia(){
        return  this.periodoDeVigencia;
    }

}
