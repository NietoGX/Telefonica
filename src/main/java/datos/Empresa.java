package datos;

import tarifa.Tarifa;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

public class Empresa extends Cliente implements Serializable {

	public Empresa(String nombre, String nif, Direccion direccion, String correo, Calendar fechaAlta, Tarifa tarifa) {
		super(nombre, nif, direccion, correo, fechaAlta, tarifa);
	}

}