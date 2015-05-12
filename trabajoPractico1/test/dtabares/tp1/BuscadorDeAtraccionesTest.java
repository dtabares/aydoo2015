package dtabares.tp1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class BuscadorDeAtraccionesTest {

    private Atraccion mordor;
    private Atraccion rivendell;
    private Atraccion rohan;
    private Atraccion bilbo;
    private Set<Atraccion> atraccionesDisponibles;
    private BuscadorDeAtracciones buscadorDeAtracciones;



    @Before
    public void prepararAmbiente(){
        mordor = new Atraccion("Mordor",20,50,10,TipoDeAtraccion.Aventura,new Posicion(200,20));
        rivendell = new Atraccion("Rivendell",300,40,50,TipoDeAtraccion.Paisaje,new Posicion(-5,5));
        rohan = new Atraccion("Rohan",20,50,10,TipoDeAtraccion.Aventura,new Posicion(-10,-10));
        bilbo = new Atraccion("La Casa De Bilbo",50,50,10,TipoDeAtraccion.Degustacion,new Posicion(10,10));
        buscadorDeAtracciones = new BuscadorDeAtracciones();
        atraccionesDisponibles = new HashSet();

    }

    @Test
    public void siEstoyEnLaPosicionCeroCeroLaMasCercanaDebeSerRivendell(){
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        //atraccionesDisponibles = new HashSet();
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(rivendell);

        Assert.assertTrue(setEsperado.containsAll(buscadorDeAtracciones.buscarAtraccionMasCercana(new Posicion(0,0),atraccionesDisponibles)));
    }

    @Test
    public void siEstoyEnLaPosicionCeroCeroYRivendellNoEstaDisponibleComoAtraccionLasMasCercanasDebenSerRohanYBilbo(){
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(rohan);
        setEsperado.add(bilbo);

        Assert.assertTrue(setEsperado.containsAll(buscadorDeAtracciones.buscarAtraccionMasCercana(new Posicion(0, 0), atraccionesDisponibles)));
    }

    @Test
    public void siEstoyEnLaPosicionDeRivendellLasMasCercanasDebenSerRohanYBilbo(){
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(rohan);
        setEsperado.add(bilbo);

        Assert.assertTrue(setEsperado.containsAll(buscadorDeAtracciones.buscarAtraccionMasCercana(rivendell.obtenerPosicion(), atraccionesDisponibles)));
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

        Assert.assertTrue(atraccionesEsperadas.containsAll(buscadorDeAtracciones.buscarAtraccionesPorCosto(atraccionesDisponibles)));
    }

    @Test
    public void siNoAgregoMordorDeberiaDevolverSoloRohan(){
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);

        Set<Atraccion> atraccionesEsperadas = new HashSet();
        atraccionesEsperadas.add(rohan);

        Assert.assertTrue(atraccionesEsperadas.containsAll(buscadorDeAtracciones.buscarAtraccionesPorCosto(atraccionesDisponibles)));
    }

    @Test
    public void siLeGustanLasAtraccionesDeDegustacionDeberíaDevolverLaCasaDeBilbo(){
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(bilbo);

        Assert.assertTrue(setEsperado.containsAll(buscadorDeAtracciones.buscarAtraccionesPorPreferencia(atraccionesDisponibles, TipoDeAtraccion.Degustacion)));

    }

    @Test
    public void siLeGustanLasAtraccionesDeAventuraDeberiaDevolverMordorYRohan(){
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(mordor);
        setEsperado.add(rohan);

        Assert.assertTrue(setEsperado.containsAll(buscadorDeAtracciones.buscarAtraccionesPorPreferencia(atraccionesDisponibles, TipoDeAtraccion.Aventura)));

    }

    @Test
    public void siLeGustanLasDePaisajeYNoHayNingunaElSetDebeEstarVacio(){

        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        atraccionesDisponibles.remove(rivendell);
        Assert.assertEquals(0, buscadorDeAtracciones.buscarAtraccionesPorPreferencia(atraccionesDisponibles, TipoDeAtraccion.Paisaje).size());
    }
}
