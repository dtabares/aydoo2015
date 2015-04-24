package dtabares.tp1;

import org.junit.Test;
import org.junit.Assert;

public class UsuarioTest{

    @Test
    public void laPosicionDelUsuarioDebeSerLatitudDiezYLongitudVeinte(){
        Posicion posicionActual = new Posicion(10,20);

        Usuario usuario = new Usuario(1000,200,20,TipoDeAtraccion.Paisaje,posicionActual);

        Assert.assertEquals(10,usuario.obtenerPosicionActual().obtenerLatitud(),0);
        Assert.assertEquals(20,usuario.obtenerPosicionActual().obtenerLongitud(),0);

    }

}
