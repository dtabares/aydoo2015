package dtabares.tp1;


import java.util.Iterator;
import java.util.Set;

public class UnaAtraccionGratisComprandoCiertasAtracciones extends Promocion{



    private Atraccion atraccionQueSeDescuentaCostoEnSuTotalidad;

    public UnaAtraccionGratisComprandoCiertasAtracciones(String nombre, PeriodoDeVigencia periodoDeVigencia, Set atracciocionesSujetasADescuento, Atraccion atraccionQueSeDescuentaCostoEnSuTotalidad){
        this.nombre = nombre;
        this.periodoDeVigencia = periodoDeVigencia;
        this.atracciocionesSujetasADescuento = atracciocionesSujetasADescuento;
        this.atraccionQueSeDescuentaCostoEnSuTotalidad = atraccionQueSeDescuentaCostoEnSuTotalidad;
        this.reduccionDeCostoTotal = 0;
    }

    @Override
    public double calcularReduccionDeCostoTotal(Set atraccionesQueElTuristaVisitara) {

        if(atraccionesQueElTuristaVisitara.containsAll(this.atracciocionesSujetasADescuento) && atraccionesQueElTuristaVisitara.contains(atraccionQueSeDescuentaCostoEnSuTotalidad) && ! this.atracciocionesSujetasADescuento.isEmpty()) {
            Iterator iteradorDeAtraccionesQueElTuristaVisitara = atraccionesQueElTuristaVisitara.iterator();

            while (iteradorDeAtraccionesQueElTuristaVisitara.hasNext()){
                Atraccion atraccionQueElTuristaVisitara = (Atraccion) iteradorDeAtraccionesQueElTuristaVisitara.next();
                if(atraccionQueElTuristaVisitara.equals(atraccionQueSeDescuentaCostoEnSuTotalidad)){
                    this.reduccionDeCostoTotal = atraccionQueElTuristaVisitara.obtenerCostoTotal();
                }
            }

        }
        return this.reduccionDeCostoTotal;
    }
}
