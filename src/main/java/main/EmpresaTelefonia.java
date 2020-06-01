package main;

import Swing.Modelo.ImplementacionModelo;
import datos.*;
import excepciones.*;
import tarifa.FactoriaTarifa;
import tarifa.Tarifa;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/****** Alex George Moraru Y David Nieto González ******/

public class EmpresaTelefonia implements Serializable {

	private static ImplementacionModelo empresa;
	private static Scanner teclado;
	private static final long serialVersionUID = 965898124655689844L;
	private static final long TAMFECHA = 16;

	// TODO: No puede tener throws, hay que tratar las excepciones
	public static void main(String[] args) {
		teclado = new Scanner(System.in);

		empresa = new ImplementacionModelo();
		empresa.cargarDatos();

		while (menu() != 13);

		teclado.close();
	}

	private static int menu() {
		System.out.println(OpcionesMenu.getMenu());
		System.out.print("Elige una opcion:");
		int opcion = teclado.nextInt();
		teclado.nextLine();

		OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);

		switch(opcionMenu) {

			case ALTA_CLIENTE:
				altaCliente();
				break;

			case BORRAR_CLIENTE:
				borrarCliente();
				break;

			case CAMBIAR_TARIFA:
				cambiarTarifa();
				break;

			case DATOS_CLIENTE:
				mostrarDatosCliente();
				break;

			case MOSTRAR_LISTA_CLIENTES:
				mostrarListaClientes();
				break;

			case DAR_DE_ALTA_LLAMADA:
				altaLlamada();
				break;

			case LISTAR_LLAMADAS:
				listarLlamadas();
				break;

			case EMITIR_FACTURAS:
				emitirFacturas();
				break;

			case MOSTRAR_FACTURA:
				mostrarFactura();
				break;

			case MOSTRAR_FACTURAS:
				mostrarFacturas();
				break;

			case MOSTRAR_CLIENTES_ENTRE_FECHAS:
				mostrarClientesEntreFechas();
				break;

			case MOSTRAR_FACTURAS_ENTRE_FECHAS:
				mostrarFacturasEntreFechas();
				break;

			case MOSTRAR_LLAMADAS_ENTRE_FECHAS:
				mostrarLlamadasEntreFechas();
				break;

			case SALIR_MENU:
				empresa.guardarDatos();
				break;

			default:
				throw new IllegalStateException("Valor no esperado: " + opcionMenu);
		}

		return opcion;
	}

	private static void altaCliente() {
		boolean esParticular = false;
		System.out.println("Es particular? S/N");
		String esParticular1 = teclado.nextLine();

		if (esParticular1.equals("S")) esParticular = true;

		System.out.println("Por favor, introduce el nombre");
		String nombre = teclado.nextLine();
		String apellido = "";

		if(esParticular) {
			System.out.println("Por favor, introduce el apellido");
			apellido = teclado.nextLine();
		}

		System.out.println("Por favor, introduce el NIF");
		String nif = teclado.nextLine();
		System.out.println("Por favor, introduce el código postal");
		String codigoPostal = teclado.nextLine();
		System.out.println("Por favor, introduce la provincia");
		String provincia = teclado.nextLine();
		System.out.println("Por favor, introduce la población");
		String poblacion = teclado.nextLine();
		System.out.println("Por favor, introduce el correo electrónico");
		String correo = teclado.nextLine();
		System.out.println("Por favor, introduce la fecha de alta (dd/MM/yyyy)");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Calendar fecha = Calendar.getInstance();

		try {
			fecha.setTime(formatter.parse(teclado.nextLine()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println("Por favor, elija su Tarifa:");
		System.out.println("0 - Tarifa Basica");
		System.out.println("1 - Tarifa Tarde");
		System.out.println("2 - Tarifa Domingo");

		int tarifaElegida = teclado.nextInt();
		Tarifa tarifa = FactoriaTarifa.basica();

		switch (tarifaElegida) {
			case 1:
				tarifa = FactoriaTarifa.tarde(tarifa);
				break;

			case 2:
				tarifa = FactoriaTarifa.domingo(tarifa);
				break;
		}

		Direccion direccion = new Direccion(provincia, poblacion, codigoPostal);
		Cliente cliente;

		if (esParticular)
			cliente = FactoriaCliente.particular(nombre, apellido, nif, direccion, correo, fecha, tarifa);
		else
			cliente = FactoriaCliente.empresa(nombre, nif, direccion, correo, fecha, tarifa);

		try {
			empresa.añadirCliente(cliente);
		} catch (ExcepcionClienteYaRegistrado ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void borrarCliente() {
		System.out.println("Por favor, introduce el NIF para borrar el cliente");
		String nif = teclado.nextLine();

		try {
			empresa.borrarCliente(nif);
			System.out.println("Cliente borrado con éxito");
		} catch (ExcepcionClienteNoEncontrado ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void cambiarTarifa() {
		System.out.println("Por favor, introduce el NIF del cliente");
		String nif = teclado.nextLine();

		System.out.println("Por favor, elija su Tarifa:");
		System.out.println("0 - Tarifa Basica");
		System.out.println("1 - Tarifa Tarde");
		System.out.println("2 - Tarifa Domingo");

		int tarifaElegida = teclado.nextInt();

		try {
			empresa.cambiarTarifa(nif, tarifaElegida);
			System.out.println("Tarifas cambiadas con éxito");
		} catch(ExcepcionClienteNoEncontrado ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void mostrarDatosCliente() {
		System.out.println("Por favor, introduce el NIF");
		String nif = teclado.nextLine();
		Cliente cliente;

		try {
			cliente = empresa.mostrarDatos(nif);
			System.out.println(cliente.toString());
			System.out.println("Datos mostrados con éxito");
		} catch (ExcepcionClienteNoEncontrado ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void mostrarListaClientes() {
		Collection<Cliente> clientes;

		try {
			clientes = empresa.mostrarListaClientes();
			for (Cliente cliente : clientes) {
				System.out.println(cliente.toString());
			}
		} catch (ExcepcionListaClientesVacia ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void altaLlamada() {
		System.out.println("Por favor, introduce el NIF ");
		String nif = teclado.nextLine();
		System.out.println("Por favor, introduce el número de destino");
		String numDestino = teclado.nextLine();
		System.out.println("Por favor, introduce la fecha de llamada (dd/MM/yyyy HH:mm)");
		String fechaLlamada = teclado.nextLine();

		try {
			if (fechaLlamada.length() != TAMFECHA)
				throw new ExcepcionFechas();
		} catch (ExcepcionFechas ex) {
			System.out.println(ex.getMessage());
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar fecha = Calendar.getInstance();

		try {
			fecha.setTime(formatter.parse(fechaLlamada));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println("Por favor, introduce la duración");
		int duracion = Integer.parseInt(teclado.nextLine());

		try {
			if (empresa.darDeAltaLlamada(nif, numDestino, fecha, duracion))
				System.out.println("Datos mostrados con éxito");
		} catch (ExcepcionClienteNoEncontrado ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void listarLlamadas() {
		System.out.println("Por favor, introduce el NIF ");
		String nif = teclado.nextLine();
		List<Llamada> llamadas;

		try {
			llamadas = empresa.listarLlamadas(nif);
			String devuelvo = "Llamadas Realizadas :";

			for (Llamada llam : llamadas) {
				String numero = llam.getTelefono();
				devuelvo = devuelvo + "\n" + numero;
			}

			System.out.println(devuelvo);
			System.out.println("Datos mostrados con éxito");
		} catch (ExcepcionListaLlamadasVacia ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void emitirFacturas() {
		System.out.println("Por favor, introduce el NIF del cliente");
		String nif = teclado.nextLine();

		try {
			empresa.emitirFacturas(nif);
			System.out.println("Factura emitida con éxito");
		} catch (ExcepcionClienteNoEncontrado | ExcepcionListaLlamadasVacia ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void mostrarFactura() {
		System.out.println("Por favor, introduce el numero de la factura");
		int codFactura = teclado.nextInt();
		teclado.nextLine();
		Factura factura;

		System.out.println("Por favor, introduce el NIF del cliente");
		String nif = teclado.nextLine();

		try {
			factura = empresa.mostrarFactura(nif, codFactura);
			System.out.println("Datos de la factura: ");
			System.out.println(factura.toString());
			System.out.println("Datos mostrados con éxito");
		} catch (ExcepcionListaFacturasVacia | ExcepcionFacturaNoEncontrada ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void mostrarFacturas(){
		System.out.println("Por favor, introduce el NIF del cliente");
		String nif = teclado.nextLine();
		List<Factura> facturas;

		try {
			facturas = empresa.mostrarFacturas(nif);
			for (Factura factura : facturas)
				System.out.println(factura.toString());
			System.out.println("Datos mostrados con éxito");
		} catch (ExcepcionListaFacturasVacia ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void mostrarClientesEntreFechas() {
		Collection<Cliente> clientes;

		try {
			Calendar[] listaFechas = obtenerFechas();
			clientes = empresa.mostrarListadoClientesFechas(listaFechas[0], listaFechas[1]);
			for (Cliente cliente : clientes) {
				System.out.println(cliente.toString());
			}
			System.out.println("Datos mostrados con éxito");
		} catch (ExcepcionFechas | ExcepcionListaClientesVacia ex) {
			System.out.println(ex.getMessage());
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
	}

	private static void mostrarFacturasEntreFechas() {
		System.out.println("Por favor, introduce el dni del cliente: ");
		String nif = teclado.nextLine();
		Collection<Factura> facturas;

		try {
			Calendar[] listaFechas = obtenerFechas();
			facturas = empresa.mostrarFacturasFechas(nif, listaFechas[0], listaFechas[1]);
			for (Factura fac : facturas) {
				System.out.println(fac.toString());
			}
			System.out.println("Datos mostrados con éxito");
		} catch (ExcepcionFechas | ExcepcionClienteNoEncontrado | ExcepcionListaFacturasVacia ex) {
			System.out.println(ex.getMessage());
		} catch (ParseException ex) {
			ex.printStackTrace();
		}

	}

	private static void mostrarLlamadasEntreFechas() {
		System.out.println("Por favor, introduce el dni del cliente: ");
		String nif = teclado.nextLine();

		try {
			Calendar[] listaFechas = obtenerFechas();
			empresa.mostrarListadoLlamadasFechas(nif, listaFechas[0], listaFechas[1]);
		} catch (ExcepcionFechas | ExcepcionClienteNoEncontrado | ExcepcionListaLlamadasVacia ex) {
			System.out.println(ex.getMessage());
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
	}

	private static Calendar[] obtenerFechas() throws ExcepcionFechas, ParseException {
		Calendar[] listaFechas = new Calendar[2];

		System.out.println("Por favor, introduce la primera fecha de llamada (dd/MM/yyyy HH:mm)");
		String fechaLlamada = teclado.nextLine();

		if (fechaLlamada.length() != TAMFECHA) {
			throw new ExcepcionFechas();
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		listaFechas[0].setTime(formatter.parse(fechaLlamada));

		System.out.println("Por favor, introduce la segunda fecha de llamada (dd/MM/yyyy HH:mm)");
		fechaLlamada = teclado.nextLine();

		if (fechaLlamada.length() != TAMFECHA) {
			throw new ExcepcionFechas();
		}

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		listaFechas[1].setTime(formatter.parse(fechaLlamada));

		return listaFechas;
	}
}