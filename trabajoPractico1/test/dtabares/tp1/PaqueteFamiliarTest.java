package dtabares.tp1;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class PaqueteFamiliarTest {


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


/*        setDeAtraccionesQueElTuristaVisitara = new HashSet();
        setDeAtraccionesQueElTuristaVisitara.add(laCasaDeBilbo);
        setDeAtraccionesQueElTuristaVisitara.add(mordor);
        setDeAtraccionesQueElTuristaVisitara.add(rivendell);*/
    }

    @Test
    public void siCompraTresEntradasLaPromocionNoDeberiaAplicarYElDescuentoDebeSer0(){

        setDeAtraccionesQueElTuristaVisitara = new HashSet();
        setDeAtraccionesQueElTuristaVisitara.add(laCasaDeBilbo);
        PaqueteFamiliar paqueteFamiliar = new PaqueteFamiliar("Super Paquete Familiar",periodoDeVigencia);

        Assert.assertEquals(0, paqueteFamiliar.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara), 0);

    }

    @Test
    public void siCompraCuatroEntradasDebeAplicarElDescuentoDel10PorcientoYElDescuentoDebeSer8(){
        setDeAtraccionesQueElTuristaVisitara = new HashSet();
        setDeAtraccionesQueElTuristaVisitara.add(mordor);
        PaqueteFamiliar paqueteFamiliar = new PaqueteFamiliar("Super Paquete Familiar",periodoDeVigencia);

        Assert.assertEquals(8,paqueteFamiliar.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara),0);

    }

    @Test
    public void siCompra10EntradasDebeAplicarElDescuentoBaseYElAdicionalYElDescuentoDebeSer1100(){
        setDeAtraccionesQueElTuristaVisitara = new HashSet();
        setDeAtraccionesQueElTuristaVisitara.add(rivendell);
        PaqueteFamiliar paqueteFamiliar = new PaqueteFamiliar("Super Paquete Familiar",periodoDeVigencia);

        Assert.assertEquals(1100,paqueteFamiliar.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara),0);
    }

    @Test
    public void siCompra4EnMordor10EnRivendellY3EnBilboElDescuentoDebeSer1108(){
        setDeAtraccionesQueElTuristaVisitara = new HashSet();
        setDeAtraccionesQueElTuristaVisitara.add(mordor);
        setDeAtraccionesQueElTuristaVisitara.add(rivendell);
        setDeAtraccionesQueElTuristaVisitara.add(laCasaDeBilbo);
        PaqueteFamiliar paqueteFamiliar = new PaqueteFamiliar("Super Paquete Familiar",periodoDeVigencia);
        Assert.assertEquals(1108,paqueteFamiliar.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara),0);
    }
}
