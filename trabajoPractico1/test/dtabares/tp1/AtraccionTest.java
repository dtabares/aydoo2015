package dtabares.tp1;

import org.junit.Test;
import org.junit.Assert;


public class AtraccionTest {

    @Test
    public void crearUnaAttraccionYaccederASuLatitud(){
        String nombre = "La Casa de Bilbo";
        double costoUnitario = 200;
        int duracionPromedioDeVisitaEnMins = 20;
        int cupoDeVisitantesDiarios = 500;
        TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.Aventura;
        Posicion posicion = new Posicion(-34.6037232,-58.3815931 );
        Atraccion atraccionDePrueba = new Atraccion (nombre, costoUnitario, duracionPromedioDeVisitaEnMins, cupoDeVisitantesDiarios, tipoDeAtraccion, posicion);

        Assert.assertEquals(-34.6037232,atraccionDePrueba.obtenerPosicion().obtenerLatitud(),0);
    }

    @Test
    public void nombreDeLaAtraccionDebeSerLaCasaDeBilbo(){
        String nombre = "La Casa de Bilbo";
        double costoUnitario = 200;
        int duracionPromedioDeVisitaEnMins = 20;
        int cupoDeVisitantesDiarios = 500;
        TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.Aventura;
        Posicion posicion = new Posicion(-34.6037232,-58.3815931 );
        Atraccion atraccionDePrueba = new Atraccion (nombre, costoUnitario, duracionPromedioDeVisitaEnMins, cupoDeVisitantesDiarios, tipoDeAtraccion, posicion);

        Assert.assertEquals("La Casa de Bilbo",atraccionDePrueba.obtenerNombre());
    }

    @Test
    public void elTipoDeAtraccionDebeSerAventura(){
        String nombre = "La Casa de Bilbo";
        double costoUnitario = 200;
        int duracionPromedioDeVisitaEnMins = 20;
        int cupoDeVisitantesDiarios = 500;
        TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.Aventura;
        Posicion posicion = new Posicion(-34.6037232,-58.3815931 );
        Atraccion atraccionDePrueba = new Atraccion (nombre, costoUnitario, duracionPromedioDeVisitaEnMins, cupoDeVisitantesDiarios, tipoDeAtraccion, posicion);

        Assert.assertEquals(TipoDeAtraccion.Aventura,atraccionDePrueba.obtenerTipoDeAtraccion());
    }

    @Test
    public void compararDosAtraccionesConElMismoNombreDebeDevolverCero(){
        String nombre = "La Casa de Bilbo";
        double costoUnitario = 200;
        int duracionPromedioDeVisitaEnMins = 20;
        int cupoDeVisitantesDiarios = 500;
        TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.Aventura;
        Posicion posicion = new Posicion(-34.6037232,-58.3815931 );
        Atraccion atraccionDePrueba = new Atraccion (nombre, costoUnitario, duracionPromedioDeVisitaEnMins, cupoDeVisitantesDiarios, tipoDeAtraccion, posicion);
        Atraccion otraAtraccion = new Atraccion (nombre, 300, duracionPromedioDeVisitaEnMins, cupoDeVisitantesDiarios, tipoDeAtraccion, posicion);

        Assert.assertEquals(0,atraccionDePrueba.compareTo(otraAtraccion));
    }


    @Test
    public void compararDosAtraccionesConDistintoNombreDebeDevolverDistintoDeCero(){

        String nombre = "La Casa de Bilbo";
        double costoUnitario = 200;
        int duracionPromedioDeVisitaEnMins = 20;
        int cupoDeVisitantesDiarios = 500;
        TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.Aventura;
        Posicion posicion = new Posicion(-34.6037232,-58.3815931 );
        Atraccion atraccionDePrueba = new Atraccion (nombre, costoUnitario, duracionPromedioDeVisitaEnMins, cupoDeVisitantesDiarios, tipoDeAtraccion, posicion);
        Atraccion otraAtraccion = new Atraccion ("Mordor", costoUnitario, duracionPromedioDeVisitaEnMins, cupoDeVisitantesDiarios, tipoDeAtraccion, posicion);

        Assert.assertEquals(-1,atraccionDePrueba.compareTo(otraAtraccion));

    }
    
    @Test
    public void siElCostoUnitarioEs200YCompro5EntradasElCostoTotalDebeSer1000(){
        String nombre = "La Casa de Bilbo";
        double costoUnitario = 200;
        int duracionPromedioDeVisitaEnMins = 20;
        int cupoDeVisitantesDiarios = 500;
        TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.Aventura;
        Posicion posicion = new Posicion(-34.6037232,-58.3815931 );
        Atraccion atraccionDePrueba = new Atraccion (nombre, costoUnitario, duracionPromedioDeVisitaEnMins, cupoDeVisitantesDiarios, tipoDeAtraccion, posicion);
        atraccionDePrueba.setearCantidadDeEntradasDeseadas(5);

        Assert.assertEquals(1000,atraccionDePrueba.obtenerCostoTotal(),0);
    }

}