package tarifa;

import datos.Llamada;

public interface Tarifa {
	double getPrecioTarifa();
	Tarifa getTarifa();
	double costeMenor(Llamada llamada, Tarifa tarifaActual);
	double calcularCoste(Llamada llamada);
}
