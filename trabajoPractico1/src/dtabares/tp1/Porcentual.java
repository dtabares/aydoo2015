package dtabares.tp1;


import java.util.Iterator;
import java.util.Set;

public class Porcentual extends Promocion{


    private double porcentajeDeDescuento;

    public Porcentual(String nombre, double porcentajeDeDescuento,PeriodoDeVigencia periodoDeVigencia,Set atracciocionesSujetasADescuento){
        this.nombre = nombre;
        this.porcentajeDeDescuento = porcentajeDeDescuento;
        this.periodoDeVigencia = periodoDeVigencia;
        this.atracciocionesSujetasADescuento = atracciocionesSujetasADescuento;
    }


    @Override
    public double calcularReduccionDeCostoTotal(Set atraccionesQueElTuristaVisitara) {
        this.reduccionDeCostoTotal = 0;
        Iterator iteradorDeAtraccionesSujetasADescuento = this.atracciocionesSujetasADescuento.iterator();
       // Iterator iteradorDeAtraccionesQueElTuristaVisitara = atraccionesQueElTuristaVisitara.
        while (iteradorDeAtraccionesSujetasADescuento.hasNext()){
            Atraccion atraccionSujetaAPromocion = (Atraccion) iteradorDeAtraccionesSujetasADescuento.next();


            if (atraccionesQueElTuristaVisitara.contains(atraccionSujetaAPromocion)){
                this.reduccionDeCostoTotal = this.reduccionDeCostoTotal + ((this.porcentajeDeDescuento * atraccionSujetaAPromocion.obtenerCosto())/100);
            }

        }

        return this.reduccionDeCostoTotal;
    }
}
