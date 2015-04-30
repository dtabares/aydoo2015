package dtabares;


import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class PrepararPescadoTest {

    @Test
    public void pescadoAlHorno(){
        List<String> listaEsperada = new LinkedList<>();
        listaEsperada.add("Limpio el Pescado");
        listaEsperada.add("Fileteo el Pescado");
        listaEsperada.add("Condimento, agrego verduras, envuelvo en papel aluminio y lo intorduzco en el horno");
        listaEsperada.add("Lo Presento en un plato cuadrado");

        PrepararPescado preparadorDePescadoAlHorno = new PrepararPescadoAlHorno();

        Assert.assertEquals(listaEsperada,preparadorDePescadoAlHorno.prepararElPescado());
    }

    @Test
    public void pescadoFrito(){
        List<String> listaEsperada = new LinkedList<>();
        listaEsperada.add("Limpio el Pescado");
        listaEsperada.add("Fileteo el Pescado");
        listaEsperada.add("Condimento, empano, y lo introduzco en la freidora");
        listaEsperada.add("Lo Presento en un plato redondo");

        PrepararPescado preparadorDePescadoFrito = new PrepararPescadoFrito();

        Assert.assertEquals(listaEsperada,preparadorDePescadoFrito.prepararElPescado());
    }

}
