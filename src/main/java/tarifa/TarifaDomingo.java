package tarifa;

import datos.Llamada;

import java.util.Calendar;

public class TarifaDomingo extends DecoradorTarifa {

    private static final double PRECIO = 0.0;

    public TarifaDomingo(Tarifa tarifaDecorada) {
        super(tarifaDecorada);
    }

    @Override
    public double coste(Llamada llamada) {
        if (llamada.getFecha().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            return llamada.getDuracion() * PRECIO;
        else
            return llamada.getDuracion() * 0.15;
    }

    public String toString() {
        return super.toString() + "Tarifa Domingo: " + PRECIO + "€ ";
    }
}
