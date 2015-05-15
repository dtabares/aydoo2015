package dtabares.tp1;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class AplicadorDePromocionesTest {

    private Atraccion mordor;
    private Atraccion rivendell;
    private Atraccion rohan;
    private Atraccion bilbo;
    private Set<Atraccion> atraccionesDisponibles;
    private Set<Atraccion> atraccionesQueVisitaraElTurista;
    private Set<Promocion> promocionesDisponibles;
    private PeriodoDeVigencia periodoDeVigencia;

    @Before
    public void prepararAmbiente(){
        mordor = new Atraccion("Mordor",20,15,100,TipoDeAtraccion.Aventura,new Posicion(200,20));
        mordor.setearCantidadDeEntradasDeseadas(1);
        rivendell = new Atraccion("Rivendell",300,40,50,TipoDeAtraccion.Paisaje,new Posicion(-5,5));
        rivendell.setearCantidadDeEntradasDeseadas(1);
        rohan = new Atraccion("Rohan",50,10,40,TipoDeAtraccion.Aventura,new Posicion(-10,-10));
        rohan.setearCantidadDeEntradasDeseadas(1);
        bilbo = new Atraccion("La Casa De Bilbo",350,30,10,TipoDeAtraccion.Degustacion,new Posicion(10,10));
        bilbo.setearCantidadDeEntradasDeseadas(1);
        Calendar fechaInicio = new GregorianCalendar(2015,4,1);
        Calendar fechaFin = new GregorianCalendar(2015,4,5);
        periodoDeVigencia = new PeriodoDeVigencia(fechaInicio,fechaFin);
        atraccionesDisponibles = new HashSet();
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);

    }

    @Test
    public void conLasSiguientesCondicionesElDescuentoTotalDebeSerDe20(){
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(rohan);
        atraccionesSujetasAPromo.add(mordor);
        UnaAtraccionGratisComprandoCiertasAtracciones promo = new UnaAtraccionGratisComprandoCiertasAtracciones("Promo Mordor Va Gratis",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        atraccionesQueVisitaraElTurista = new HashSet<>();
        atraccionesQueVisitaraElTurista.add(rohan);
        atraccionesQueVisitaraElTurista.add(mordor);
        AplicadorDePromociones aplicadorDePromociones = new AplicadorDePromociones(promocionesDisponibles,new GregorianCalendar(2015,4,3),atraccionesQueVisitaraElTurista);
        aplicadorDePromociones.aplicarPromociones();


        Assert.assertEquals(20, aplicadorDePromociones.obtenerReduccionGanadaEnPromociones(), 0);
    }

   @Test
    public void conLasSiguientesCondicionesLasPromocionesAplicadasDebeSerPromoMordorVaGratis(){
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(rohan);
        atraccionesSujetasAPromo.add(mordor);
        UnaAtraccionGratisComprandoCiertasAtracciones promo = new UnaAtraccionGratisComprandoCiertasAtracciones("Promo Mordor Va Gratis",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        atraccionesQueVisitaraElTurista = new HashSet<>();
        atraccionesQueVisitaraElTurista.add(rohan);
        atraccionesQueVisitaraElTurista.add(mordor);
        AplicadorDePromociones aplicadorDePromociones = new AplicadorDePromociones(promocionesDisponibles,new GregorianCalendar(2015,4,3),atraccionesQueVisitaraElTurista);
        aplicadorDePromociones.aplicarPromociones();


        Assert.assertEquals("Promo Mordor Va Gratis", aplicadorDePromociones.obtenerPromocionesAplicadas().iterator().next().obtenerNombre());
    }


}
