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
    private PerfilDeUsuario perfilDeUsuario;


    public AplicadorDePromociones(Set<Promocion> promociones, Calendar fechaDeLaVisita,Set<Atraccion> atraccionesQueVisitaraElTurista,PerfilDeUsuario perfilDeUsuario){
        this.promocionesDisponibles = promociones;
        this.promocionesAplicadas = new HashSet<>();
        this.reduccionGanadaEnPromociones = 0;
        this.fechaDeLaVisita = fechaDeLaVisita;
        this.atraccionesQueVisitaraElTurista = atraccionesQueVisitaraElTurista;
        this.perfilDeUsuario = perfilDeUsuario;
    }

    public void aplicarPromociones(){
        double reduccionGanada = 0;
        boolean salir = false;
        Iterator<Promocion> iteradorDePromociones = this.promocionesDisponibles.iterator();
        while(iteradorDePromociones.hasNext() && salir == false){
            Promocion promocion = iteradorDePromociones.next();

            if(promocion.estaVigente(this.fechaDeLaVisita)){
                reduccionGanada = promocion.calcularReduccionDeCostoTotal(atraccionesQueVisitaraElTurista);
                if (reduccionGanada > 0){
                    if(promocion.getClass().getName().equals("dtabares.tp1.PromoExtranjero")){
                        salir = true;
                        this.reduccionGanadaEnPromociones = reduccionGanada;
                        this.promocionesAplicadas.clear();
                        this.promocionesAplicadas.add(promocion);
                    }
                    else {
                        this.reduccionGanadaEnPromociones = this.reduccionGanadaEnPromociones + reduccionGanada;
                        this.promocionesAplicadas.add(promocion);
                    }
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
