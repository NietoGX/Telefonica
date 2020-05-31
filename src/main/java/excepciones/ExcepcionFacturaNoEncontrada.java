package excepciones;

public class ExcepcionFacturaNoEncontrada extends Exception {
    public ExcepcionFacturaNoEncontrada() {
        super("Factura no encontrada");
    }
}
