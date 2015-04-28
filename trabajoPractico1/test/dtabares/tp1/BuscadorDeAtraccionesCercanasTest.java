package dtabares.tp1;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class BuscadorDeAtraccionesCercanasTest {

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
        bilbo = new Atraccion("La Casa De Bilbo",20,50,10,TipoDeAtraccion.Aventura,new Posicion(10,10));

    }

    @Test
    public void siEstoyEnLaPosicionCeroCeroLaMasCercanaDebeSerRivendell(){
        atraccionesDisponibles = new HashSet();
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(rivendell);

        Assert.assertTrue(setEsperado.containsAll(BuscadorDeAtraccionesCercanas.buscarAtraccionMasCercana(new Posicion(0,0),atraccionesDisponibles)));
    }

    @Test
    public void siEstoyEnLaPosicionCeroCeroYRivendellNoEstaDisponibleComoAtraccionLasMasCercanasDebenSerRohanYBilbo(){
        atraccionesDisponibles = new HashSet();
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(rohan);
        setEsperado.add(bilbo);

        Assert.assertTrue(setEsperado.containsAll(BuscadorDeAtraccionesCercanas.buscarAtraccionMasCercana(new Posicion(0, 0), atraccionesDisponibles)));
    }

    @Test
    public void siEstoyEnLaPosicionDeRivendellLasMasCercanasDebenSerRohanYBilbo(){
        atraccionesDisponibles = new HashSet();
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(rohan);
        setEsperado.add(bilbo);

        Assert.assertTrue(setEsperado.containsAll(BuscadorDeAtraccionesCercanas.buscarAtraccionMasCercana(rivendell.obtenerPosicion(),atraccionesDisponibles)));
    }
}
