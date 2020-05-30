package InterfazSwing.Modelo;

import datos.Cliente;
import datos.Factura;
import datos.Llamada;
import excepciones.*;
import tarifa.Tarifa;

import java.util.*;

public interface Modelo {
    public boolean addCliente(Cliente cliente) throws ExcepcionClienteYaRegistrado;
    public boolean borrarCliente(String nif) throws ExcepcionClienteNoEncontrado;
    public boolean cambiarTarifa(String nif, Tarifa tarifa) throws ExcepcionClienteNoEncontrado ;
    public Cliente recuperarDatosNIF(String nif) throws ExcepcionClienteNoEncontrado;
    public HashMap<String, Cliente> recuperarListadoClientes() throws ExcepcionListaClientesVacia;
    public Collection<Cliente> mostrarListadoClientesFechas(Calendar fechaInicio, Calendar fechaFin) throws ExcepcionFechas, ExcepcionListaClientesVacia;


    public boolean darDeAltaLlamada(String nif, Llamada llamada) throws ExcepcionClienteNoEncontrado ;
    public List<Llamada> listarLlamadasCliente(String nif) throws ExcepcionClienteNoEncontrado ;
    public Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaLlamadasVacia, ExcepcionFechas, ExcepcionClienteNoEncontrado;


    public Factura emitirFactura(String nif, Calendar fechaFacturacion) throws ExcepcionClienteNoEncontrado;
    public Factura recuperarDatosFacturaCodigo(Integer codigo) throws ExcepcionFacturaNoEncontrada ;
    public List<Factura> recuperarFacturas(String nif) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia ;
    public Collection<Factura> mostrarListadoFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia, ExcepcionFechas ;


    public void guardarDatos() ;
    public void cargarDatos() ;
}
