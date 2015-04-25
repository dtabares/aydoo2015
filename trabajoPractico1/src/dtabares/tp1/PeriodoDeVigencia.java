package dtabares.tp1;


import java.util.Calendar;

public class PeriodoDeVigencia {

    private Calendar fechaInicio;
    private Calendar fechaFin;
    private boolean vigente;

    public PeriodoDeVigencia(Calendar fechaInicio,Calendar fechaFin){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public boolean estaVigente(Calendar fecha){
        if ( (fecha.after(fechaInicio) || fecha.equals(fechaInicio)) && (fecha.before(fechaFin) || fecha.equals(fechaFin))){
            this.vigente = true;
        }
        else{
            this.vigente = false;
        }
        return this.vigente;
    }
}
