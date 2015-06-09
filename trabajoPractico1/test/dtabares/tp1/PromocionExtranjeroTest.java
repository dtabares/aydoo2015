package dtabares.tp1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;


public class PromocionExtranjeroTest {


    private Promocion promoSinAtracciones;
    private Set<Atraccion> setDeAtraccionesQueElTuristaVisitara;
    private Promocion promoConUnaSolaAtraccion;
    private PeriodoDeVigencia periodoDeVigencia;
    private Atraccion laCasaDeBilbo;
    private Atraccion mordor;
    private Atraccion rivendell;

    @Before
    public void prepararAmbiente() {
        Calendar fechaInicio = new GregorianCalendar(2015,4,1);
        Calendar fechaFin = new GregorianCalendar(2015,4,5);
        periodoDeVigencia = new PeriodoDeVigencia(fechaInicio,fechaFin);

        laCasaDeBilbo = new Atraccion("La Casa De Bilbo", 200, 30, 50, TipoDeAtraccion.Degustacion, new Posicion(20, 30));
        mordor = new Atraccion("Mordor", 20, 50, 90, TipoDeAtraccion.Aventura, new Posicion(50, 40));
        rivendell = new Atraccion("Rivendell", 500, 120, 90, TipoDeAtraccion.Paisaje, new Posicion(500, 10));


        laCasaDeBilbo.setearCantidadDeEntradasDeseadas(3);
        mordor.setearCantidadDeEntradasDeseadas(4);
        rivendell.setearCantidadDeEntradasDeseadas(10);
    }

    @Test
    public void SiViveAMenosDe200DeLaAtraccionMasCercanaElDescuentoDebeSerCero(){
        Posicion domicilio = new Posicion(0,-0);
        PerfilDeUsuario perfilDeUsuario = new PerfilDeUsuario(200,500,20,TipoDeAtraccion.Aventura,new Posicion(0,0),domicilio);
        setDeAtraccionesQueElTuristaVisitara = new HashSet();
        setDeAtraccionesQueElTuristaVisitara.add(laCasaDeBilbo);
        setDeAtraccionesQueElTuristaVisitara.add(mordor);
        setDeAtraccionesQueElTuristaVisitara.add(rivendell);

        PromocionExtranjero promocionExtranjero = new PromocionExtranjero("Si vivis a mas de 200 todas las atracciones a mitad de precio",periodoDeVigencia,perfilDeUsuario);

        Assert.assertEquals(0, promocionExtranjero.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara),0);

    }

     @Test
    public void SiViveAMasDe200DeLaAtraccionMasCercanaElDescuentoDebeSer2840(){
         Posicion domicilio = new Posicion(-150,-100);
         PerfilDeUsuario perfilDeUsuario = new PerfilDeUsuario(200,500,20,TipoDeAtraccion.Aventura,new Posicion(0,0),domicilio);
         setDeAtraccionesQueElTuristaVisitara = new HashSet();
         setDeAtraccionesQueElTuristaVisitara.add(laCasaDeBilbo);
         setDeAtraccionesQueElTuristaVisitara.add(mordor);
         setDeAtraccionesQueElTuristaVisitara.add(rivendell);

         PromocionExtranjero promocionExtranjero = new PromocionExtranjero("Si vivis a mas de 200 todas las atracciones a mitad de precio",periodoDeVigencia,perfilDeUsuario);

         Assert.assertEquals(2840, promocionExtranjero.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara),0);
    }

}
