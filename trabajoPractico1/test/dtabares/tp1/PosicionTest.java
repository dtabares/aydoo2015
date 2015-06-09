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

    @Test
    public void laDistanciaEntreUnaPosicionYEsaMismaPosicionDebeSerCero(){

        double resultadoEsperado = 0;
        Posicion origen = new Posicion(-34.6037232,-58.3815931 );
        Posicion destino = new Posicion(-34.6037232,-58.3815931 );

        double distancia = origen.calcularDistanciaHastaOtraPosicion(destino);

        Assert.assertEquals(resultadoEsperado,distancia,0);

    }

    @Test
    public void laDistanciaEntreOrigenYDestinoDebeSerCasi22Coma36(){

        double resultadoEsperado = 22.360679775;
        Posicion origen = new Posicion(20,40);
        Posicion destino = new Posicion(10,20);

        double distancia = origen.calcularDistanciaHastaOtraPosicion(destino);

        Assert.assertEquals(resultadoEsperado,distancia,0.1);

    }
    

}