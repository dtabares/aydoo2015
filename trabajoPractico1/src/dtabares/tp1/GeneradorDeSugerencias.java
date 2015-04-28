package dtabares.tp1;


import java.util.*;

public class GeneradorDeSugerencias {

    private PerfilDeUsuario perfilDeUsuario;
    private Set<Promocion> promociones;
    private List<Sugerencia> sugerencias;
    private Set<Atraccion> atraccionesDelParque;
    private Set<Atraccion> atraccionesDelParqueAgregadasAlItinerarioQuePriorizaGustosDelUsuario;
    private Set<Atraccion> atraccionesDelParqueAgregadasAlItinerarioQueCantidadDeAtraccionesVisitadas;
    private Set<Atraccion> atraccionesDelParqueAgregadasAlItinerarioQuePriorizaGastarElMenorMontoPosible;
    private Calendar fechaDeLaVisita;

    public GeneradorDeSugerencias(PerfilDeUsuario perfilDeUsuario,Set<Promocion> promociones,Set<Atraccion> atraccionesDelParque,Calendar fechaDeLaVisita){
        this.perfilDeUsuario = perfilDeUsuario;
        this.promociones = promociones;
        this.sugerencias = new ArrayList<>();
        this.atraccionesDelParque = atraccionesDelParque;
        this.fechaDeLaVisita = fechaDeLaVisita;
    }

    public List<Sugerencia> generarSugerencia(){

        this.generarItinerarioPriorizandoGustosDelUsuario();
        this.generarItinerarioPriorizandoRecorrerLaMaxCantidadDeAtracciones();
       // this.generarItinerarioPriorizandoElMenorCostoPosible();

        return sugerencias;

    }

    private void generarItinerarioPriorizandoGustosDelUsuario(){
        Itinerario itinerarioPriorizandoGustosDelUsuario = new Itinerario();
        Set<Atraccion> atraccionesQueLeFaltarianRecorrer = new HashSet(this.atraccionesDelParque);
        //System.out.println("AtraccionesPorRecorrer en Gusto: " + atraccionesQueLeFaltarianRecorrer.size());
        Set<Atraccion> atraccionesQueLeFaltanRecorrerYSonDeSuPreferencia = BuscadorDeAtraccionesPorPreferencia.buscarAtraccionesPorPreferencia(this.atraccionesDelParque, this.perfilDeUsuario.obtenerTipoDeAtraccionFavorita());
        double dineroDelUsuarioRestante = this.perfilDeUsuario.obtenerPresupuestoDisponible();
        double tiempoParaVisitasRestante = this.perfilDeUsuario.obtenerTiempoDisponibleParaVisitas();
        Posicion posicionActualDelUsuario = this.perfilDeUsuario.obtenerPosicionActual();
        double reduccionGanadaEnPromociones = 0;
        Iterator<Atraccion> iteradorDeAtraccionesPreferidas = atraccionesQueLeFaltanRecorrerYSonDeSuPreferencia.iterator();
        Iterator<Promocion> iteradorDePromociones = this.promociones.iterator();

        while (iteradorDeAtraccionesPreferidas.hasNext()){
            Atraccion atraccionPreferidaAEvaluar = iteradorDeAtraccionesPreferidas.next();
            if (this.seDanLasCondiciones(atraccionPreferidaAEvaluar,dineroDelUsuarioRestante,tiempoParaVisitasRestante,posicionActualDelUsuario)){
                itinerarioPriorizandoGustosDelUsuario.agregarAlitinerario(atraccionPreferidaAEvaluar);
                dineroDelUsuarioRestante = dineroDelUsuarioRestante - atraccionPreferidaAEvaluar.obtenerCosto();
                tiempoParaVisitasRestante = tiempoParaVisitasRestante - atraccionPreferidaAEvaluar.obtenerDuracionPromedioDeVisitaEnMins() - this.calcularTiempoRequeridoParaAlcanzarAtraccion(this.perfilDeUsuario.obtenerVelocidadDeTraslado(),atraccionPreferidaAEvaluar,posicionActualDelUsuario);
                posicionActualDelUsuario = atraccionPreferidaAEvaluar.obtenerPosicion();
            }
            atraccionesQueLeFaltarianRecorrer.remove(atraccionPreferidaAEvaluar);
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
        //System.out.println("AtraccionesPorRecorrer: " + atraccionesQueLeFaltarianRecorrer.size());
        double dineroDelUsuarioRestante = this.perfilDeUsuario.obtenerPresupuestoDisponible();
        //System.out.println("guitaEnMax: " + dineroDelUsuarioRestante);
        double tiempoParaVisitasRestante = this.perfilDeUsuario.obtenerTiempoDisponibleParaVisitas();
        //System.out.println("tiempoEnMAx: " + tiempoParaVisitasRestante);
        Posicion posicionActualDelUsuario = this.perfilDeUsuario.obtenerPosicionActual();
        //System.out.println("Latitud en Max : " + posicionActualDelUsuario.obtenerLatitud());
        //System.out.println("Longitud en Max: " + posicionActualDelUsuario.obtenerLongitud());
        double reduccionGanadaEnPromociones = 0;
        Iterator<Promocion> iteradorDePromociones = this.promociones.iterator();

        //Busco la mas cercana
        Set<Atraccion> atraccionesMasCercanas = BuscadorDeAtraccionesCercanas.buscarAtraccionMasCercana(posicionActualDelUsuario, atraccionesQueLeFaltarianRecorrer);

        while (atraccionesQueLeFaltarianRecorrer.size() > 0) {
            if (atraccionesMasCercanas.size() == 1) {
                Atraccion atraccionMasCercana = atraccionesMasCercanas.iterator().next();
                System.out.println("AtraccionMasCercana: " + atraccionMasCercana.obtenerNombre());

                if (seDanLasCondiciones(atraccionMasCercana,dineroDelUsuarioRestante,tiempoParaVisitasRestante,posicionActualDelUsuario)){
                //if (this.laAtraccionTieneLugarDisponible(atraccionMasCercana) && this.leAlcanzaElDinero(dineroDelUsuarioRestante, atraccionMasCercana) && this.leAlcanzaElTiempo(tiempoParaVisitasRestante, this.perfilDeUsuario.obtenerVelocidadDeTraslado(), atraccionMasCercana, posicionActualDelUsuario)) {
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
                //Si no encuentro ninguna de su preferencia tomo la primera que el iterador me de
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

    private void generarItinerarioPriorizandoElMenorCostoPosible(){

        Itinerario itinerarioPriorizandoElMenorCostoPosible = new Itinerario();
        Set<Atraccion> atraccionesQueLeFaltarianRecorrer = this.atraccionesDelParque;
        double dineroDelUsuarioRestante = this.perfilDeUsuario.obtenerPresupuestoDisponible();
        double tiempoParaVisitasRestante = this.perfilDeUsuario.obtenerTiempoDisponibleParaVisitas();

        //this.sugerencias.add();

    }

    private boolean leAlcanzaElDinero(double dineroDisponible, Atraccion atraccionAVisitar){

        boolean dineroSuficienteParavisitarAtraccion = false;

        if (dineroDisponible >= atraccionAVisitar.obtenerCosto()){
            dineroSuficienteParavisitarAtraccion =  true;
        }

        //System.out.println("booleanDinero: " + dineroSuficienteParavisitarAtraccion);
        return dineroSuficienteParavisitarAtraccion;
    }

    private boolean leAlcanzaElTiempo(double tiempoDisponible, double velocidadDeTraslado, Atraccion atraccionAVisitar, Posicion posicionActual){
        boolean tiempoSuficienteParaVisitarAtraccion = false;
        double tiempoRequeridoParaAlcanzarLaAtraccion = this.calcularTiempoRequeridoParaAlcanzarAtraccion(velocidadDeTraslado,atraccionAVisitar,posicionActual);

        if (tiempoDisponible >= (tiempoRequeridoParaAlcanzarLaAtraccion + atraccionAVisitar.obtenerDuracionPromedioDeVisitaEnMins())){
            tiempoSuficienteParaVisitarAtraccion = true;
        }
        //System.out.println("booleanTiempo: " + tiempoSuficienteParaVisitarAtraccion);
        return tiempoSuficienteParaVisitarAtraccion;

    }

    private boolean laAtraccionTieneLugarDisponible(Atraccion atraccionAVisitar){
        boolean lugarDisponible = false;
        if (atraccionAVisitar.obtenerNumeroDeVisitantesActuales() < atraccionAVisitar.obtenerCupoDeVisitantesDiarios()){
            lugarDisponible = true;
        }

        //System.out.println("boolean lugarDisponible: " + lugarDisponible);
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

        //System.out.println("boolean condiciones: " + condiciones);
        return condiciones;
    }

}
