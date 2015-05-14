
package dtabares.tp1;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class GeneradorDeSugerenciasTest {

    private Atraccion mordor;
    private Atraccion rivendell;
    private Atraccion rohan;
    private Atraccion bilbo;
    private Set<Atraccion> atraccionesDisponibles;
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
    public void siElUsuarioNoTieneDineroLosItinerariosDebenEstarVacios(){
        PerfilDeUsuario perfilSinPlata = new PerfilDeUsuario(0,200,10,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0));
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(bilbo);
        atraccionesSujetasAPromo.add(mordor);
        UnaAtraccionGratisComprandoCiertasAtracciones promo = new UnaAtraccionGratisComprandoCiertasAtracciones("Promo 1",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        GeneradorDeSugerencias generador = new GeneradorDeSugerencias(perfilSinPlata,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        Assert.assertEquals(0, generador.generarSugerencia().size());
    }


    @Test
    public void siElUsuarioNoTieneTiempoLosItinerariosDebenEstarVacios(){
        PerfilDeUsuario perfilSinTiempo = new PerfilDeUsuario(200,0,10,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0));
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(bilbo);
        atraccionesSujetasAPromo.add(mordor);
        UnaAtraccionGratisComprandoCiertasAtracciones promo = new UnaAtraccionGratisComprandoCiertasAtracciones("Promo 1",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        GeneradorDeSugerencias generador = new GeneradorDeSugerencias(perfilSinTiempo,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        Assert.assertEquals(0,generador.generarSugerencia().size());
    }

    @Test
    public void conLasCaracterísticasDeEsteUsuarioDebeDevolverRivendellEnLosItinerarios(){
        PerfilDeUsuario perfil = new PerfilDeUsuario(300,500,40,TipoDeAtraccion.Paisaje,new Posicion(0,0),new Posicion(0,0));
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(bilbo);
        atraccionesSujetasAPromo.add(mordor);
        UnaAtraccionGratisComprandoCiertasAtracciones promo = new UnaAtraccionGratisComprandoCiertasAtracciones("Promo 1",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        GeneradorDeSugerencias generador = new GeneradorDeSugerencias(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        List<Sugerencia> listaDeSugerencias = generador.generarSugerencia();
        System.out.println(listaDeSugerencias.size());
        Iterator<Sugerencia> iteradorDeListaDeSugerencias = listaDeSugerencias.iterator();

        Assert.assertEquals("Rivendell", listaDeSugerencias.get(0).obtenerItinerario().obtenerItinerario().get(0).obtenerNombre());
        Assert.assertEquals("Rivendell", listaDeSugerencias.get(1).obtenerItinerario().obtenerItinerario().get(0).obtenerNombre());

    }


   @Test
    public void conElSiguientePerfilDeUsuarioDebeDevolverRohanYLuegoMordorEnEseOrdenParaAmbosItinerarios(){
        PerfilDeUsuario perfil = new PerfilDeUsuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0));
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(bilbo);
        atraccionesSujetasAPromo.add(mordor);
        UnaAtraccionGratisComprandoCiertasAtracciones promo = new UnaAtraccionGratisComprandoCiertasAtracciones("Promo 1",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        GeneradorDeSugerencias generador = new GeneradorDeSugerencias(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        List<Sugerencia> listaDeSugerencias = generador.generarSugerencia();

        Assert.assertEquals("Rohan", listaDeSugerencias.get(0).obtenerItinerario().obtenerItinerario().get(0).obtenerNombre());
        Assert.assertEquals("Mordor", listaDeSugerencias.get(0).obtenerItinerario().obtenerItinerario().get(1).obtenerNombre());
        Assert.assertEquals("Rohan", listaDeSugerencias.get(1).obtenerItinerario().obtenerItinerario().get(0).obtenerNombre());
        Assert.assertEquals("Mordor", listaDeSugerencias.get(1).obtenerItinerario().obtenerItinerario().get(1).obtenerNombre());

    }

    @Test
    public void conElSiguientePerfilDeUsuarioYLaSiguientePromoElDescuentoTotalDebeSerDe20(){
        PerfilDeUsuario perfil = new PerfilDeUsuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0));
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(rohan);
        atraccionesSujetasAPromo.add(mordor);
        UnaAtraccionGratisComprandoCiertasAtracciones promo = new UnaAtraccionGratisComprandoCiertasAtracciones("Promo Mordor Va Gratis",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        GeneradorDeSugerencias generador = new GeneradorDeSugerencias(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        List<Sugerencia> listaDeSugerencias = generador.generarSugerencia();

        Assert.assertEquals(20,listaDeSugerencias.get(0).obtenerReduccionGanadaEnPromociones(),0);
        Assert.assertEquals(20,listaDeSugerencias.get(1).obtenerReduccionGanadaEnPromociones(),0);

    }

    @Test
    public void conElSiguientePerfilDeUsuarioYLasSiguientesPromoElDescuentoTotalDebeSerDeVeintiCinco(){
        PerfilDeUsuario perfil = new PerfilDeUsuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0));
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(rohan);
        atraccionesSujetasAPromo.add(mordor);
        UnaAtraccionGratisComprandoCiertasAtracciones promo = new UnaAtraccionGratisComprandoCiertasAtracciones("Promo Mordor Va Gratis",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        Set<Atraccion> promoRohan = new HashSet<>();
        promoRohan.add(rohan);
        Porcentual promoPorcentual= new Porcentual("Diez Porciento en Rohan",10,periodoDeVigencia,promoRohan);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        promocionesDisponibles.add(promoPorcentual);
        GeneradorDeSugerencias generador = new GeneradorDeSugerencias(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        List<Sugerencia> listaDeSugerencias = generador.generarSugerencia();

        Assert.assertEquals(25,listaDeSugerencias.get(0).obtenerReduccionGanadaEnPromociones(),0);
        Assert.assertEquals(25,listaDeSugerencias.get(1).obtenerReduccionGanadaEnPromociones(),0);

    }

    @Test
    public void conElSiguientePerfilDeUsuarioYLaSiguientePromoAbsolutaElDescuentoTotalDebeSerDeDiez(){
        PerfilDeUsuario perfil = new PerfilDeUsuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0));
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(rohan);
        atraccionesSujetasAPromo.add(mordor);
        Absoluta promoAbsoluta = new Absoluta("Mordor + Rohan por 60",periodoDeVigencia,atraccionesSujetasAPromo,60);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promoAbsoluta);
        GeneradorDeSugerencias generador = new GeneradorDeSugerencias(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        List<Sugerencia> listaDeSugerencias = generador.generarSugerencia();

        Assert.assertEquals(10,listaDeSugerencias.get(0).obtenerReduccionGanadaEnPromociones(),0);
        Assert.assertEquals(10,listaDeSugerencias.get(1).obtenerReduccionGanadaEnPromociones(),0);

    }

    @Test
    public void siLaPromoNoEstaVigenteNoDebeHaberDescuento(){
        PerfilDeUsuario perfil = new PerfilDeUsuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0));
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(rohan);
        atraccionesSujetasAPromo.add(mordor);
        Absoluta promoAbsoluta = new Absoluta("Mordor + Rohan por 60",periodoDeVigencia,atraccionesSujetasAPromo,60);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promoAbsoluta);
        GeneradorDeSugerencias generador = new GeneradorDeSugerencias(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,5,3));

        List<Sugerencia> listaDeSugerencias = generador.generarSugerencia();

        Assert.assertEquals(0,listaDeSugerencias.get(0).obtenerReduccionGanadaEnPromociones(),0);
        Assert.assertEquals(0,listaDeSugerencias.get(1).obtenerReduccionGanadaEnPromociones(),0);

    }
}

