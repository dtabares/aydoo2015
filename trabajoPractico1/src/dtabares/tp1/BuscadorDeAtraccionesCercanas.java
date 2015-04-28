package dtabares.tp1;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BuscadorDeAtraccionesCercanas {


    //Devuelve un Set De Atracciones ya que puede que 2 o mas esten a la misma distancia
    public static Set<Atraccion> buscarAtraccionMasCercana(Posicion posicionActual,Set<Atraccion> atraccionesDisponibles){

        Set<Atraccion> atraccionesMasCercanas = new HashSet();
        Atraccion atracciónConLaDistanciaMasBajaEncontrada;
        Iterator iterador = atraccionesDisponibles.iterator();
        double distanciaAlaAtraccionMasCercana;
        double distanciaDeLaAtraccionAComparar;


        if(iterador.hasNext()){
            //Por ser la primera, es la mas cercana.
            atracciónConLaDistanciaMasBajaEncontrada = (Atraccion) iterador.next();
            distanciaAlaAtraccionMasCercana = CalculadorDeDistancia.calcularDistanciaEntreDosPosiciones(posicionActual,atracciónConLaDistanciaMasBajaEncontrada.obtenerPosicion());
            atraccionesMasCercanas.add(atracciónConLaDistanciaMasBajaEncontrada);

            while(iterador.hasNext()){
                Atraccion atraccionAComparar  = (Atraccion) iterador.next();
                distanciaDeLaAtraccionAComparar = CalculadorDeDistancia.calcularDistanciaEntreDosPosiciones(posicionActual,atraccionAComparar.obtenerPosicion());


                //Si esto es True quiere decir que el usuario estaba parado en esta atraccion
                if (distanciaAlaAtraccionMasCercana == 0){
                    atraccionesMasCercanas.clear();
                    atracciónConLaDistanciaMasBajaEncontrada = atraccionAComparar;
                    atraccionesMasCercanas.add(atracciónConLaDistanciaMasBajaEncontrada);

                }
                else{

                    if(distanciaDeLaAtraccionAComparar < distanciaAlaAtraccionMasCercana && distanciaDeLaAtraccionAComparar != 0){

                        atraccionesMasCercanas.clear();
                        atracciónConLaDistanciaMasBajaEncontrada = atraccionAComparar;
                        atraccionesMasCercanas.add(atracciónConLaDistanciaMasBajaEncontrada);
                    }
                    else {
                        if (distanciaDeLaAtraccionAComparar == distanciaAlaAtraccionMasCercana){
                            atraccionesMasCercanas.add(atraccionAComparar);
                        }
                    }

                }



            }
        }


        return atraccionesMasCercanas;

    }
}
