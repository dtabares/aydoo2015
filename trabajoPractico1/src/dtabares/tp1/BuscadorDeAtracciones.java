package dtabares.tp1;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class BuscadorDeAtracciones {

    private CalculadorDeDistancia calculadorDeDistancia;

    public BuscadorDeAtracciones(){
        this.calculadorDeDistancia = new CalculadorDeDistancia();
    }

    //Devuelve un Set De Atracciones ya que puede que 2 o mas esten a la misma distancia
    public Set<Atraccion> buscarAtraccionMasCercana(Posicion posicionActual,Set<Atraccion> atraccionesDisponibles){

        Set<Atraccion> atraccionesMasCercanas = new HashSet();
        Atraccion atracciónConLaDistanciaMasBajaEncontrada;
        Iterator iterador = atraccionesDisponibles.iterator();
        double distanciaAlaAtraccionMasCercana;
        double distanciaDeLaAtraccionAComparar;

        if(iterador.hasNext()){
            //Por ser la primera, es la mas cercana.
            atracciónConLaDistanciaMasBajaEncontrada = (Atraccion) iterador.next();
            distanciaAlaAtraccionMasCercana = calculadorDeDistancia.calcularDistanciaEntreDosPosiciones(posicionActual,atracciónConLaDistanciaMasBajaEncontrada.obtenerPosicion());
            atraccionesMasCercanas.add(atracciónConLaDistanciaMasBajaEncontrada);

            while(iterador.hasNext()){
                Atraccion atraccionAComparar  = (Atraccion) iterador.next();
                distanciaDeLaAtraccionAComparar = calculadorDeDistancia.calcularDistanciaEntreDosPosiciones(posicionActual,atraccionAComparar.obtenerPosicion());


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

    public Set<Atraccion> buscarAtraccionesPorCosto(Set<Atraccion> atraccionesDisponibles){
        Set<Atraccion> atraccionesConMenorCosto = new HashSet();
        double menorCosto = -1;
        Iterator<Atraccion> iteradorDeAtraccionesDisponibles = atraccionesDisponibles.iterator();
        Atraccion atraccionAEvaluar;
        //Atraccion atraccionConMenorCosto;

        while (iteradorDeAtraccionesDisponibles.hasNext()){
            atraccionAEvaluar = iteradorDeAtraccionesDisponibles.next();

            if(menorCosto == -1 || atraccionAEvaluar.obtenerCostoTotal() < menorCosto){
                if(!atraccionesConMenorCosto.isEmpty()){
                    atraccionesConMenorCosto.clear();
                }
                menorCosto = atraccionAEvaluar.obtenerCostoTotal();
                atraccionesConMenorCosto.add(atraccionAEvaluar);
            }
            else{
                if (atraccionAEvaluar.obtenerCostoTotal() == menorCosto){
                    atraccionesConMenorCosto.add(atraccionAEvaluar);
                }
            }
        }

        return atraccionesConMenorCosto;
    }

    public Set<Atraccion> buscarAtraccionesPorPreferencia(Set<Atraccion> atraccionesDisponibles, TipoDeAtraccion tipoDeAtraccionPreferidaPorElUsuario){

        Set<Atraccion> atraccionesPreferidas = new HashSet();
        Iterator<Atraccion> iteradorDeAtracciones = atraccionesDisponibles.iterator();
        Atraccion atraccionAEvaluar;

        while(iteradorDeAtracciones.hasNext()){
            atraccionAEvaluar = iteradorDeAtracciones.next();

            if (atraccionAEvaluar.obtenerTipoDeAtraccion().equals(tipoDeAtraccionPreferidaPorElUsuario)){
                atraccionesPreferidas.add(atraccionAEvaluar);
            }
        }

        return atraccionesPreferidas;
    }
}