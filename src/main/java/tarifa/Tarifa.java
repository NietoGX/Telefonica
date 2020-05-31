package tarifa;

import datos.Llamada;

public abstract class Tarifa {
	private double precio;

	public Tarifa (double precio) {
		this.precio = precio;
	}

	protected Tarifa() {
	}

	public abstract double getPrecioCorrecto(Llamada llamada, Tarifa tarifa);

	public abstract double coste(Llamada llamada);

	public abstract String toString();
}
