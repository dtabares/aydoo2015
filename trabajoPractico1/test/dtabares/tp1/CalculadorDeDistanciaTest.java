package dtabares.tp1;

import org.junit.Test;
import org.junit.Assert;

public class CalculadorDeDistanciaTest {


    @Test
    public void laDistanciaEntreUnaPosicionYEsaMismaPosicionDebeSerCero(){

        CalculadorDeDistancia calculadorDeDistancia = new CalculadorDeDistancia();
        double resultadoEsperado = 0;
        Posicion origen = new Posicion(-34.6037232,-58.3815931 );
        Posicion destino = new Posicion(-34.6037232,-58.3815931 );

        double distancia = calculadorDeDistancia.calcularDistanciaEntreDosPosiciones(origen, destino);

        Assert.assertEquals(resultadoEsperado,distancia,0);

    }

    @Test
    public void laDistanciaEntreOrigenYDestinoDebeSerCasi22Coma36(){

        CalculadorDeDistancia calculadorDeDistancia = new CalculadorDeDistancia();
        double resultadoEsperado = 22.360679775;
        Posicion origen = new Posicion(20,40);
        Posicion destino = new Posicion(10,20);

        double distancia = calculadorDeDistancia.calcularDistanciaEntreDosPosiciones(origen, destino);

        Assert.assertEquals(resultadoEsperado,distancia,0.1);

    }

}