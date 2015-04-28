package dtabares.tp1;


import java.util.Set;

public class AxB extends Promocion{



    private Atraccion atraccionQueSeDescuentaCostoEnSuTotalidad;

    public AxB(String nombre, PeriodoDeVigencia periodoDeVigencia,Set atracciocionesSujetasADescuento, Atraccion atraccionQueSeDescuentaCostoEnSuTotalidad){
        this.nombre = nombre;
        this.periodoDeVigencia = periodoDeVigencia;
        this.atracciocionesSujetasADescuento = atracciocionesSujetasADescuento;
        this.atraccionQueSeDescuentaCostoEnSuTotalidad = atraccionQueSeDescuentaCostoEnSuTotalidad;
        this.reduccionDeCostoTotal = 0;
    }

    @Override
    public double calcularReduccionDeCostoTotal(Set atraccionesQueElTuristaVisitara) {

        if(atraccionesQueElTuristaVisitara.containsAll(this.atracciocionesSujetasADescuento) && atraccionesQueElTuristaVisitara.contains(atraccionQueSeDescuentaCostoEnSuTotalidad) && ! this.atracciocionesSujetasADescuento.isEmpty()) {

            this.reduccionDeCostoTotal = atraccionQueSeDescuentaCostoEnSuTotalidad.obtenerCosto();
        }

        return this.reduccionDeCostoTotal;
    }
}
