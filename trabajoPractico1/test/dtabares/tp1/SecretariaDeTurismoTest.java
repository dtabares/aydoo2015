
package dtabares.tp1;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class SecretariaDeTurismoTest {

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
        rivendell = new Atraccion("Rivendell",300,40,50,TipoDeAtraccion.Paisaje,new Posicion(-5,5));
        rohan = new Atraccion("Rohan",50,10,40,TipoDeAtraccion.Aventura,new Posicion(-10,-10));
        bilbo = new Atraccion("La Casa De Bilbo",350,30,10,TipoDeAtraccion.Degustacion,new Posicion(10,10));
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
        Usuario perfilSinPlata = new Usuario(0,200,10,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(bilbo);
        atraccionesSujetasAPromo.add(mordor);
        PromocionUnaAtraccionGratisComprandoCiertasAtracciones promo = new PromocionUnaAtraccionGratisComprandoCiertasAtracciones("Promo 1",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfilSinPlata,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        Assert.assertEquals(0, secretariaDeTurismo.generarSugerencia().size());
    }


    @Test
    public void siElUsuarioNoTieneTiempoLosItinerariosDebenEstarVacios(){
        Usuario perfilSinTiempo = new Usuario(200,0,10,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(bilbo);
        atraccionesSujetasAPromo.add(mordor);
        PromocionUnaAtraccionGratisComprandoCiertasAtracciones promo = new PromocionUnaAtraccionGratisComprandoCiertasAtracciones("Promo 1",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfilSinTiempo,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        Assert.assertEquals(0, secretariaDeTurismo.generarSugerencia().size());
    }

    @Test
    public void conLasCaracterísticasDeEsteUsuarioDebeDevolverRivendellEnLosItinerarios(){
        Usuario perfil = new Usuario(300,500,40,TipoDeAtraccion.Paisaje,new Posicion(0,0),new Posicion(0,0),1);
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(bilbo);
        atraccionesSujetasAPromo.add(mordor);
        PromocionUnaAtraccionGratisComprandoCiertasAtracciones promo = new PromocionUnaAtraccionGratisComprandoCiertasAtracciones("Promo 1",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        List<Sugerencia> listaDeSugerencias = secretariaDeTurismo.generarSugerencia();
        Iterator<Sugerencia> iteradorDeListaDeSugerencias = listaDeSugerencias.iterator();

        Assert.assertEquals("Rivendell", listaDeSugerencias.get(0).obtenerItinerario().obtenerItinerario().get(0).obtenerNombre());
        Assert.assertEquals("Rivendell", listaDeSugerencias.get(1).obtenerItinerario().obtenerItinerario().get(0).obtenerNombre());

    }


   @Test
    public void conElSiguientePerfilDeUsuarioDebeDevolverRohanYLuegoMordorEnEseOrdenParaAmbosItinerarios(){
        Usuario perfil = new Usuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(bilbo);
        atraccionesSujetasAPromo.add(mordor);
        PromocionUnaAtraccionGratisComprandoCiertasAtracciones promo = new PromocionUnaAtraccionGratisComprandoCiertasAtracciones("Promo 1",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        List<Sugerencia> listaDeSugerencias = secretariaDeTurismo.generarSugerencia();

        Assert.assertEquals("Rohan", listaDeSugerencias.get(0).obtenerItinerario().obtenerItinerario().get(0).obtenerNombre());
        Assert.assertEquals("Mordor", listaDeSugerencias.get(0).obtenerItinerario().obtenerItinerario().get(1).obtenerNombre());
        Assert.assertEquals("Rohan", listaDeSugerencias.get(1).obtenerItinerario().obtenerItinerario().get(0).obtenerNombre());
        Assert.assertEquals("Mordor", listaDeSugerencias.get(1).obtenerItinerario().obtenerItinerario().get(1).obtenerNombre());

    }

    @Test
    public void conElSiguientePerfilDeUsuarioYLaSiguientePromoElDescuentoTotalDebeSerDe20(){
        Usuario perfil = new Usuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(rohan);
        atraccionesSujetasAPromo.add(mordor);
        PromocionUnaAtraccionGratisComprandoCiertasAtracciones promo = new PromocionUnaAtraccionGratisComprandoCiertasAtracciones("Promo Mordor Va Gratis",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        List<Sugerencia> listaDeSugerencias = secretariaDeTurismo.generarSugerencia();

        Assert.assertEquals(20,listaDeSugerencias.get(0).obtenerReduccionGanadaEnPromociones(),0);
        Assert.assertEquals(20,listaDeSugerencias.get(1).obtenerReduccionGanadaEnPromociones(),0);

    }

    @Test
    public void conElSiguientePerfilDeUsuarioYLasSiguientesPromoElDescuentoTotalDebeSerDeVeintiCinco(){
        Usuario perfil = new Usuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(rohan);
        atraccionesSujetasAPromo.add(mordor);
        PromocionUnaAtraccionGratisComprandoCiertasAtracciones promo = new PromocionUnaAtraccionGratisComprandoCiertasAtracciones("Promo Mordor Va Gratis",periodoDeVigencia,atraccionesSujetasAPromo,mordor);
        Set<Atraccion> promoRohan = new HashSet<>();
        promoRohan.add(rohan);
        PromocionPorcentual promoPromocionPorcentual = new PromocionPorcentual("Diez Porciento en Rohan",10,periodoDeVigencia,promoRohan);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promo);
        promocionesDisponibles.add(promoPromocionPorcentual);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        List<Sugerencia> listaDeSugerencias = secretariaDeTurismo.generarSugerencia();

        Assert.assertEquals(25,listaDeSugerencias.get(0).obtenerReduccionGanadaEnPromociones(),0);
        Assert.assertEquals(25,listaDeSugerencias.get(1).obtenerReduccionGanadaEnPromociones(),0);

    }

    @Test
    public void conElSiguientePerfilDeUsuarioYLaSiguientePromoAbsolutaElDescuentoTotalDebeSerDeDiez(){
        Usuario perfil = new Usuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(rohan);
        atraccionesSujetasAPromo.add(mordor);
        PromocionPaqueteDeAtracciones promoAbsoluta = new PromocionPaqueteDeAtracciones("Mordor + Rohan por 60",periodoDeVigencia,atraccionesSujetasAPromo,60);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promoAbsoluta);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,4,3));

        List<Sugerencia> listaDeSugerencias = secretariaDeTurismo.generarSugerencia();

        Assert.assertEquals(10,listaDeSugerencias.get(0).obtenerReduccionGanadaEnPromociones(),0);
        Assert.assertEquals(10,listaDeSugerencias.get(1).obtenerReduccionGanadaEnPromociones(),0);

    }

    @Test
    public void siLaPromoNoEstaVigenteNoDebeHaberDescuento(){
        Usuario perfil = new Usuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        Set<Atraccion> atraccionesSujetasAPromo = new HashSet();
        atraccionesSujetasAPromo.add(rohan);
        atraccionesSujetasAPromo.add(mordor);
        PromocionPaqueteDeAtracciones promoAbsoluta = new PromocionPaqueteDeAtracciones("Mordor + Rohan por 60",periodoDeVigencia,atraccionesSujetasAPromo,60);
        promocionesDisponibles = new HashSet();
        promocionesDisponibles.add(promoAbsoluta);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,5,3));

        List<Sugerencia> listaDeSugerencias = secretariaDeTurismo.generarSugerencia();

        Assert.assertEquals(0, listaDeSugerencias.get(0).obtenerReduccionGanadaEnPromociones(), 0);
        Assert.assertEquals(0, listaDeSugerencias.get(1).obtenerReduccionGanadaEnPromociones(), 0);

    }

    @Test
    public void siEstoyEnLaPosicionCeroCeroLaMasCercanaDebeSerRivendell(){
        Usuario perfil = new Usuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        //atraccionesDisponibles = new HashSet();
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(rivendell);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,5,3));
        Assert.assertTrue(setEsperado.containsAll(secretariaDeTurismo.buscarAtraccionMasCercana(new Posicion(0, 0), atraccionesDisponibles)));
    }



    @Test
    public void siEstoyEnLaPosicionDeRivendellLasMasCercanasDebenSerRohanYBilbo(){
        mordor = new Atraccion("Mordor",20,50,10,TipoDeAtraccion.Aventura,new Posicion(200,20));
        rivendell = new Atraccion("Rivendell",300,40,50,TipoDeAtraccion.Paisaje,new Posicion(-5,5));
        rohan = new Atraccion("Rohan",20,50,10,TipoDeAtraccion.Aventura,new Posicion(-10,-10));
        bilbo = new Atraccion("La Casa De Bilbo",50,50,10,TipoDeAtraccion.Degustacion,new Posicion(10,10));
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(rohan);
        setEsperado.add(bilbo);
        Usuario perfil = new Usuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,5,3));

        Assert.assertTrue(setEsperado.containsAll(secretariaDeTurismo.buscarAtraccionMasCercana(rivendell.obtenerPosicion(), atraccionesDisponibles)));
    }

    @Test
    public void siAgregoTodasLasAtracionesPreparadasAlSetDeberíaDevolverMordorYRohan(){
        mordor = new Atraccion("Mordor",20,50,10,TipoDeAtraccion.Aventura,new Posicion(200,20));
        rivendell = new Atraccion("Rivendell",300,40,50,TipoDeAtraccion.Paisaje,new Posicion(-5,5));
        rohan = new Atraccion("Rohan",20,50,10,TipoDeAtraccion.Aventura,new Posicion(-10,-10));
        bilbo = new Atraccion("La Casa De Bilbo",50,50,10,TipoDeAtraccion.Degustacion,new Posicion(10,10));
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);

        Set<Atraccion> atraccionesEsperadas = new HashSet();
        atraccionesEsperadas.add(mordor);
        atraccionesEsperadas.add(rohan);
        Usuario perfil = new Usuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,5,3));

        Assert.assertTrue(atraccionesEsperadas.containsAll(secretariaDeTurismo.buscarAtraccionesPorCosto(atraccionesDisponibles)));
    }



    @Test
    public void siLeGustanLasAtraccionesDeDegustacionDeberíaDevolverLaCasaDeBilbo(){
        mordor = new Atraccion("Mordor",20,50,10,TipoDeAtraccion.Aventura,new Posicion(200,20));
        rivendell = new Atraccion("Rivendell",300,40,50,TipoDeAtraccion.Paisaje,new Posicion(-5,5));
        rohan = new Atraccion("Rohan",20,50,10,TipoDeAtraccion.Aventura,new Posicion(-10,-10));
        bilbo = new Atraccion("La Casa De Bilbo",50,50,10,TipoDeAtraccion.Degustacion,new Posicion(10,10));
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(bilbo);
        Usuario perfil = new Usuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,5,3));

        Assert.assertTrue(setEsperado.containsAll(secretariaDeTurismo.buscarAtraccionesPorPreferencia(atraccionesDisponibles, TipoDeAtraccion.Degustacion)));

    }

    @Test
    public void siLeGustanLasAtraccionesDeAventuraDeberiaDevolverMordorYRohan(){
        mordor = new Atraccion("Mordor",20,50,10,TipoDeAtraccion.Aventura,new Posicion(200,20));
        rivendell = new Atraccion("Rivendell",300,40,50,TipoDeAtraccion.Paisaje,new Posicion(-5,5));
        rohan = new Atraccion("Rohan",20,50,10,TipoDeAtraccion.Aventura,new Posicion(-10,-10));
        bilbo = new Atraccion("La Casa De Bilbo",50,50,10,TipoDeAtraccion.Degustacion,new Posicion(10,10));
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        Set<Atraccion> setEsperado = new HashSet();
        setEsperado.add(mordor);
        setEsperado.add(rohan);
        Usuario perfil = new Usuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,5,3));

        Assert.assertTrue(setEsperado.containsAll(secretariaDeTurismo.buscarAtraccionesPorPreferencia(atraccionesDisponibles, TipoDeAtraccion.Aventura)));

    }

    @Test
    public void siLeGustanLasDePaisajeYNoHayNingunaElSetDebeEstarVacio(){
        mordor = new Atraccion("Mordor",20,50,10,TipoDeAtraccion.Aventura,new Posicion(200,20));
        rivendell = new Atraccion("Rivendell",300,40,50,TipoDeAtraccion.Paisaje,new Posicion(-5,5));
        rohan = new Atraccion("Rohan",20,50,10,TipoDeAtraccion.Aventura,new Posicion(-10,-10));
        bilbo = new Atraccion("La Casa De Bilbo",50,50,10,TipoDeAtraccion.Degustacion,new Posicion(10,10));
        atraccionesDisponibles.add(mordor);
        atraccionesDisponibles.add(rivendell);
        atraccionesDisponibles.add(rohan);
        atraccionesDisponibles.add(bilbo);
        atraccionesDisponibles.remove(rivendell);
        Usuario perfil = new Usuario(70,500,40,TipoDeAtraccion.Aventura,new Posicion(0,0),new Posicion(0,0),1);
        SecretariaDeTurismo secretariaDeTurismo = new SecretariaDeTurismo(perfil,promocionesDisponibles,atraccionesDisponibles,new GregorianCalendar(2015,5,3));

        Assert.assertEquals(0, secretariaDeTurismo.buscarAtraccionesPorPreferencia(atraccionesDisponibles, TipoDeAtraccion.Paisaje).size());
    }
}

