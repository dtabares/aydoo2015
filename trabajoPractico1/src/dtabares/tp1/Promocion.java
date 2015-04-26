package dtabares.tp1;


import java.util.Set;

public abstract class Promocion {

    protected String nombre;
    protected double reduccionDeCostoTotal;
    protected PeriodoDeVigencia periodoDeVigencia;



    public abstract double calcularReduccionDeCostoTotal(Set atraccionesQueElTuristaVisitara);

}
