package excepciones;

public class ExcepcionClienteNoEncontrado extends Exception {
    public ExcepcionClienteNoEncontrado() {
        super("Cliente no encontrado");
    }
}
