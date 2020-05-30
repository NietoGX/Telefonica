package tarifa;

import datos.Llamada;

import java.util.Calendar;

public class TarifaTarde extends DecoradorTarifa {

    private static final double PRECIO = 0.05;

    public TarifaTarde(Tarifa tarifa) {
        super(tarifa);
    }

    @Override
    public String toString() {
        return "Tarifa Tarde: " + PRECIO + "€";
    }

    @Override
    public double getPrecioTarifa() {
        return PRECIO;
    }

    @Override
    public double calcularCoste(Llamada llamada) {
        int hora = llamada.getFecha().get(Calendar.HOUR_OF_DAY);

        if (hora >= 16 && hora <= 20)
            return llamada.getDuracion() * PRECIO;

        return llamada.getDuracion() * 0.15;
    }
}
