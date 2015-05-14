package dtabares.tp1;

import java.util.Iterator;
import java.util.Set;



public class PaqueteFamiliar extends Promocion{

    private double cantidadMinimaDeEntradas;
    private double descuentoBase;
    private double descuentoAdicional;

    public PaqueteFamiliar(String nombre, PeriodoDeVigencia periodoDeVigencia){
        descuentoBase = 0.1;
        descuentoAdicional = 0.3;
        this.cantidadMinimaDeEntradas = 4;
        this.nombre = nombre;
        this.reduccionDeCostoTotal = 0;
        this.periodoDeVigencia = periodoDeVigencia;
    }
    @Override
    public double calcularReduccionDeCostoTotal(Set atraccionesQueElTuristaVisitara) {
        Iterator iteradorDeAtraccionesQueElTuristaVisitara = atraccionesQueElTuristaVisitara.iterator();
        while (iteradorDeAtraccionesQueElTuristaVisitara.hasNext()){
            Atraccion atraccionAEvaluar = (Atraccion) iteradorDeAtraccionesQueElTuristaVisitara.next();

            if (atraccionAEvaluar.obtenerCantidadDeEntradasDeseadas() == this.cantidadMinimaDeEntradas){
                this.reduccionDeCostoTotal = this.reduccionDeCostoTotal + this.aplicarDescuentoBase(atraccionAEvaluar);
            }

            if (atraccionAEvaluar.obtenerCantidadDeEntradasDeseadas() > this.cantidadMinimaDeEntradas){
                double entradasRestantes = atraccionAEvaluar.obtenerCantidadDeEntradasDeseadas() - this.cantidadMinimaDeEntradas;

                this.reduccionDeCostoTotal = this.reduccionDeCostoTotal + this.aplicarDescuentoBase(atraccionAEvaluar);
                this.reduccionDeCostoTotal = this.reduccionDeCostoTotal + this.aplicarDescuentoAdicional(atraccionAEvaluar,entradasRestantes);
            }
        }

        return this.reduccionDeCostoTotal;
    }

    private double aplicarDescuentoBase(Atraccion atraccion){
        return atraccion.obtenerCostoUnitario()*cantidadMinimaDeEntradas*descuentoBase;
    }

    private double aplicarDescuentoAdicional(Atraccion atraccion, double entradasRestantes){
        return atraccion.obtenerCostoUnitario()*entradasRestantes*descuentoAdicional;
    }
}
