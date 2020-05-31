package tarifa;

import datos.Llamada;

import java.util.Calendar;

public class TarifaTarde extends DecoradorTarifa {

    private static final double PRECIO = 0.05;

    public TarifaTarde(Tarifa tarifaDecorada) {
        super(tarifaDecorada);
    }

    @Override
    public double coste(Llamada llamada) {
        int hora = llamada.getFecha().get(Calendar.HOUR_OF_DAY);

        if (hora >= 16 && hora <= 20)
            return llamada.getDuracion() * PRECIO;
        else
            return llamada.getDuracion() * 0.15;
    }

    @Override
    public String toString() {
        return "Tarifa Tarde: " + PRECIO + "€";
    }
}
