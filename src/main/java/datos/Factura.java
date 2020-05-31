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
	private long periodo;
	private double importe;
	
	public Factura(int codFactura, Tarifa tarifa, Calendar fecha, long periodo, double importe) {
		this.codFactura = codFactura;
		this.tarifa = tarifa;
		this.fechaEmision = fecha;
		this.periodo = periodo;
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
	
	public long getPeriodo() {
		return periodo;
	}

	public double getImporte() {
		return importe;
	}

	// TODO: Comprobar como se ve
	@Override
	public String toString() {
		return "Factura Cliente: \n" +
				"- Codigo de Factura: " + codFactura + "\n" +
				"- Tarifa: " + tarifa.toString() + "\n" +
				"- Emision: " + fechaEmision.getTime().toString() + "\n" +
				"- Importe: " + importe + "\n";
	}
}
