package dtabares.tp1;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class PromocionUnaAtraccionGratisComprandoCiertasAtraccionesTest {

    private Set<Atraccion> setDeAtraccionesQueElTuristaVisitara;
    private PeriodoDeVigencia periodoDeVigencia;

    @Before
    public void prepararAmbiente() {
        Calendar fechaInicio = new GregorianCalendar(2015,4,1);
        Calendar fechaFin = new GregorianCalendar(2015,4,5);
        periodoDeVigencia = new PeriodoDeVigencia(fechaInicio,fechaFin);
        setDeAtraccionesQueElTuristaVisitara = new HashSet();
        Atraccion bilbo = new Atraccion("La Casa De Bilbo",200,30,50,TipoDeAtraccion.Degustacion,new Posicion(20,30));
        Atraccion mordor = new Atraccion("Mordor", 20, 50, 90, TipoDeAtraccion.Aventura, new Posicion(50, 40));
        Atraccion rivendell = new Atraccion("Rivendell", 500, 120, 90, TipoDeAtraccion.Paisaje, new Posicion(500, 10));
        setDeAtraccionesQueElTuristaVisitara.add(bilbo);
        setDeAtraccionesQueElTuristaVisitara.add(mordor);
        setDeAtraccionesQueElTuristaVisitara.add(rivendell);


    }

    @Test
    public void siNoCompraTodasLasAtraccionesQueSonParteDeLaPromocionLaReduccionDeCostoTotalDebeSerCero() {
        Set<Atraccion> setDeAtracciones = new HashSet();
        setDeAtracciones.add(new Atraccion("La Casa De Bilbo", 300, 30, 50, TipoDeAtraccion.Degustacion, new Posicion(20, 30)));
        setDeAtracciones.add(new Atraccion("Rivendell", 500, 120, 90, TipoDeAtraccion.Paisaje, new Posicion(500, 10)));
        setDeAtracciones.add(new Atraccion("Ravenhill", 10, 10, 50, TipoDeAtraccion.Degustacion, new Posicion(35, 42)));
        PromocionUnaAtraccionGratisComprandoCiertasAtracciones promo = new PromocionUnaAtraccionGratisComprandoCiertasAtracciones("Promo", periodoDeVigencia,setDeAtracciones,new Atraccion("Ravenhill", 10, 10, 50, TipoDeAtraccion.Degustacion, new Posicion(35, 42)));

        Assert.assertEquals(0,promo.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara),0);
    }

    @Test
    public void siElTuristaVisitara3AtraccionesDeLasCuales1DeCosto300EsGratisElDescuentoDebeSerDe300() {
        Set<Atraccion> setDeAtracciones = new HashSet();
        setDeAtracciones.add(new Atraccion("La Casa De Bilbo", 300, 30, 50, TipoDeAtraccion.Degustacion, new Posicion(20, 30)));
        setDeAtracciones.add(new Atraccion("Mordor", 20, 50, 90, TipoDeAtraccion.Aventura, new Posicion(50, 40)));
        setDeAtracciones.add(new Atraccion("Rivendell", 500, 120, 90, TipoDeAtraccion.Paisaje, new Posicion(500, 10)));
        PromocionUnaAtraccionGratisComprandoCiertasAtracciones promo = new PromocionUnaAtraccionGratisComprandoCiertasAtracciones("Promo", periodoDeVigencia,setDeAtracciones,new Atraccion("Rivendell", 500, 120, 90, TipoDeAtraccion.Paisaje, new Posicion(500, 10)));

        Assert.assertEquals(500,promo.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara),0);

    }
}
