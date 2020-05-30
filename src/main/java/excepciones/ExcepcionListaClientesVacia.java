package excepciones;

public class ExcepcionListaClientesVacia extends Throwable {
    public ExcepcionListaClientesVacia() {
        super("Lista clientes vacia");
    }
}
