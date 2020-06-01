package Swing.Modelo;

import excepciones.*;
import fecha.FechaGenerico;
import datos.Cliente;
import datos.Factura;
import datos.Llamada;
import tarifa.FactoriaTarifa;
import tarifa.Tarifa;

import java.io.*;
import java.util.*;

public class ImplementacionModelo implements Serializable, ModeloEmpresa {
    public HashMap<String, Cliente> clientes;
    public HashMap<String, List<Llamada>> llamadasPorNif;
    public HashMap<String, List<Factura>> facturasPorNif;
    public int idFactura;

    public ImplementacionModelo() {
        clientes = new HashMap<>();
        llamadasPorNif = new HashMap<>();
        facturasPorNif = new HashMap<>();
        idFactura = 0;
    }

    public boolean añadirCliente(Cliente cliente) throws ExcepcionClienteYaRegistrado {
        String nif= cliente.getNif();

        if (clientes.containsKey(nif)) {
            throw new ExcepcionClienteYaRegistrado();
        }

        clientes.put(nif,cliente);

        return true;
    }

    public boolean borrarCliente(String nif) throws ExcepcionClienteNoEncontrado {
        if (clientes.remove(nif) == null)
            throw new ExcepcionClienteNoEncontrado();
        return true;
    }

    public boolean cambiarTarifa(String nif, int tar) throws ExcepcionClienteNoEncontrado{
        Cliente cliente = clientes.get(nif);
        if (cliente == null)
            throw new ExcepcionClienteNoEncontrado();

        Tarifa tarifa = FactoriaTarifa.basica();

        switch (tar) {
            case 0:
                tarifa = FactoriaTarifa.basica();
                break;
            case 1:
                tarifa = FactoriaTarifa.tarde(tarifa);
                break;
            case 2:
                tarifa = FactoriaTarifa.domingo(tarifa);
                break;
            case 3:
                tarifa = FactoriaTarifa.tarde(tarifa);
                tarifa = FactoriaTarifa.domingo(tarifa);
                break;
        }

        cliente.setTarifa(tarifa);
        return true;
    }

    public Cliente mostrarDatos(String nif) throws ExcepcionClienteNoEncontrado {
        Cliente cliente = clientes.get(nif);

        if (cliente == null)
            throw new ExcepcionClienteNoEncontrado();

        return cliente;
    }

    public Collection<Cliente> mostrarListaClientes() throws ExcepcionListaClientesVacia{
        if (clientes.isEmpty())
            throw new ExcepcionListaClientesVacia();
        return clientes.values();
    }

    public boolean darDeAltaLlamada(String nif, String numDestino, Calendar fecha, int duracion) throws ExcepcionClienteNoEncontrado {
        Llamada llamada = new Llamada(numDestino, fecha, duracion, false);

        if(clientes.get(nif)==null){
            throw new ExcepcionClienteNoEncontrado();
        }

        if(llamadasPorNif.get(nif) == null){
            llamadasPorNif.put(nif, new ArrayList<>());
        }

        llamadasPorNif.get(nif).add(llamada);

        return true;
    }

    public List<Llamada> listarLlamadas(String nif) throws ExcepcionListaLlamadasVacia {
        List<Llamada> llamadas = llamadasPorNif.get(nif);

        if (llamadas != null) {
            return llamadas;
        } else
            throw new ExcepcionListaLlamadasVacia();
    }

    public Factura emitirFacturas(String nif) throws ExcepcionClienteNoEncontrado, ExcepcionListaLlamadasVacia {
        List<Llamada> llamadas = llamadasPorNif.get(nif);
        Cliente cliente = clientes.get(nif);

        if (llamadas == null)
            throw new ExcepcionListaLlamadasVacia();
        if (cliente == null)
            throw new ExcepcionClienteNoEncontrado();

        double importe = 0;
        int numLlamadas =  0;
        Tarifa tarifa = cliente.getTarifa();

        for (Llamada llamada : llamadas) {
            if (!llamada.getFacturada()) {
                importe += tarifa.getPrecioCorrecto(llamada, tarifa);
                numLlamadas += 1;
                llamada.setFacturada(true);
            }
        }

        if (numLlamadas > 0) {
            importe = importe / 60;
            Factura factura = new Factura(idFactura, tarifa, Calendar.getInstance(), importe);
            idFactura += 1;

            if(facturasPorNif.get(nif) == null) {
                facturasPorNif.put(nif, new ArrayList<>());
            }

            facturasPorNif.get(nif).add(factura);

            return factura;
        } else
            return null;

    }

    public Factura mostrarFactura(String nif, int codFactura) throws ExcepcionListaFacturasVacia, ExcepcionFacturaNoEncontrada {
        List<Factura> facturas = facturasPorNif.get(nif);

        if (facturas != null) {
            for(Factura f: facturas){
                if (f.getCodFactura()==codFactura)
                    return f;
            }
            throw new ExcepcionFacturaNoEncontrada();
        } else
            throw new ExcepcionListaFacturasVacia();
    }

    public List<Factura> mostrarFacturas(String nif) throws ExcepcionListaFacturasVacia {
        List<Factura> facturas = facturasPorNif.get(nif);

        if (facturas != null) {
            return facturas;
        } else
            throw new ExcepcionListaFacturasVacia();
    }

    public Collection<Factura> mostrarFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia, ExcepcionFechas {
        if (fechaInicio.after(fechaFin))
            throw new ExcepcionFechas();

        if (!facturasPorNif.containsKey(nif))
            throw new ExcepcionClienteNoEncontrado();

        Collection<Factura> facturas = facturasPorNif.get(nif);
        facturas = FechaGenerico.getConjuntoPorFecha(facturas, fechaInicio, fechaFin);

        if (facturas.isEmpty())
            throw new ExcepcionListaFacturasVacia();

        return facturas;
    }

    public Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaLlamadasVacia, ExcepcionFechas, ExcepcionClienteNoEncontrado {
        if (fechaInicio.after(fechaFin))
            throw new ExcepcionFechas();

        if (!llamadasPorNif.containsKey(nif))
            throw new ExcepcionClienteNoEncontrado();

        Collection<Llamada> llamadas = llamadasPorNif.get(nif);
        llamadas = FechaGenerico.getConjuntoPorFecha(llamadas, fechaInicio, fechaFin);

        if (llamadas.isEmpty())
            throw new ExcepcionListaLlamadasVacia();

        return llamadas;
    }

    public Collection<Cliente> mostrarListadoClientesFechas(Calendar fechaInicio, Calendar fechaFin) throws ExcepcionFechas, ExcepcionListaClientesVacia {
        if (fechaInicio.after(fechaFin))
            throw new ExcepcionFechas();

        Collection<Cliente> clientes = this.clientes.values();
        clientes = FechaGenerico.getConjuntoPorFecha(clientes, fechaInicio, fechaFin);

        if (clientes.isEmpty())
            throw new ExcepcionListaClientesVacia();

        return clientes;
    }

    public void guardarDatos() {
        try {
            File file = new File("datos.bin");
            if (!file.exists())
                new FileOutputStream("datos.bin").close();

            FileOutputStream fileOutputStream = new FileOutputStream("datos.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(clientes);
            objectOutputStream.writeObject(facturasPorNif);
            objectOutputStream.writeObject(llamadasPorNif);
            objectOutputStream.writeObject(idFactura);

            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarDatos() {
        try {
            File file = new File("datos.bin");
            if (!file.exists())
                return;

            FileInputStream fileInputStream = new FileInputStream("datos.bin");
            if (fileInputStream.available() == 0)
                return;

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            clientes = (HashMap<String, Cliente>) objectInputStream.readObject();
            facturasPorNif = (HashMap<String, List<Factura>>) objectInputStream.readObject();
            llamadasPorNif = (HashMap<String, List<Llamada>>) objectInputStream.readObject();
            idFactura = (int) objectInputStream.readObject();


            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}