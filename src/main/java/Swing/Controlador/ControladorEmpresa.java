package Swing.Controlador;

import Swing.Modelo.EmpresaTelefonia;
import datos.Cliente;
import datos.Direccion;
import datos.Factura;
import datos.Llamada;
import excepciones.*;
import tarifa.Tarifa;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public interface ControladorEmpresa {
     void setModelo(EmpresaTelefonia modelo);

     boolean creaParticular(String nombre, String apellidos, String nif, Direccion dir, String correo, Calendar fecha, Tarifa tarifa) throws ExcepcionClienteYaRegistrado;
     boolean creaEmpresa(String nombre, String nif, Direccion dir, String correo, Calendar fecha, Tarifa tarifa) throws ExcepcionClienteYaRegistrado;
     boolean borrarCliente(String nif) throws ExcepcionClienteNoEncontrado;
     boolean cambiarTarifa(String nif, int tar) throws ExcepcionClienteNoEncontrado;
     Cliente mostrarDatos(String nif) throws ExcepcionClienteNoEncontrado;
     Collection<Cliente> mostrarListaClientes() throws ExcepcionListaClientesVacia;
     boolean darDeAltaLlamada(String nif, String numDestino, Calendar fecha, int duracion) throws ExcepcionClienteNoEncontrado;
     List<Llamada> listarLlamadas(String nif) throws ExcepcionListaLlamadasVacia ;
     Factura emitirFacturas(String nif) throws ExcepcionClienteNoEncontrado ;
     Factura mostrarFactura(String nif, int codFactura)  throws ExcepcionListaFacturasVacia ;
     List<Factura> mostrarFacturas(String nif) throws ExcepcionListaFacturasVacia ;
     Collection<Factura> mostrarFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia, ExcepcionFechas;
     Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaLlamadasVacia, ExcepcionFechas, ExcepcionClienteNoEncontrado;
     Collection<Cliente> mostrarListadoClientesFechas(Calendar fechaInicio, Calendar fechaFin) throws ExcepcionFechas, ExcepcionListaClientesVacia;
}
