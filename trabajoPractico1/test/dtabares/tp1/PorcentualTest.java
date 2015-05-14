package dtabares.tp1;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;


public class PorcentualTest {

    private Promocion promoSinAtracciones;
    private Set<Atraccion> setDeAtraccionesQueElTuristaVisitara;
    private Promocion promoConUnaSolaAtraccion;
    private PeriodoDeVigencia periodoDeVigencia;


    @Before
    public void prepararAmbiente() {
        Calendar fechaInicio = new GregorianCalendar(2015,4,1);
        Calendar fechaFin = new GregorianCalendar(2015,4,5);
        periodoDeVigencia = new PeriodoDeVigencia(fechaInicio,fechaFin);
        setDeAtraccionesQueElTuristaVisitara = new HashSet();
        Atraccion bilbo = new Atraccion("La Casa De Bilbo",200,30,50,TipoDeAtraccion.Paisaje,new Posicion(20,30));
        bilbo.setearCantidadDeEntradasDeseadas(1);
        Atraccion mordor = new Atraccion("Mordor", 20, 50, 90, TipoDeAtraccion.Paisaje, new Posicion(50, 40));
        mordor.setearCantidadDeEntradasDeseadas(1);
        setDeAtraccionesQueElTuristaVisitara.add(bilbo);
        setDeAtraccionesQueElTuristaVisitara.add(mordor);


    }

    @Test
    public void siNoHayPromocioneslaReduccionDeCostoTotalDebeSerCero() {
        Set<Atraccion> setVacio = new HashSet();
        promoSinAtracciones = new Porcentual("Promo Sin Atracciones",10,periodoDeVigencia,setVacio);
        Assert.assertEquals(0, promoSinAtracciones.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara), 0);

    }

    @Test
    public void conDescuentoDe10PorcientoEnUnaSolaAtraccionQueVale200ElDescuentoDebeSer20CuandoElSetDeAtraccionesConDescuentoTieneUnaSolaAtraccion(){
        Set<Atraccion> setDeUnaSolaAtraccion = new HashSet();
        setDeUnaSolaAtraccion.add(new Atraccion("La Casa De Bilbo",200,30,50,TipoDeAtraccion.Paisaje,new Posicion(20,30)));
        promoConUnaSolaAtraccion = new Porcentual("Promo Casa De Bilbo 10%",10,periodoDeVigencia,setDeUnaSolaAtraccion);
        Assert.assertEquals(20, promoConUnaSolaAtraccion.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara), 0);
    }

    @Test
    public void conDescuentoDe10PorcientoEnUnaSolaAtraccionQueVale200ElDescuentoDebeSer20CuandoElSetDeAtraccionesConDescuentoTieneVariasAtracciones(){
        Set<Atraccion> setDeVariasAtracciones = new HashSet();
        setDeVariasAtracciones.add(new Atraccion("La Casa De Bilbo",200,30,50,TipoDeAtraccion.Degustacion,new Posicion(20,30)));
        setDeVariasAtracciones.add(new Atraccion("Rohan",500,30,50,TipoDeAtraccion.Aventura,new Posicion(100,30)));
        setDeVariasAtracciones.add(new Atraccion("Rivendell",500,30,50,TipoDeAtraccion.Paisaje,new Posicion(100,60)));
        promoConUnaSolaAtraccion = new Porcentual("Promo Casa De Bilbo 10%",10,periodoDeVigencia,setDeVariasAtracciones);
        Assert.assertEquals(20, promoConUnaSolaAtraccion.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara), 0);
    }

    @Test
    public void conDescuentoDe10PorcientoEnDosAtraccionesElDescuentoDebeSer80(){
        Set<Atraccion> setDeVariasAtracciones = new HashSet();
        setDeVariasAtracciones.add(new Atraccion("La Casa De Bilbo",200,30,50,TipoDeAtraccion.Degustacion,new Posicion(20,30)));
        setDeVariasAtracciones.add(new Atraccion("Rohan",600,30,50,TipoDeAtraccion.Aventura,new Posicion(100,30)));
        setDeVariasAtracciones.add(new Atraccion("Rivendell",500,30,50,TipoDeAtraccion.Paisaje,new Posicion(100,60)));
        promoConUnaSolaAtraccion = new Porcentual("Promo Casa De Bilbo 10%",10,periodoDeVigencia,setDeVariasAtracciones);
        Atraccion rohan = new Atraccion("Rohan",600,30,50,TipoDeAtraccion.Aventura,new Posicion(100,30));
        rohan.setearCantidadDeEntradasDeseadas(1);
        setDeAtraccionesQueElTuristaVisitara.add(rohan);
        Assert.assertEquals(80, promoConUnaSolaAtraccion.calcularReduccionDeCostoTotal(setDeAtraccionesQueElTuristaVisitara), 0);
    }
}