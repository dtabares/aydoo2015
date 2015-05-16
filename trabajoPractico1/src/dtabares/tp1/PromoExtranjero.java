package dtabares.tp1;

import java.util.Iterator;
import java.util.Set;


public class PromoExtranjero extends Promocion{


    private PerfilDeUsuario perfilDeUsuario;
    private BuscadorDeAtracciones buscadorDeAtracciones;
    private double distanciaMinimaParaQueLaPromocionAplique;

    public PromoExtranjero(String nombre,PeriodoDeVigencia periodoDeVigencia, PerfilDeUsuario perfilDeUsuario){
        this.nombre = nombre;
        this.periodoDeVigencia = periodoDeVigencia;
        this.perfilDeUsuario = perfilDeUsuario;
        this.buscadorDeAtracciones = new BuscadorDeAtracciones();
        this.reduccionDeCostoTotal = 0;
        this.distanciaMinimaParaQueLaPromocionAplique = 200;
    }


    @Override
    public double calcularReduccionDeCostoTotal(Set atraccionesQueElTuristaVisitara) {
        Set<Atraccion> atraccionesMasCercanaAlDomicilioDelUsuario = this.buscadorDeAtracciones.buscarAtraccionMasCercana(perfilDeUsuario.obtenerDomicilio(),atraccionesQueElTuristaVisitara);
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
        CalculadorDeDistancia calculadorDeDistancia = new CalculadorDeDistancia();
        double distanciaEntreElDomicilioYlaAtraccionMasCercana = calculadorDeDistancia.calcularDistanciaEntreDosPosiciones(this.perfilDeUsuario.obtenerDomicilio(),atraccionMasCercana.obtenerPosicion());
        if (distanciaEntreElDomicilioYlaAtraccionMasCercana >= 200){
            laDistanciaEntreElDomicilioDelUsuarioYLaAtraccionMasCercanaEsMayorOIgualALaDistanciaMinimaParaQueLaPromoAplique = true;
        }

        return laDistanciaEntreElDomicilioDelUsuarioYLaAtraccionMasCercanaEsMayorOIgualALaDistanciaMinimaParaQueLaPromoAplique;
    }
}
