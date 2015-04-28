package dtabares.tp1;


import java.util.ArrayList;
import java.util.List;

public class Itinerario {

    private List<Atraccion> listaDeAtracciones;

    public Itinerario(){
        this.listaDeAtracciones = new ArrayList();
    }

    public void agregarAlitinerario(Atraccion atraccion){
        this.listaDeAtracciones.add(atraccion);
    }

    public List<Atraccion> obtenerItinerario(){
        return this.listaDeAtracciones;
    }
}
