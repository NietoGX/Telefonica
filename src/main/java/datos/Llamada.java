package datos;
import fecha.TieneFecha;

import java.io.Serializable;
import java.util.Calendar;

public class Llamada implements TieneFecha, Serializable {
	private String telefono;
	private Calendar fecha;
	private int duracion;
	private boolean facturada;

	public Llamada(String telefono, Calendar fecha, int duracion, boolean facturada) {
		this.telefono = telefono;
		this.fecha = fecha;
		this.duracion = duracion;
		this.facturada = facturada;
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

	public boolean getFacturada() {
		return facturada;
	}

	public void setFacturada(boolean facturada) {
		this.facturada = facturada;
	}

	public String toString() {
		return "Llamada:\n" +
				"- Telefono: " + telefono + "\n" +
				"- Fecha: "+ fecha.get(Calendar.DAY_OF_MONTH) + "/" + (fecha.get(Calendar.MONTH)+1) + "/" + fecha.get(Calendar.YEAR)
					       + " " +  fecha.get(Calendar.HOUR_OF_DAY) + ":" + fecha.get(Calendar.MINUTE) + "\n" +
				"- Duracion: " + duracion + "segundos"+"\n";
	}
}
