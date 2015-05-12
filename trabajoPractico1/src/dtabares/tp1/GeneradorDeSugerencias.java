package dtabares.tp1;


import java.util.*;

public class GeneradorDeSugerencias {

    private PerfilDeUsuario perfilDeUsuario;
    private Set<Promocion> promociones;
    private List<Sugerencia> sugerencias;
    private Set<Atraccion> atraccionesDelParque;
    private Calendar fechaDeLaVisita;
    private BuscadorDeAtracciones buscadorDeAtracciones;

    public GeneradorDeSugerencias(PerfilDeUsuario perfilDeUsuario,Set<Promocion> promociones,Set<Atraccion> atraccionesDelParque,Calendar fechaDeLaVisita){
        this.perfilDeUsuario = perfilDeUsuario;
        this.promociones = promociones;
        this.sugerencias = new ArrayList<>();
        this.atraccionesDelParque = atraccionesDelParque;
        this.fechaDeLaVisita = fechaDeLaVisita;
        this.buscadorDeAtracciones = new BuscadorDeAtracciones();
    }

    public List<Sugerencia> generarSugerencia(){

        this.generarItinerarioPriorizandoGustosDelUsuario();
        this.generarItinerarioPriorizandoRecorrerLaMaxCantidadDeAtracciones();

        return sugerencias;

    }

    private void generarItinerarioPriorizandoGustosDelUsuario(){
        Itinerario itinerarioPriorizandoGustosDelUsuario = new Itinerario();
        Set<Atraccion> atraccionesQueLeFaltarianRecorrer = new HashSet(this.atraccionesDelParque);
        Set<Atraccion> atraccionesQueLeFaltanRecorrerYSonDeSuPreferencia = this.buscadorDeAtracciones.buscarAtraccionesPorPreferencia(this.atraccionesDelParque, this.perfilDeUsuario.obtenerTipoDeAtraccionFavorita());
        double dineroDelUsuarioRestante = this.perfilDeUsuario.obtenerPresupuestoDisponible();
        double tiempoParaVisitasRestante = this.perfilDeUsuario.obtenerTiempoDisponibleParaVisitas();
        Posicion posicionActualDelUsuario = this.perfilDeUsuario.obtenerPosicionActual();
        double reduccionGanadaEnPromociones = 0;
        Iterator<Promocion> iteradorDePromociones = this.promociones.iterator();

        Set<Atraccion> atraccionesQueLeFaltanRecorrerYSonDeSuPreferenciaMasCercanas = BuscadorDeAtraccionesCercanas.buscarAtraccionMasCercana(posicionActualDelUsuario, atraccionesQueLeFaltanRecorrerYSonDeSuPreferencia);

        while (atraccionesQueLeFaltanRecorrerYSonDeSuPreferencia.size() >0 ){
            if (atraccionesQueLeFaltanRecorrerYSonDeSuPreferenciaMasCercanas.size() == 1){
                Atraccion atraccionPreferidaMasCercana = atraccionesQueLeFaltanRecorrerYSonDeSuPreferenciaMasCercanas.iterator().next();
                if (this.seDanLasCondiciones(atraccionPreferidaMasCercana,dineroDelUsuarioRestante,tiempoParaVisitasRestante,posicionActualDelUsuario)){
                    itinerarioPriorizandoGustosDelUsuario.agregarAlitinerario(atraccionPreferidaMasCercana);
                    dineroDelUsuarioRestante = dineroDelUsuarioRestante - atraccionPreferidaMasCercana.obtenerCosto();
                    tiempoParaVisitasRestante = tiempoParaVisitasRestante - atraccionPreferidaMasCercana.obtenerDuracionPromedioDeVisitaEnMins() - this.calcularTiempoRequeridoParaAlcanzarAtraccion(this.perfilDeUsuario.obtenerVelocidadDeTraslado(),atraccionPreferidaMasCercana,posicionActualDelUsuario);
                    posicionActualDelUsuario = atraccionPreferidaMasCercana.obtenerPosicion();
                }
                atraccionesQueLeFaltarianRecorrer.remove(atraccionPreferidaMasCercana);
                atraccionesQueLeFaltanRecorrerYSonDeSuPreferencia.remove(atraccionPreferidaMasCercana);
            }
            else{
                //Pido la de Menor Precio.
                Set<Atraccion> atraccionesQueLeFaltanRecorrerYSonDeSuPreferenciaYTienenIgualDistanciaDeMenoCosto = BuscadorDeAtraccionesPorCosto.buscarAtraccionesPorCosto(atraccionesQueLeFaltanRecorrerYSonDeSuPreferenciaMasCercanas);

                //Por lo explicado en los supuestos, no va a haber mas de una Atraccion llegado a este caso.
                Atraccion atraccionAEvaluar = atraccionesQueLeFaltanRecorrerYSonDeSuPreferenciaYTienenIgualDistanciaDeMenoCosto.iterator().next();

                if (this.seDanLasCondiciones(atraccionAEvaluar,dineroDelUsuarioRestante,tiempoParaVisitasRestante,posicionActualDelUsuario)){
                    itinerarioPriorizandoGustosDelUsuario.agregarAlitinerario(atraccionAEvaluar);
                    dineroDelUsuarioRestante = dineroDelUsuarioRestante - atraccionAEvaluar.obtenerCosto();
                    tiempoParaVisitasRestante = tiempoParaVisitasRestante - atraccionAEvaluar.obtenerDuracionPromedioDeVisitaEnMins() - this.calcularTiempoRequeridoParaAlcanzarAtraccion(this.perfilDeUsuario.obtenerVelocidadDeTraslado(),atraccionAEvaluar,posicionActualDelUsuario);
                    posicionActualDelUsuario = atraccionAEvaluar.obtenerPosicion();
                }
                atraccionesQueLeFaltarianRecorrer.remove(atraccionAEvaluar);
                atraccionesQueLeFaltanRecorrerYSonDeSuPreferencia.remove(atraccionAEvaluar);
            }

            atraccionesQueLeFaltanRecorrerYSonDeSuPreferenciaMasCercanas = BuscadorDeAtraccionesCercanas.buscarAtraccionMasCercana(posicionActualDelUsuario, atraccionesQueLeFaltanRecorrerYSonDeSuPreferencia);
        }

        Iterator<Atraccion> iteradorDeAtraccionesQueLeFaltarianRecorrer = atraccionesQueLeFaltarianRecorrer.iterator();
        while (iteradorDeAtraccionesQueLeFaltarianRecorrer.hasNext()){
            Atraccion atraccionAEvaluar = iteradorDeAtraccionesQueLeFaltarianRecorrer.next();

            if(this.seDanLasCondiciones(atraccionAEvaluar,dineroDelUsuarioRestante,tiempoParaVisitasRestante,posicionActualDelUsuario)){
                itinerarioPriorizandoGustosDelUsuario.agregarAlitinerario((atraccionAEvaluar));
                dineroDelUsuarioRestante = dineroDelUsuarioRestante - atraccionAEvaluar.obtenerCosto();
                tiempoParaVisitasRestante = tiempoParaVisitasRestante - atraccionAEvaluar.obtenerDuracionPromedioDeVisitaEnMins() - this.calcularTiempoRequeridoParaAlcanzarAtraccion(this.perfilDeUsuario.obtenerVelocidadDeTraslado(),atraccionAEvaluar,posicionActualDelUsuario);
                posicionActualDelUsuario = atraccionAEvaluar.obtenerPosicion();
            }

        }

        Set<Atraccion> atraccionesQueVisitaraElTurista = new HashSet(itinerarioPriorizandoGustosDelUsuario.obtenerItinerario());
        while(iteradorDePromociones.hasNext()){
            Promocion promocion = iteradorDePromociones.next();

            if(promocion.estaVigente(this.fechaDeLaVisita)){
                reduccionGanadaEnPromociones = reduccionGanadaEnPromociones + promocion.calcularReduccionDeCostoTotal(atraccionesQueVisitaraElTurista);
            }

        }
        if (itinerarioPriorizandoGustosDelUsuario.obtenerItinerario().size() >0) {
            this.sugerencias.add(new Sugerencia(itinerarioPriorizandoGustosDelUsuario,reduccionGanadaEnPromociones));
        }

    }

    private void generarItinerarioPriorizandoRecorrerLaMaxCantidadDeAtracciones(){
        Itinerario itinerarioPriorizandoRecorrerLaMaxCantidadDeAtracciones = new Itinerario();
        Set<Atraccion> atraccionesQueLeFaltarianRecorrer = new HashSet<>(this.atraccionesDelParque) ;
        double dineroDelUsuarioRestante = this.perfilDeUsuario.obtenerPresupuestoDisponible();
        double tiempoParaVisitasRestante = this.perfilDeUsuario.obtenerTiempoDisponibleParaVisitas();
        Posicion posicionActualDelUsuario = this.perfilDeUsuario.obtenerPosicionActual();
        double reduccionGanadaEnPromociones = 0;
        Iterator<Promocion> iteradorDePromociones = this.promociones.iterator();

        //Busco la mas cercana
        Set<Atraccion> atraccionesMasCercanas = BuscadorDeAtraccionesCercanas.buscarAtraccionMasCercana(posicionActualDelUsuario, atraccionesQueLeFaltarianRecorrer);

        while (atraccionesQueLeFaltarianRecorrer.size() > 0) {
            if (atraccionesMasCercanas.size() == 1) {
                Atraccion atraccionMasCercana = atraccionesMasCercanas.iterator().next();

                if (seDanLasCondiciones(atraccionMasCercana,dineroDelUsuarioRestante,tiempoParaVisitasRestante,posicionActualDelUsuario)){
                    itinerarioPriorizandoRecorrerLaMaxCantidadDeAtracciones.agregarAlitinerario(atraccionMasCercana);
                    dineroDelUsuarioRestante = dineroDelUsuarioRestante - atraccionMasCercana.obtenerCosto();
                    tiempoParaVisitasRestante = tiempoParaVisitasRestante - atraccionMasCercana.obtenerDuracionPromedioDeVisitaEnMins() - this.calcularTiempoRequeridoParaAlcanzarAtraccion(this.perfilDeUsuario.obtenerVelocidadDeTraslado(), atraccionMasCercana, posicionActualDelUsuario);

                    //muevo al usuario a la nueva posicion en la simulacion
                    posicionActualDelUsuario = atraccionMasCercana.obtenerPosicion();

                }
                atraccionesQueLeFaltarianRecorrer.remove(atraccionMasCercana);


            }
            //Debo Analizar a cúal ir, ya que hay varias a la misma distancia
            else {
                Iterator<Atraccion> iterador = atraccionesMasCercanas.iterator();
                boolean encontreUnaDeSuPreferencia = false;
                //Si hay varias a la misma distancia, debo ir a por preferencia y luego precio
                Atraccion atraccionMasCercana = atraccionesMasCercanas.iterator().next();;

                while (iterador.hasNext() && !encontreUnaDeSuPreferencia){
                    Atraccion atraccionAEvaluar = iterador.next();
                    if (atraccionAEvaluar.obtenerTipoDeAtraccion().equals(perfilDeUsuario.obtenerTipoDeAtraccionFavorita())){
                        atraccionMasCercana = atraccionAEvaluar;
                        encontreUnaDeSuPreferencia = true;
                    }
                }

                if (seDanLasCondiciones(atraccionMasCercana,dineroDelUsuarioRestante,tiempoParaVisitasRestante,posicionActualDelUsuario)){
                    itinerarioPriorizandoRecorrerLaMaxCantidadDeAtracciones.agregarAlitinerario(atraccionMasCercana);
                    dineroDelUsuarioRestante = dineroDelUsuarioRestante - atraccionMasCercana.obtenerCosto();
                    tiempoParaVisitasRestante = tiempoParaVisitasRestante - atraccionMasCercana.obtenerDuracionPromedioDeVisitaEnMins() -this.calcularTiempoRequeridoParaAlcanzarAtraccion(this.perfilDeUsuario.obtenerVelocidadDeTraslado(), atraccionMasCercana, posicionActualDelUsuario);
                    //muevo al usuario a la nueva posicion en la simulacion
                    posicionActualDelUsuario = atraccionMasCercana.obtenerPosicion();
                }
                atraccionesQueLeFaltarianRecorrer.remove(atraccionMasCercana);

            }

            atraccionesMasCercanas = BuscadorDeAtraccionesCercanas.buscarAtraccionMasCercana(posicionActualDelUsuario, atraccionesQueLeFaltarianRecorrer);
        }


        Set<Atraccion> atraccionesQueVisitaraElTurista = new HashSet(itinerarioPriorizandoRecorrerLaMaxCantidadDeAtracciones.obtenerItinerario());
        while(iteradorDePromociones.hasNext()){
            Promocion promocion = iteradorDePromociones.next();

            if(promocion.estaVigente(this.fechaDeLaVisita)){
                reduccionGanadaEnPromociones = reduccionGanadaEnPromociones + promocion.calcularReduccionDeCostoTotal(atraccionesQueVisitaraElTurista);
            }

        }
        if (itinerarioPriorizandoRecorrerLaMaxCantidadDeAtracciones.obtenerItinerario().size() > 0 ) {
            this.sugerencias.add(new Sugerencia(itinerarioPriorizandoRecorrerLaMaxCantidadDeAtracciones,reduccionGanadaEnPromociones));
        }
    }

    private boolean leAlcanzaElDinero(double dineroDisponible, Atraccion atraccionAVisitar){

        boolean dineroSuficienteParavisitarAtraccion = false;

        if (dineroDisponible >= atraccionAVisitar.obtenerCosto()){
            dineroSuficienteParavisitarAtraccion =  true;
        }

        return dineroSuficienteParavisitarAtraccion;
    }

    private boolean leAlcanzaElTiempo(double tiempoDisponible, double velocidadDeTraslado, Atraccion atraccionAVisitar, Posicion posicionActual){
        boolean tiempoSuficienteParaVisitarAtraccion = false;
        double tiempoRequeridoParaAlcanzarLaAtraccion = this.calcularTiempoRequeridoParaAlcanzarAtraccion(velocidadDeTraslado,atraccionAVisitar,posicionActual);

        if (tiempoDisponible >= (tiempoRequeridoParaAlcanzarLaAtraccion + atraccionAVisitar.obtenerDuracionPromedioDeVisitaEnMins())){
            tiempoSuficienteParaVisitarAtraccion = true;
        }

        return tiempoSuficienteParaVisitarAtraccion;

    }

    private boolean laAtraccionTieneLugarDisponible(Atraccion atraccionAVisitar){
        boolean lugarDisponible = false;
        if (atraccionAVisitar.obtenerNumeroDeVisitantesActuales() < atraccionAVisitar.obtenerCupoDeVisitantesDiarios()){
            lugarDisponible = true;
        }

        return lugarDisponible;
    }

    private double calcularTiempoRequeridoParaAlcanzarAtraccion(double velocidadDeTraslado, Atraccion atraccionAVisitar, Posicion posicionActual){
        double tiempoRequeridoParaAlcanzarLaAtraccion = velocidadDeTraslado/(CalculadorDeDistancia.calcularDistanciaEntreDosPosiciones(posicionActual,atraccionAVisitar.obtenerPosicion()));

        return tiempoRequeridoParaAlcanzarLaAtraccion;
    }

    private boolean seDanLasCondiciones(Atraccion atraccion, double dineroDelUsuarioRestante,double tiempoParaVisitasRestante,Posicion posicionActualDelUsuario){
        boolean condiciones = false;

        if (this.laAtraccionTieneLugarDisponible(atraccion) && this.leAlcanzaElDinero(dineroDelUsuarioRestante, atraccion) && this.leAlcanzaElTiempo(tiempoParaVisitasRestante, this.perfilDeUsuario.obtenerVelocidadDeTraslado(), atraccion, posicionActualDelUsuario)) {
            condiciones = true;
        }

        return condiciones;
    }

}