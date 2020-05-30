package datos;

import java.io.Serializable;

public class Direccion implements Serializable {
	private String provincia;
	private String poblacion;
	private String codPostal;

	public Direccion(String provincia, String poblacion, String codPostal) {
		this.provincia = provincia;
		this.poblacion = poblacion;
		this.codPostal = codPostal;
	}

	public String toString(){
		return "\t - Provincia: " + provincia + "\n" +
				"\t - Poblacion: "+ poblacion + "\n" +
				"\t - Codigo Postal: " + codPostal + "\n";
	}

}
