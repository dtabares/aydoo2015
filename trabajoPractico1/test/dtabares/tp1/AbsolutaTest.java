package dtabares.tp1;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class AbsolutaTest {

    private Promocion promoSinAtracciones;
    private Set<Atraccion> setDeAtraccionesQueElTuristaVisitara;
    private Promocion promoConUnaSolaAtraccion;
    private PeriodoDeVigencia periodoDeVigencia;

    @Before
    public void prepararAmbiente() {
        Calendar fechaInicio = new GregorianCalendar(2015,4,1);
        Calendar fechaFin = new GregorianCalendar(2015,4,5);
        periodoDeVigencia = new PeriodoDeVigencia(fechaInicio,fechaFin);
        Atraccion laCasaDeBilbo = new Atraccion("La Casa De Bilbo", 200, 30, 50, TipoDeAtraccion.Degustacion, new Posicion(20, 30));
        Atraccion mordor = new Atraccion("Mordor", 20, 50, 90, TipoDeAtraccion.Aventura, new Posicion(50, 40));
        laCasaDeBilbo.setearCantidadDeEntradasDeseadas(1);
        mordor.setearCantidadDeEntradasDeseadas(1);
        Atraccion rivendell = new Atraccion("Rivendell", 500, 120, 90, TipoDeAtraccion.Paisaje, new Posicion(500, 10));
        rivendell.setearCantidadDeEntradasDeseadas(1);
        setDeAtraccionesQueElTuristaVisitara = new HashSet();
        setDeAtraccionesQueElTuristaVisitara.add(laCasaDeBilbo);
        setDeAtraccionesQueElTuristaVisitara.add(mordor);
        setDeAtraccionesQueElTuristaVisitara.add(rivendell);


    }

    @Test
    public void siNoHayPromocioneslaReduccionDeCostoTotalDebeSerCero() {
        Set<Atraccion> setVacio = new HashSet();
        promoSinAtracciones = new Absoluta("Promo Sin Atracciones",periodoDeVigencia,setVacio,0);
        Assert.assertEquals(0, promoSinAtracciones.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara), 0);

    }

    @Test
    public void siElTuristaVisitara3AtraccionesDeLasCuales2FormanUnPaqueteConDescuentoEseDescuentoDebeSerDe300() {
        Set<Atraccion> setDeDosAtracciones = new HashSet();
        Atraccion laCasaDeBilbo = new Atraccion("La Casa De Bilbo", 200, 30, 50, TipoDeAtraccion.Degustacion, new Posicion(20, 30));
        laCasaDeBilbo.setearCantidadDeEntradasDeseadas(1);
        setDeDosAtracciones.add(laCasaDeBilbo);
        Atraccion rivendell = new Atraccion("Rivendell", 500, 120, 90, TipoDeAtraccion.Paisaje, new Posicion(500, 10));
        rivendell.setearCantidadDeEntradasDeseadas(1);
        setDeDosAtracciones.add(rivendell);
        Absoluta promoBilboMasRivendell = new Absoluta("Promo La Casa de Bilbo + Rivendell en un día", periodoDeVigencia,setDeDosAtracciones,400);

        Assert.assertEquals(300,promoBilboMasRivendell.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara),0);

    }

    @Test
    public void siElTuristaVisitara3AtraccionesDeLasCualesNingunaFormaParteDelPaqueteElDescuentoDebeSer0(){
        Set<Atraccion> setDeAtracciones = new HashSet();
        setDeAtracciones.add(new Atraccion("The Green Dragon", 10, 10, 50, TipoDeAtraccion.Degustacion, new Posicion(35, 42)));
        setDeAtracciones.add(new Atraccion("Minas Tirith", 10, 10, 50, TipoDeAtraccion.Degustacion, new Posicion(35, 42)));
        setDeAtracciones.add(new Atraccion("Ravenhill", 10, 10, 50, TipoDeAtraccion.Degustacion, new Posicion(35, 42)));

        Absoluta promo = new Absoluta("Promo",periodoDeVigencia,setDeAtracciones,400);

        Assert.assertEquals(0,promo.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara),0);

    }
}
