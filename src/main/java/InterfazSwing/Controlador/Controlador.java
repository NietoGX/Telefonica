package InterfazSwing.Controlador;

import datos.Cliente;
import datos.Direccion;
import datos.Factura;
import datos.Llamada;
import excepciones.*;
import tarifa.Tarifa;

import java.util.*;

public interface Controlador {
    public boolean creaParticular(String nombre, String apellidos, String nif, Direccion dir, String correo, Calendar fecha, Tarifa tarifa) throws ExcepcionClienteYaRegistrado;
    public boolean creaEmpresa(String nombre, String nif, Direccion dir, String correo, Calendar fecha, Tarifa tarifa) throws ExcepcionClienteYaRegistrado;
    public boolean borraCliente(String nif) throws ExcepcionClienteNoEncontrado;
    public boolean cambiaTarifa(String nif, Tarifa tarifa) throws ExcepcionClienteNoEncontrado;
    public Cliente recuperarDatosCliente(String nif) throws ExcepcionClienteNoEncontrado;
    public HashMap<String, Cliente> recuperaListadoClientes() throws ExcepcionListaClientesVacia;
    public Collection<Cliente> recuperaListadoClientesEntreFechas(Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaClientesVacia, ExcepcionFechas;
    public boolean darDeAltaLlamada(String nif, Llamada llamada) throws ExcepcionClienteNoEncontrado;
    public List<Llamada> listarLlamadasCliente(String nif) throws ExcepcionClienteNoEncontrado;
    public Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaLlamadasVacia, ExcepcionFechas, ExcepcionClienteNoEncontrado;
    public Factura emitirFactura(String nif, Calendar fechaFacturacion) throws ExcepcionClienteNoEncontrado;
    public Factura recuperarDatosFacturaCodigo(Integer codigo) throws ExcepcionFacturaNoEncontrada;
    public List<Factura> recuperarFacturas(String nif) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia;
    public Collection<Factura> mostrarListadoFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia, ExcepcionFechas;

}
