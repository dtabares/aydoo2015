package dtabares.tp1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 */
public class BuscadorDeAtraccionesPorCosto {


    public static Set<Atraccion> buscarAtraccionesPorCosto(Set<Atraccion> atraccionesDisponibles){
        Set<Atraccion> atraccionesConMenorCosto = new HashSet();
        double menorCosto = -1;
        Iterator<Atraccion> iteradorDeAtraccionesDisponibles = atraccionesDisponibles.iterator();
        Atraccion atraccionAEvaluar;
        //Atraccion atraccionConMenorCosto;

        while (iteradorDeAtraccionesDisponibles.hasNext()){
            atraccionAEvaluar = iteradorDeAtraccionesDisponibles.next();

            if(menorCosto == -1 || atraccionAEvaluar.obtenerCosto() < menorCosto){
                if(!atraccionesConMenorCosto.isEmpty()){
                    atraccionesConMenorCosto.clear();
                }
                menorCosto = atraccionAEvaluar.obtenerCosto();
                atraccionesConMenorCosto.add(atraccionAEvaluar);
            }
            else{
                if (atraccionAEvaluar.obtenerCosto() == menorCosto){
                    atraccionesConMenorCosto.add(atraccionAEvaluar);
                }
            }
        }

        return atraccionesConMenorCosto;
    }
}
