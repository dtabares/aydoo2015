package dtabares.tp1;


import java.util.Iterator;
import java.util.Set;

public class PromocionPorcentual extends Promocion{


    private double porcentajeDeDescuento;

    public PromocionPorcentual(String nombre, double porcentajeDeDescuento, PeriodoDeVigencia periodoDeVigencia, Set atracciocionesSujetasADescuento){
        this.nombre = nombre;
        this.porcentajeDeDescuento = porcentajeDeDescuento;
        this.periodoDeVigencia = periodoDeVigencia;
        this.atracciocionesSujetasADescuento = atracciocionesSujetasADescuento;
    }


    @Override
    public double calcularReduccionDeCostoTotal(Set atraccionesQueElTuristaVisitara) {
        this.reduccionDeCostoTotal = 0;
        Iterator iteradorDeAtraccionesSujetasADescuento = this.atracciocionesSujetasADescuento.iterator();
        Iterator iteradorDeAtraccionesQueElTuristaVisitara;
        while (iteradorDeAtraccionesSujetasADescuento.hasNext()){
            Atraccion atraccionSujetaAPromocion = (Atraccion) iteradorDeAtraccionesSujetasADescuento.next();
            iteradorDeAtraccionesQueElTuristaVisitara = atraccionesQueElTuristaVisitara.iterator();

            while(iteradorDeAtraccionesQueElTuristaVisitara.hasNext()){
                Atraccion atraccionQueElTuristaVisitara = (Atraccion) iteradorDeAtraccionesQueElTuristaVisitara.next();

                if (atraccionQueElTuristaVisitara.equals(atraccionSujetaAPromocion)){
                    this.reduccionDeCostoTotal = this.reduccionDeCostoTotal + ((this.porcentajeDeDescuento * atraccionQueElTuristaVisitara.obtenerCostoTotal())/100);
                }

            }


/*            if (atraccionesQueElTuristaVisitara.contains(atraccionSujetaAPromocion)){
                this.reduccionDeCostoTotal = this.reduccionDeCostoTotal + ((this.porcentajeDeDescuento * atraccionSujetaAPromocion.obtenerCostoTotal())/100);
            }*/

        }

        return this.reduccionDeCostoTotal;
    }
}
