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
        double latitud = -34.6037232;
        double longitud = -58.3815931;
        Atraccion atraccionDePrueba = new Atraccion (nombre, costo, duracionPromedioDeVisitaEnMins, cupoDeVisitantesDiarios, tipoDeAtraccion, latitud, longitud);

        Assert.assertEquals(-34.6037232,atraccionDePrueba.getLatitud(),0);
    }
}