package dtabares;

public class PrepararPescadoAlHorno extends PrepararPescado{

    @Override
    public String cocinarlo() {
        //System.out.println("Condimento, agrego verduras, envuelvo en papel aluminio y lo intorduzco en el horno");
        return "Condimento, agrego verduras, envuelvo en papel aluminio y lo intorduzco en el horno";
    }

    @Override
    public String presentarlo() {
        //System.out.println("Lo Presento en un plato cuadrado");
        return "Lo Presento en un plato cuadrado";
    }
}
