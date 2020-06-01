package tarifa;

import datos.Llamada;

public abstract class DecoradorTarifa extends Tarifa{
    private Tarifa tarifaDecorada;

    public DecoradorTarifa(Tarifa tarifaDecorada) {
        this.tarifaDecorada = tarifaDecorada;
    }

    @Override
    public double getPrecioCorrecto(Llamada llamada, Tarifa tarifa) {
        if (coste(llamada) < tarifa.coste(llamada))
            return tarifaDecorada.getPrecioCorrecto(llamada, this);
        else
            return tarifaDecorada.getPrecioCorrecto(llamada, tarifa);
    }

    public String toString() {
        return tarifaDecorada.toString();
    }
}
