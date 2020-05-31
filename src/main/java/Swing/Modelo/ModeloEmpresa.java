package Swing.Modelo;

import datos.Cliente;
import datos.Factura;
import datos.Llamada;
import excepciones.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public interface ModeloEmpresa {
    boolean añadirCliente(Cliente cliente) throws ExcepcionClienteYaRegistrado;
    boolean borrarCliente(String nif) throws ExcepcionClienteNoEncontrado;
    boolean cambiarTarifa(String nif, int tar) throws ExcepcionClienteNoEncontrado;
    Cliente mostrarDatos(String nif) throws ExcepcionClienteNoEncontrado;
    Collection<Cliente> mostrarListaClientes() throws ExcepcionListaClientesVacia;
    boolean darDeAltaLlamada(String nif, String numDestino, Calendar fecha, int duracion) throws ExcepcionClienteNoEncontrado;
    List<Llamada> listarLlamadas(String nif) throws ExcepcionListaLlamadasVacia;
    Factura emitirFacturas(String nif) throws ExcepcionClienteNoEncontrado;
    Factura mostrarFactura(String nif, int codFactura) throws ExcepcionListaFacturasVacia, ExcepcionFacturaNoEncontrada;
    List<Factura> mostrarFacturas(String nif) throws ExcepcionListaFacturasVacia;
    Collection<Factura> mostrarFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia, ExcepcionFechas;
    Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaLlamadasVacia, ExcepcionFechas, ExcepcionClienteNoEncontrado;
    Collection<Cliente> mostrarListadoClientesFechas(Calendar fechaInicio, Calendar fechaFin) throws ExcepcionFechas, ExcepcionListaClientesVacia;




    }
