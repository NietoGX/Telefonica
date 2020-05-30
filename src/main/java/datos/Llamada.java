package datos;
import fecha.TieneFecha;

import java.io.Serializable;
import java.util.Calendar;

public class Llamada implements TieneFecha, Serializable {
	private String telefono;
	private Calendar fecha;
	private int duracion;

	public Llamada(String telefono, Calendar fecha, int duracion) {
		this.telefono = telefono;
		this.fecha = fecha;
		this.duracion = duracion;
	}

	public String getTelefono() {
		return telefono;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public int getDuracion() {
		return duracion;
	}

	public String toString() {
		return "Llamada:\n" +
				"- Telefono: " + telefono + "\n" +
				"- Fecha: "+ fecha.toString() + "\n" +
				"- Duracion: " + duracion + "\n";
	}
}
