package dtabares.tp1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PeriodoDeVigenciaTest {

    @Test
    public void laVigenciaDebeDevolverFalseCuandoLaFechaAValidarEsSuperiorALaFechaFin(){

        Calendar fechaInicio = new GregorianCalendar(2015,4,1);
        Calendar fechaFin = new GregorianCalendar(2015,4,5);
        Calendar fechaAValidar = new GregorianCalendar(2015,4,6);


        PeriodoDeVigencia periodoDeVigencia = new PeriodoDeVigencia(fechaInicio,fechaFin);

        Assert.assertEquals(false,periodoDeVigencia.estaVigente(fechaAValidar));
    }

    @Test
    public void laVigenciaDebeDevolverFalseCuandoLaFechaAValidarEsInferiorALaFechaInicio(){

        Calendar fechaInicio = new GregorianCalendar(2015,4,1);
        Calendar fechaFin = new GregorianCalendar(2015,4,5);
        Calendar fechaAValidar = new GregorianCalendar(2015,3,25);


        PeriodoDeVigencia periodoDeVigencia = new PeriodoDeVigencia(fechaInicio,fechaFin);

        Assert.assertEquals(false,periodoDeVigencia.estaVigente(fechaAValidar));
    }

    @Test
    public void laVigenciaDebeDevolverTrueCuandoLaFechaAValidarEstaDentroDeRango(){

        Calendar fechaInicio = new GregorianCalendar(2015,4,1);
        Calendar fechaFin = new GregorianCalendar(2015,4,5);
        Calendar fechaAValidar = new GregorianCalendar(2015,4,4);


        PeriodoDeVigencia periodoDeVigencia = new PeriodoDeVigencia(fechaInicio,fechaFin);


        Assert.assertEquals(true,periodoDeVigencia.estaVigente(fechaAValidar));
    }

    @Test
    public void laVigenciaDebeDevolverTrueCuandoLaFechaAValidarEsIgualAlLimiteInferior(){

        Calendar fechaInicio = new GregorianCalendar(2015,4,1);
        Calendar fechaFin = new GregorianCalendar(2015,4,5);
        Calendar fechaAValidar = new GregorianCalendar(2015,4,1);


        PeriodoDeVigencia periodoDeVigencia = new PeriodoDeVigencia(fechaInicio,fechaFin);


        Assert.assertEquals(true,periodoDeVigencia.estaVigente(fechaAValidar));
    }

    @Test
    public void laVigenciaDebeDevolverTrueCuandoLaFechaAValidarEsIgualAlLimiteSuperior(){

        Calendar fechaInicio = new GregorianCalendar(2015,4,1);
        Calendar fechaFin = new GregorianCalendar(2015,4,5);
        Calendar fechaAValidar = new GregorianCalendar(2015,4,5);


        PeriodoDeVigencia periodoDeVigencia = new PeriodoDeVigencia(fechaInicio,fechaFin);


        Assert.assertEquals(true,periodoDeVigencia.estaVigente(fechaAValidar));
    }
}
