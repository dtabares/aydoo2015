package dtabares.tp1;

import org.junit.Test;
import org.junit.Assert;

public class PosicionTest{

    @Test
    public void laLatitudDebeSerDiez(){
        Posicion posicion = new Posicion(10,20);
        double resultadoEsperado = 10;

        Assert.assertEquals(resultadoEsperado,posicion.obtenerLatitud(),0.1);
    }

    @Test
    public void laLongitudDebeSerVenite(){
        Posicion posicion = new Posicion(10,20);
        double resultadoEsperado = 20;

        Assert.assertEquals(resultadoEsperado,posicion.obtenerLongitud(),0.1);
    }
    

}