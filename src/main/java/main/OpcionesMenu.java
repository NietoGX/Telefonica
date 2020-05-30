package main;

public enum OpcionesMenu {
	
	ALTA_CLIENTE("Dar de alta un cliente"),
	BORRAR_CLIENTE("Borrar un cliente"),
	CAMBIAR_TARIFA("Cambiar la tarifa de un cliente"),
	DATOS_CLIENTE("Mostrar datos de un cliente"),
	MOSTRAR_LISTA_CLIENTES("Mostrar la lista de todos los clientes"),
	DAR_DE_ALTA_LLAMADA("Dar de alta una llamada"),
	LISTAR_LLAMADAS("Listar todas las llamadas de un cliente"),
	EMITIR_FACTURAS("Emitir facturas"),
	MOSTRAR_FACTURA("Mostrar factura a partir del codigo de la factura"),
	MOSTRAR_FACTURAS("Mostrar facturas de un cliente"),
	MOSTRAR_CLIENTES_ENTRE_FECHAS("Mostrar los clientes dados de alta entre 2 fechas"),
	MOSTRAR_FACTURAS_ENTRE_FECHAS("Mostrar las facturas entre 2 fechas"),
	MOSTRAR_LLAMADAS_ENTRE_FECHAS("Mostrar las llamadas entre 2 fechas"),
	SALIR_MENU("Salir");

	private String descripcion;

	private OpcionesMenu(String descripcion) {
		this.descripcion=descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static OpcionesMenu getOpcion(int posicion) {
		return values()[posicion];
	}

	public static String getMenu() {
		StringBuilder sb=new StringBuilder();
		for(OpcionesMenu opcion: OpcionesMenu.values()) {
			sb.append(opcion.ordinal());
			sb.append(".--");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
