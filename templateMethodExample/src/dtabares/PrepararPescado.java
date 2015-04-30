package dtabares;


import java.util.LinkedList;
import java.util.List;

public abstract class PrepararPescado {

    private List<String> listaDePasosRealizados;

    public PrepararPescado(){
        this.listaDePasosRealizados = new LinkedList<>();
    }

    //Template Method
    public final List prepararElPescado(){

        this.listaDePasosRealizados.add(this.limpiarlo());
        this.listaDePasosRealizados.add(this.filetearlo());
        this.listaDePasosRealizados.add(this.cocinarlo());
        this.listaDePasosRealizados.add(this.presentarlo());

        return this.listaDePasosRealizados;
    }


    public abstract String cocinarlo();
    public abstract String presentarlo();
    public String limpiarlo(){
        //System.out.println("Limpio el Pescado");
        return "Limpio el Pescado";
    }
    public String filetearlo(){
        //System.out.println("Fileteo el Pescado");
        return "Fileteo el Pescado";
    }
}