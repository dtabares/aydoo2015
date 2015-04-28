package dtabares.tp1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * @Pre: Recibe un Set de Atracciones y un tipo de atraccion .
 * @Pos: Devuelve un Set de atracciones preferidas por el usuario.
 */

public class BuscadorDeAtraccionesPorPreferencia {

    public static Set<Atraccion> buscarAtraccionesPorPreferencia(Set<Atraccion> atraccionesDisponibles, TipoDeAtraccion tipoDeAtraccionPreferidaPorElUsuario){

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
