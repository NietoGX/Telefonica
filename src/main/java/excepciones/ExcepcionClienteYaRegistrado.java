package excepciones;

public class ExcepcionClienteYaRegistrado extends Exception {
    public ExcepcionClienteYaRegistrado() {
        super("Cliente ya registrado");
    }
}
