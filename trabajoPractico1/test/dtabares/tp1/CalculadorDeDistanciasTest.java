package dtabares.tp1;

import org.junit.Test;
import org.junit.Assert;

public class CalculadorDeDistanciasTest{


    @Test
    public void laDistanciaEntreUnaPosicionYEsaMismaPosicionDebeSerCero(){

        double resultadoEsperado = 0;
        Posicion origen = new Posicion(-34.6037232,-58.3815931 );
        Posicion destino = new Posicion(-34.6037232,-58.3815931 );

        double distancia = CalculadorDeDistancias.calcularDistanciaEntreDosPosiciones(origen,destino);

        Assert.assertEquals(resultadoEsperado,distancia,0);

    }

}