package Swing.Controlador;

import Swing.Modelo.ImplementacionModelo;
import datos.*;
import excepciones.*;
import tarifa.Tarifa;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class ImplementacionControlador implements ControladorEmpresa {
    private ImplementacionModelo modelo;

    public void setModelo(ImplementacionModelo modelo) {
        this.modelo = modelo;
    }
    public boolean creaParticular(String nombre, String apellidos, String nif, Direccion dir, String correo, Calendar fecha, Tarifa tarifa) throws ExcepcionClienteYaRegistrado {
        Cliente cliente= FactoriaCliente.particular(nombre,apellidos,nif, dir,correo,fecha, tarifa);
        return modelo.añadirCliente(cliente);
    }
    public boolean creaEmpresa(String nombre, String nif, Direccion dir, String correo, Calendar fecha, Tarifa tarifa) throws ExcepcionClienteYaRegistrado {
        Cliente cliente= FactoriaCliente.empresa(nombre,nif, dir,correo,fecha, tarifa);
        return modelo.añadirCliente(cliente);
    }

    public boolean borrarCliente(String nif) throws ExcepcionClienteNoEncontrado {
        return modelo.borrarCliente(nif);
    }

    public boolean cambiarTarifa(String nif, int tar) throws ExcepcionClienteNoEncontrado {
        return modelo.cambiarTarifa(nif, tar);
    }
    public Cliente mostrarDatos(String nif) throws ExcepcionClienteNoEncontrado {
        return modelo.mostrarDatos(nif);
    }
    public Collection<Cliente> mostrarListaClientes() throws ExcepcionListaClientesVacia {
        return modelo.mostrarListaClientes();
    }
    public boolean darDeAltaLlamada(String nif, String numDestino, Calendar fecha, int duracion) throws ExcepcionClienteNoEncontrado {
        return modelo.darDeAltaLlamada(nif,numDestino,fecha,duracion);
    }
    public List<Llamada> listarLlamadas(String nif) throws ExcepcionListaLlamadasVacia {
        return modelo.listarLlamadas(nif);
    }
    public Factura emitirFacturas(String nif) throws ExcepcionClienteNoEncontrado, ExcepcionListaLlamadasVacia {
        return modelo.emitirFacturas(nif);
    }
    public Factura mostrarFactura(String nif, int codFactura) throws ExcepcionListaFacturasVacia, ExcepcionFacturaNoEncontrada {
        return modelo.mostrarFactura(nif,codFactura);
    }
    public List<Factura> mostrarFacturas(String nif) throws ExcepcionListaFacturasVacia {
        return modelo.mostrarFacturas(nif);
    }
    public Collection<Factura> mostrarFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia, ExcepcionFechas {
        return modelo.mostrarFacturasFechas(nif,fechaInicio,fechaFin);
    }
    public Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaLlamadasVacia, ExcepcionFechas, ExcepcionClienteNoEncontrado {
        return modelo.mostrarListadoLlamadasFechas(nif,fechaInicio,fechaFin);
    }
    public Collection<Cliente> mostrarListadoClientesFechas(Calendar fechaInicio, Calendar fechaFin) throws ExcepcionFechas, ExcepcionListaClientesVacia {
        return modelo.mostrarListadoClientesFechas(fechaInicio, fechaFin);
    }

}



