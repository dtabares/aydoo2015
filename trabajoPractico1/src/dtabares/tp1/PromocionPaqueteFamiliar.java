package dtabares.tp1;

import java.util.Iterator;
import java.util.Set;



public class PromocionPaqueteFamiliar extends Promocion{

    private double cantidadMinimaDeEntradas;
    private double descuentoBase;
    private double descuentoAdicional;
    private Usuario usuario;

    public PromocionPaqueteFamiliar(String nombre, PeriodoDeVigencia periodoDeVigencia, Usuario usuario){
        descuentoBase = 0.1;
        descuentoAdicional = 0.3;
        this.cantidadMinimaDeEntradas = 4;
        this.nombre = nombre;
        this.reduccionDeCostoTotal = 0;
        this.periodoDeVigencia = periodoDeVigencia;
        this.usuario = usuario;
    }
    @Override
    public double calcularReduccionDeCostoTotal(Set atraccionesQueElTuristaVisitara) {
        Iterator iteradorDeAtraccionesQueElTuristaVisitara = atraccionesQueElTuristaVisitara.iterator();
        while (iteradorDeAtraccionesQueElTuristaVisitara.hasNext()){
            Atraccion atraccionAEvaluar = (Atraccion) iteradorDeAtraccionesQueElTuristaVisitara.next();

            if (usuario.getCantidadDeEntradasDeseadas() == this.cantidadMinimaDeEntradas){
                this.reduccionDeCostoTotal = this.reduccionDeCostoTotal + this.aplicarDescuentoBase(atraccionAEvaluar);
            }

            if (usuario.getCantidadDeEntradasDeseadas() > this.cantidadMinimaDeEntradas){
                double entradasRestantes = usuario.getCantidadDeEntradasDeseadas() - this.cantidadMinimaDeEntradas;

                this.reduccionDeCostoTotal = this.reduccionDeCostoTotal + this.aplicarDescuentoBase(atraccionAEvaluar);
                this.reduccionDeCostoTotal = this.reduccionDeCostoTotal + this.aplicarDescuentoAdicional(atraccionAEvaluar,entradasRestantes);
            }
        }

        return this.reduccionDeCostoTotal;
    }

    private double aplicarDescuentoBase(Atraccion atraccion){
        return atraccion.getCosto()*cantidadMinimaDeEntradas*descuentoBase;
    }

    private double aplicarDescuentoAdicional(Atraccion atraccion, double entradasRestantes){
        return atraccion.getCosto()*entradasRestantes*descuentoAdicional;
    }
}
