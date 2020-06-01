package datos;
import fecha.TieneFecha;
import tarifa.Tarifa;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

public class Factura implements TieneFecha, Serializable {
	private int codFactura;
	private Tarifa tarifa;
	private Calendar fechaEmision;
	private double importe;
	
	public Factura(int codFactura, Tarifa tarifa, Calendar fecha, double importe) {
		this.codFactura = codFactura;
		this.tarifa = tarifa;
		this.fechaEmision = fecha;
		this.importe = importe;
	}

	public int getCodFactura() {
		return codFactura;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public Calendar getFecha() {
		return fechaEmision;
	}

	public double getImporte() {
		return importe;
	}

	@Override
	public String toString() {
		return "Factura Cliente: \n" +
				"- Codigo de Factura: " + codFactura + "\n" +
				"- Tarifa: " + tarifa.toString() + "\n" +
				"- Emision: " + fechaEmision.get(Calendar.DAY_OF_MONTH) + "/" + (fechaEmision.get(Calendar.MONTH)+1) + "/" + fechaEmision.get(Calendar.YEAR) + "\n" +
				"- Importe: " + importe + "\n";
	}
}
