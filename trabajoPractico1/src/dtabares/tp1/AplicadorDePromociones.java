package dtabares.tp1;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class AplicadorDePromociones {

    private Set<Promocion> promocionesDisponibles;
    private Set<Promocion> promocionesAplicadas;
    private double reduccionGanadaEnPromociones;
    private Calendar fechaDeLaVisita;
    private Set<Atraccion> atraccionesQueVisitaraElTurista;


    public AplicadorDePromociones(Set<Promocion> promociones, Calendar fechaDeLaVisita,Set<Atraccion> atraccionesQueVisitaraElTurista){
        this.promocionesDisponibles = promociones;
        this.promocionesAplicadas = new HashSet<>();
        this.reduccionGanadaEnPromociones = 0;
        this.fechaDeLaVisita = fechaDeLaVisita;
        this.atraccionesQueVisitaraElTurista = atraccionesQueVisitaraElTurista;
    }

    public void aplicarPromociones(){
        Iterator<Promocion> iteradorDePromociones = this.promocionesDisponibles.iterator();
        double reduccionGanada = 0;
        while(iteradorDePromociones.hasNext()){
            Promocion promocion = iteradorDePromociones.next();

            if(promocion.estaVigente(this.fechaDeLaVisita)){
                reduccionGanada = promocion.calcularReduccionDeCostoTotal(atraccionesQueVisitaraElTurista);

                if (reduccionGanada > 0){
                    this.reduccionGanadaEnPromociones = this.reduccionGanadaEnPromociones + reduccionGanada;
                    this.promocionesAplicadas.add(promocion);
                }
            }
        }
    }

    public double obtenerReduccionGanadaEnPromociones(){
        return this.reduccionGanadaEnPromociones;
    }

    public Set<Promocion> obtenerPromocionesAplicadas(){
        return this.promocionesAplicadas;
    }
}
