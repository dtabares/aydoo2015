package dtabares.tp1;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class PromocionPaqueteFamiliarTest {

    private Set<Atraccion> setDeAtraccionesQueElTuristaVisitara;
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


/*
        laCasaDeBilbo.setearCantidadDeEntradasDeseadas(3);
        mordor.setearCantidadDeEntradasDeseadas(4);
        rivendell.setearCantidadDeEntradasDeseadas(10);*/
    }

    @Test
    public void siCompraTresEntradasLaPromocionNoDeberiaAplicarYElDescuentoDebeSer0(){
        Usuario usuario = new Usuario(1000,200,20,TipoDeAtraccion.Degustacion,new Posicion(0,0),new Posicion(0,0),3);
        setDeAtraccionesQueElTuristaVisitara = new HashSet();
        setDeAtraccionesQueElTuristaVisitara.add(laCasaDeBilbo);
        PromocionPaqueteFamiliar promocionPaqueteFamiliar = new PromocionPaqueteFamiliar("Super Paquete Familiar",periodoDeVigencia,usuario);

        Assert.assertEquals(0, promocionPaqueteFamiliar.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara), 0);

    }

    @Test
    public void siCompraCuatroEntradasDebeAplicarElDescuentoDel10PorcientoYElDescuentoDebeSer8(){
        setDeAtraccionesQueElTuristaVisitara = new HashSet();
        setDeAtraccionesQueElTuristaVisitara.add(mordor);
        Usuario usuario = new Usuario(1000,200,20,TipoDeAtraccion.Degustacion,new Posicion(0,0),new Posicion(0,0),4);
        PromocionPaqueteFamiliar promocionPaqueteFamiliar = new PromocionPaqueteFamiliar("Super Paquete Familiar",periodoDeVigencia,usuario);

        Assert.assertEquals(8, promocionPaqueteFamiliar.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara),0);

    }

    @Test
    public void siCompra10EntradasDebeAplicarElDescuentoBaseYElAdicionalYElDescuentoDebeSer1100(){
        setDeAtraccionesQueElTuristaVisitara = new HashSet();
        setDeAtraccionesQueElTuristaVisitara.add(rivendell);
        Usuario usuario = new Usuario(1000,200,20,TipoDeAtraccion.Degustacion,new Posicion(0,0),new Posicion(0,0),10);
        PromocionPaqueteFamiliar promocionPaqueteFamiliar = new PromocionPaqueteFamiliar("Super Paquete Familiar",periodoDeVigencia,usuario);

        Assert.assertEquals(1100, promocionPaqueteFamiliar.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara),0);
    }

}
