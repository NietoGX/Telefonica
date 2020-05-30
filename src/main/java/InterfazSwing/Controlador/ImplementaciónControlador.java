package InterfazSwing.Controlador;

import InterfazSwing.Modelo.Modelo;
import datos.*;
import excepciones.*;
import tarifa.Tarifa;

import java.util.*;

public class ImplementaciónControlador implements Controlador{
    private Modelo modelo;

    public void setModelo(Modelo modelo){
        this.modelo=modelo;
    }
    public ImplementaciónControlador(){}

    public boolean creaParticular(String nombre, String apellidos, String nif, Direccion dir, String correo, Calendar fecha, Tarifa tarifa) throws ExcepcionClienteYaRegistrado {
        Cliente cliente= FactoriaCliente.particular(nombre,apellidos,nif, dir,correo,Calendar.getInstance(), tarifa);
        return modelo.addCliente(cliente);
    }
    public boolean creaEmpresa(String nombre, String nif, Direccion dir, String correo, Calendar fecha, Tarifa tarifa) throws ExcepcionClienteYaRegistrado {
        Cliente cliente= FactoriaCliente.empresa(nombre,nif, dir,correo,Calendar.getInstance(), tarifa);
        return modelo.addCliente(cliente);
    }
    public boolean borraCliente(String nif) throws ExcepcionClienteNoEncontrado{
        return modelo.borrarCliente(nif);
    }
    public boolean cambiaTarifa(String nif, Tarifa tarifa) throws ExcepcionClienteNoEncontrado {
        return modelo.cambiarTarifa(nif, tarifa);
    }

    public Cliente recuperarDatosCliente(String nif) throws ExcepcionClienteNoEncontrado {
        return modelo.recuperarDatosNIF(nif);

    }

    public HashMap<String, Cliente> recuperaListadoClientes() throws ExcepcionListaClientesVacia {
        return modelo.recuperarListadoClientes();

    }

    public Collection<Cliente> recuperaListadoClientesEntreFechas(Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaClientesVacia, ExcepcionFechas {
        return modelo.mostrarListadoClientesFechas(fechaInicio, fechaFin);
    }

    public boolean darDeAltaLlamada(String nif, Llamada llamada) throws ExcepcionClienteNoEncontrado{
        return modelo.darDeAltaLlamada(nif, llamada);
    }

    public List<Llamada> listarLlamadasCliente(String nif) throws ExcepcionClienteNoEncontrado{
        return modelo.listarLlamadasCliente(nif);
    }

    public Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaLlamadasVacia, ExcepcionFechas, ExcepcionClienteNoEncontrado{
        return modelo.mostrarListadoLlamadasFechas(nif, fechaInicio, fechaFin);
    }

    public Factura emitirFactura(String nif, Calendar fechaFacturacion) throws ExcepcionClienteNoEncontrado{
        return modelo.emitirFactura(nif, fechaFacturacion);
    }

    public Factura recuperarDatosFacturaCodigo(Integer codigo) throws ExcepcionFacturaNoEncontrada{
        return modelo.recuperarDatosFacturaCodigo(codigo);
    }

    public List<Factura> recuperarFacturas(String nif) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia{
        return modelo.recuperarFacturas(nif);
    }

    public Collection<Factura> mostrarListadoFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia, ExcepcionFechas{
        return modelo.mostrarListadoFacturasFechas(nif, fechaInicio, fechaFin);
    }



}
