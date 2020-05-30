package tarifa;

import datos.Llamada;

import java.util.Calendar;

public class TarifaDomingo extends DecoradorTarifa {

    private static final double PRECIO = 0.0;

    public TarifaDomingo(Tarifa tarifa) {
        super(tarifa);
    }

    @Override
    public String toString() {
        return "Tarifa Domingo: " + PRECIO + "€";
    }

    @Override
    public double getPrecioTarifa() {
        return PRECIO;
    }

    @Override
    public double calcularCoste(Llamada llamada) {
        if (llamada.getFecha().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            return llamada.getDuracion() * PRECIO;

        return llamada.getDuracion() * 0.15;
    }

}
