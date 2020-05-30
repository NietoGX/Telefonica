package tarifa;

import datos.Llamada;

public abstract class DecoradorTarifa implements Tarifa{
    private Tarifa tarifa;

    @Override
    public Tarifa getTarifa() {
        return tarifa;
    }

    public DecoradorTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public double costeMenor(Llamada llamada, Tarifa tarifaActual) {
        if (calcularCoste(llamada) < tarifaActual.calcularCoste(llamada))
            return tarifa.costeMenor(llamada, this);

        return tarifa.costeMenor(llamada, tarifaActual);
    }
}
