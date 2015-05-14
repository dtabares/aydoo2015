package dtabares.tp1;

import org.junit.Test;
import org.junit.Assert;

public class PerfilDeUsuarioTest {

    @Test
    public void laPosicionDelUsuarioDebeSerLatitudDiezYLongitudVeinte(){
        Posicion posicionActual = new Posicion(10,20);

        PerfilDeUsuario perfilDeUsuario = new PerfilDeUsuario(1000,200,20,TipoDeAtraccion.Aventura,posicionActual,new Posicion(0,0));

        Assert.assertEquals(10, perfilDeUsuario.obtenerPosicionActual().obtenerLatitud(),0);
        Assert.assertEquals(20, perfilDeUsuario.obtenerPosicionActual().obtenerLongitud(),0);

    }

    @Test
    public void elTipoDeAtraccionFavoritaDebeSerDelTipoDegustación(){
        Posicion posicionActual = new Posicion(10,20);

        PerfilDeUsuario perfilDeUsuario = new PerfilDeUsuario(1000,200,20,TipoDeAtraccion.Degustacion,posicionActual,new Posicion(0,0));

        Assert.assertEquals(TipoDeAtraccion.Degustacion, perfilDeUsuario.obtenerTipoDeAtraccionFavorita());

    }

}
