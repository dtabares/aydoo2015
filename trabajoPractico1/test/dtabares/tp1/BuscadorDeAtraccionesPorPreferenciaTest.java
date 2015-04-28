package dtabares.tp1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


public class BuscadorDeAtraccionesPorPreferenciaTest {

    private Atraccion mordor;
    private Atraccion rivendell;
    private Atraccion rohan;
    private Atraccion bilbo;
    private Set<Atraccion> atraccionesDisponibles;

    @Before
    public void prepararAmbiente(){
        mordor = new Atraccion("Mordor",20,50,10,TipoDeAtraccion.Aventura,new Posicion(200,20));
        rivendell = new Atraccion("Rivendell",300,40,50,TipoDeAtraccion.Paisaje,new Posicion(-5,5));
        rohan = new Atraccion("Rohan",20,50,10,TipoDeAtraccion.Aventura,new Posicion(-10,-10));
        bilbo = new Atraccion("La Casa De Bilbo",20,50,10,TipoDeAtraccion.Degustacion,new Posicion(10,10));
        atraccionesDisponibles = new HashSet();
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);

    }

    @Test
    public void siLeGustanLasAtraccionesDeDegustacionDeberíaDevolverLaCasaDeBilbo(){

        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(bilbo);

        Assert.assertTrue(setEsperado.containsAll(BuscadorDeAtraccionesPorPreferencia.buscarAtraccionesPorPreferencia(atraccionesDisponibles, TipoDeAtraccion.Degustacion)));

    }

    @Test
    public void siLeGustanLasAtraccionesDeAventuraDeberiaDevolverMordorYRohan(){
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(mordor);
        setEsperado.add(rohan);

        Assert.assertTrue(setEsperado.containsAll(BuscadorDeAtraccionesPorPreferencia.buscarAtraccionesPorPreferencia(atraccionesDisponibles,TipoDeAtraccion.Aventura)));

    }

    @Test
    public void siLeGustanLasDePaisajeYNoHayNingunaElSetDebeEstarVacio(){

        atraccionesDisponibles.remove(rivendell);

        Assert.assertEquals(0,BuscadorDeAtraccionesPorPreferencia.buscarAtraccionesPorPreferencia(atraccionesDisponibles,TipoDeAtraccion.Paisaje).size());
    }
}
