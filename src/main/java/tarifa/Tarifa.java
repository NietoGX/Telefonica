package tarifa;

import datos.Llamada;

import java.io.Serializable;

public abstract class Tarifa implements Serializable {
	public abstract double getPrecioCorrecto(Llamada llamada, Tarifa tarifa);

	public abstract double coste(Llamada llamada);

	public abstract String toString();
}
