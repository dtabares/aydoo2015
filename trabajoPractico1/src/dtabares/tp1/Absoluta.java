package dtabares.tp1;


import java.util.Iterator;
import java.util.Set;

public class Absoluta extends Promocion{

    private Set atracciocionesSujetasADescuento;
    private double costoPorPaquete;

    public Absoluta(String nombre,PeriodoDeVigencia periodoDeVigencia,Set atracciocionesSujetasADescuento,double costoPorPaquete){
        this.nombre = nombre;
        this.periodoDeVigencia = periodoDeVigencia;
        this.atracciocionesSujetasADescuento = atracciocionesSujetasADescuento;
        this.costoPorPaquete = costoPorPaquete;
        this.reduccionDeCostoTotal = 0;
    }
    @Override
    public double calcularReduccionDeCostoTotal(Set atraccionesQueElTuristaVisitara) {

        double costoOriginal = 0;
        Iterator iteradorDeAtraccionesQueElTuristaVisitara = atraccionesQueElTuristaVisitara.iterator();

        if(atraccionesQueElTuristaVisitara.containsAll(this.atracciocionesSujetasADescuento) && ! this.atracciocionesSujetasADescuento.isEmpty()) {
            while (iteradorDeAtraccionesQueElTuristaVisitara.hasNext()) {
                Atraccion atraccionQueElTuristaVisitara = (Atraccion) iteradorDeAtraccionesQueElTuristaVisitara.next();

                if (this.atracciocionesSujetasADescuento.contains(atraccionQueElTuristaVisitara)) {
                    costoOriginal = costoOriginal + atraccionQueElTuristaVisitara.obtenerCosto();
                }
            }
            this.reduccionDeCostoTotal = costoOriginal - costoPorPaquete;
        }

        return this.reduccionDeCostoTotal;
    }
}
