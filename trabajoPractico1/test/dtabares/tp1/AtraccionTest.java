package dtabares.tp1;

import org.junit.Test;
import org.junit.Assert;


public class AtraccionTest {

    @Test
    public void crearUnaAttraccionYaccederASuLatitud(){
        String nombre = "La Casa de Bilbo";
        double costo = 200;
        int duracionPromedioDeVisitaEnMins = 20;
        int cupoDeVisitantesDiarios = 500;
        TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.Aventura;
        Posicion posicion = new Posicion(-34.6037232,-58.3815931 );
        Atraccion atraccionDePrueba = new Atraccion (nombre, costo, duracionPromedioDeVisitaEnMins, cupoDeVisitantesDiarios, tipoDeAtraccion, posicion);

        Assert.assertEquals(-34.6037232,atraccionDePrueba.obtenerPosicion().obtenerLatitud(),0);
    }

    @Test
    public void nombreDeLaAtraccionDebeSerLaCasaDeBilbo(){
        String nombre = "La Casa de Bilbo";
        double costo = 200;
        int duracionPromedioDeVisitaEnMins = 20;
        int cupoDeVisitantesDiarios = 500;
        TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.Aventura;
        Posicion posicion = new Posicion(-34.6037232,-58.3815931 );
        Atraccion atraccionDePrueba = new Atraccion (nombre, costo, duracionPromedioDeVisitaEnMins, cupoDeVisitantesDiarios, tipoDeAtraccion, posicion);

        Assert.assertEquals("La Casa de Bilbo",atraccionDePrueba.getNombre());
    }
}