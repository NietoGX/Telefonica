package tarifa;

import datos.Llamada;

import java.util.Calendar;

public class TarifaBasica extends Tarifa {

    private static final double PRECIO = 0.15;

    @Override
    public double getPrecioCorrecto(Llamada llamada, Tarifa tarifa) {
        return tarifa.coste(llamada);
    }

    @Override
    public double coste(Llamada llamada) {
        return llamada.getDuracion() * PRECIO;
    }

    @Override
    public String toString() {
        return "Tarifa Basica: " + PRECIO + "€";
    }
}
