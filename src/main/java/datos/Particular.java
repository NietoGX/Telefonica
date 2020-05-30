package datos;
import tarifa.Tarifa;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

public class Particular extends Cliente implements Serializable {
	private String apellido;

	public Particular(String nombre, String apellido, String nif, Direccion direccion, String correo, Calendar fechaAlta, Tarifa tarifa) {
		super(nombre, nif, direccion, correo, fechaAlta, tarifa);
		this.apellido = apellido;
	}

	public String getApellido() {
		return apellido;
	}

}