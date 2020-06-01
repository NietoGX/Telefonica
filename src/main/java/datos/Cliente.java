package datos;
import fecha.TieneFecha;
import tarifa.Tarifa;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

public abstract class Cliente extends FactoriaCliente implements TieneFecha, Serializable  {
	private String nombre;
	private String nif;
	private String correo;
	private Direccion direccion;
	private Calendar fechaAlta;
	private Tarifa tarifa;
	private Set<Factura> facturas;

	public Cliente(String nombre, String nif, Direccion direccion, String correo, Calendar fechaAlta, Tarifa tarifa) {
		this.nombre = nombre;
		this.nif = nif;
		this.correo = correo;
		this.direccion = direccion;
		this.fechaAlta = fechaAlta;
		this.tarifa = tarifa;
		this.facturas = null;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getNif() {
		return nif;
	}

	public String getEmail() {
		return correo;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void addFactura(Factura fac) {
		facturas.add(fac);
	}

	public String toString() {
		return "Cliente: \n" +
				"- Nombre: " + nombre + "\n" +
				"- NIF: " + nif + "\n" +
				"- Direccion: \n" + direccion.toString() +
				"- Correo: " + correo + "\n" +
				"- Fecha de Alta: " + fechaAlta.get(Calendar.DAY_OF_MONTH) + "/" + (fechaAlta.get(Calendar.MONTH)+1) + "/" + fechaAlta.get(Calendar.YEAR) + "\n" +
				"- " + tarifa.toString() + "\n";
	}

	public Calendar getFecha(){
		return fechaAlta;
	};

}
