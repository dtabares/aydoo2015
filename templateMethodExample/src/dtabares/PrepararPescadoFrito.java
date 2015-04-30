package dtabares;


public class PrepararPescadoFrito extends PrepararPescado {
    @Override
    public String cocinarlo() {
        //System.out.println("Condimento, empano, y lo introduzco en la freidora");
        return "Condimento, empano, y lo introduzco en la freidora";
    }

    @Override
    public String presentarlo() {
        //System.out.println("Lo Presento en un plato redondo");
        return "Lo Presento en un plato redondo";
    }
}
