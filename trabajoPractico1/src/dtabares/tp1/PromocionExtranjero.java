package dtabares.tp1;

import java.util.Iterator;
import java.util.Set;


public class PromocionExtranjero extends Promocion{


    private Usuario usuario;
    //private BuscadorDeAtracciones buscadorDeAtracciones;
    private double distanciaMinimaParaQueLaPromocionAplique;

    public PromocionExtranjero(String nombre, PeriodoDeVigencia periodoDeVigencia, Usuario usuario){
        this.nombre = nombre;
        this.periodoDeVigencia = periodoDeVigencia;
        this.usuario = usuario;
        //this.buscadorDeAtracciones = new BuscadorDeAtracciones();
        this.reduccionDeCostoTotal = 0;
        this.distanciaMinimaParaQueLaPromocionAplique = 200;
    }


    @Override
    public double calcularReduccionDeCostoTotal(Set atraccionesQueElTuristaVisitara) {

            if(this.laDistanciaEntreElDomicilioDelUsuarioYLaAtraccionMasCercanaEsMayorOIgualALaDistanciaMinimaParaQueLaPromoAplique(atraccionesQueElTuristaVisitara)){
                Iterator<Atraccion> iteradorDeAtraccionesQueElTuristaVisitara = atraccionesQueElTuristaVisitara.iterator();

                while(iteradorDeAtraccionesQueElTuristaVisitara.hasNext()){
                    Atraccion atraccion = iteradorDeAtraccionesQueElTuristaVisitara.next();

                    this.reduccionDeCostoTotal = this.reduccionDeCostoTotal + (atraccion.getCosto()*this.usuario.getCantidadDeEntradasDeseadas()*.5);
                }
            }

        return this.reduccionDeCostoTotal;
    }

    private boolean laDistanciaEntreElDomicilioDelUsuarioYLaAtraccionMasCercanaEsMayorOIgualALaDistanciaMinimaParaQueLaPromoAplique(Set<Atraccion> atraccionesQueElTuristaVisitara){
        boolean aplicaPromo = false;
        Double menorDistancia = null;
        Double distancia;
        Iterator<Atraccion> atraccionIterator = atraccionesQueElTuristaVisitara.iterator();
        while ((atraccionIterator.hasNext())){
            Atraccion atraccion = atraccionIterator.next();
            distancia = this.usuario.obtenerDomicilio().calcularDistanciaHastaOtraPosicion(atraccion.obtenerPosicion());
            if(menorDistancia == null || distancia < menorDistancia){
                menorDistancia = distancia;
            }

        }
        if (menorDistancia >= 200){
            aplicaPromo = true;
            return aplicaPromo;
        }
        return aplicaPromo;
    }
}
