package dtabares.tp1;

import java.util.Iterator;
import java.util.Set;


public class PromocionExtranjero extends Promocion{


    private Usuario usuario;
    private BuscadorDeAtracciones buscadorDeAtracciones;
    private double distanciaMinimaParaQueLaPromocionAplique;

    public PromocionExtranjero(String nombre, PeriodoDeVigencia periodoDeVigencia, Usuario usuario){
        this.nombre = nombre;
        this.periodoDeVigencia = periodoDeVigencia;
        this.usuario = usuario;
        this.buscadorDeAtracciones = new BuscadorDeAtracciones();
        this.reduccionDeCostoTotal = 0;
        this.distanciaMinimaParaQueLaPromocionAplique = 200;
    }


    @Override
    public double calcularReduccionDeCostoTotal(Set atraccionesQueElTuristaVisitara) {
        Set<Atraccion> atraccionesMasCercanaAlDomicilioDelUsuario = this.buscadorDeAtracciones.buscarAtraccionMasCercana(usuario.obtenerDomicilio(),atraccionesQueElTuristaVisitara);
        if (atraccionesMasCercanaAlDomicilioDelUsuario.size()>0){
            Atraccion AtraccionMasCercana = atraccionesMasCercanaAlDomicilioDelUsuario.iterator().next();
            if(this.laDistanciaEntreElDomicilioDelUsuarioYLaAtraccionMasCercanaEsMayorOIgualALaDistanciaMinimaParaQueLaPromoAplique(AtraccionMasCercana)){
                Iterator<Atraccion> iteradorDeAtraccionesQueElTuristaVisitara = atraccionesQueElTuristaVisitara.iterator();

                while(iteradorDeAtraccionesQueElTuristaVisitara.hasNext()){
                    Atraccion atraccion = iteradorDeAtraccionesQueElTuristaVisitara.next();

                    this.reduccionDeCostoTotal = this.reduccionDeCostoTotal + (atraccion.obtenerCostoTotal()*.5);
                }
            }
        }
        return this.reduccionDeCostoTotal;
    }

    private boolean laDistanciaEntreElDomicilioDelUsuarioYLaAtraccionMasCercanaEsMayorOIgualALaDistanciaMinimaParaQueLaPromoAplique(Atraccion atraccionMasCercana){
        boolean laDistanciaEntreElDomicilioDelUsuarioYLaAtraccionMasCercanaEsMayorOIgualALaDistanciaMinimaParaQueLaPromoAplique = false;
        double distanciaEntreElDomicilioYlaAtraccionMasCercana = this.usuario.obtenerDomicilio().calcularDistanciaHastaOtraPosicion(atraccionMasCercana.obtenerPosicion());
        if (distanciaEntreElDomicilioYlaAtraccionMasCercana >= 200){
            laDistanciaEntreElDomicilioDelUsuarioYLaAtraccionMasCercanaEsMayorOIgualALaDistanciaMinimaParaQueLaPromoAplique = true;
        }

        return laDistanciaEntreElDomicilioDelUsuarioYLaAtraccionMasCercanaEsMayorOIgualALaDistanciaMinimaParaQueLaPromoAplique;
    }
}
