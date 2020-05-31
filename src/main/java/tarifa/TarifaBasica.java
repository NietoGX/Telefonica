package tarifa;

import datos.Llamada;

import java.util.Calendar;

public class TarifaBasica implements Tarifa {

    private static final double PRECIO = 0.15;

    @Override
    public double getPrecioTarifa() {
        return PRECIO;
    }

    @Override
    public Tarifa getTarifa() {
        return null;
    }

    @Override
    public double costeMenor(Llamada llamada, Tarifa tarifaActual) {
        return tarifaActual.calcularCoste(llamada);
    }

    @Override
    public double calcularCoste(Llamada llamada) {
        return llamada.getDuracion() * PRECIO;
    }
}
