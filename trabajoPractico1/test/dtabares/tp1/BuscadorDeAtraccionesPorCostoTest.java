package dtabares.tp1;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class BuscadorDeAtraccionesPorCostoTest {

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
        bilbo = new Atraccion("La Casa De Bilbo",50,50,10,TipoDeAtraccion.Aventura,new Posicion(10,10));
        atraccionesDisponibles = new HashSet();
    }

    @Test
    public void siAgregoTodasLasAtracionesPreparadasAlSetDeberíaDevolverMordorYRohan(){
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);

        Set<Atraccion> atraccionesEsperadas = new HashSet();
        atraccionesEsperadas.add(mordor);
        atraccionesEsperadas.add(rohan);

        Assert.assertTrue(atraccionesEsperadas.containsAll(BuscadorDeAtraccionesPorCosto.buscarAtraccionesPorCosto(atraccionesDisponibles)));
    }

    @Test
    public void siNoAgregoMordorDeberiaDevolverSoloRohan(){
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);

        Set<Atraccion> atraccionesEsperadas = new HashSet();
        atraccionesEsperadas.add(rohan);

        Assert.assertTrue(atraccionesEsperadas.containsAll(BuscadorDeAtraccionesPorCosto.buscarAtraccionesPorCosto(atraccionesDisponibles)));
    }
}
