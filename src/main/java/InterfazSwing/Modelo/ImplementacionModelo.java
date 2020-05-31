package InterfazSwing.Modelo;

import datos.Cliente;
import datos.Factura;
import datos.Llamada;
import excepciones.*;
import fecha.FechaGenerico;
import tarifa.Tarifa;

import java.io.*;
import java.util.*;

public class  ImplementacionModelo implements Modelo {
    public HashMap<String, Cliente> clientes;
    public HashMap<String, List<Llamada>> llamadasPorNif;
    public List<Llamada> llamadas;
    public HashMap<String, List<Factura>> facturasPorNif;
    public HashMap<Integer, List<Factura>> facturasPorCodigo;

    public List<Factura> facturas;

    public ImplementacionModelo() {
        clientes = new HashMap<>();
        llamadas = new ArrayList<>();
        llamadasPorNif = new HashMap<>();
        facturas = new ArrayList<>();
        facturasPorNif = new HashMap<>();
    }

    public boolean addCliente(Cliente cliente) throws ExcepcionClienteYaRegistrado {
        String nif= cliente.getNif();
        if(this.clientes.containsKey(nif)){
            throw new ExcepcionClienteYaRegistrado();
        }
        this.clientes.put(nif,cliente);
        return true;
    }

    public boolean borrarCliente(String nif) throws ExcepcionClienteNoEncontrado {
        if (this.clientes.containsKey(nif)) {
            this.clientes.remove(nif);
            return true;
        }
        throw new ExcepcionClienteNoEncontrado();
    }

    public boolean cambiarTarifa(String nif, Tarifa tarifa) throws ExcepcionClienteNoEncontrado {
        if (this.clientes.containsKey(nif)) {
            this.clientes.get(nif).setTarifa(tarifa);
            return true;
        }
        throw new ExcepcionClienteNoEncontrado();
    }

    public Cliente recuperarDatosNIF(String nif) throws ExcepcionClienteNoEncontrado {
        if (this.clientes.containsKey(nif)) {
            return this.clientes.get(nif);
        }
        throw new ExcepcionClienteNoEncontrado();
    }

    public HashMap<String, Cliente> recuperarListadoClientes() throws ExcepcionListaClientesVacia {
        if(this.clientes.isEmpty())
            throw new ExcepcionListaClientesVacia();

        return this.clientes;
    }

    public Collection<Cliente> mostrarListadoClientesFechas(Calendar fechaInicio, Calendar fechaFin) throws ExcepcionFechas, ExcepcionListaClientesVacia{
        if(fechaInicio.after(fechaFin))
            throw new ExcepcionFechas();

        Collection<Cliente> clientes = this.clientes.values();
        clientes = FechaGenerico.getConjuntoPorFecha(clientes, fechaInicio, fechaFin);

        if(clientes.isEmpty())
            throw new ExcepcionListaClientesVacia();

        return clientes;
    }

    public boolean darDeAltaLlamada(String nif, Llamada llamada) throws ExcepcionClienteNoEncontrado {
        if(!this.clientes.containsKey(nif))
            throw new ExcepcionClienteNoEncontrado();

        if (!this.llamadasPorNif.containsKey(nif))
            this.llamadasPorNif.put(nif, new ArrayList<Llamada>());
        this.llamadasPorNif.get(nif).add(llamada);
        return true;
    }

    public List<Llamada> listarLlamadasCliente(String nif) throws ExcepcionClienteNoEncontrado {
        if (this.llamadasPorNif.containsKey(nif)) {
            return this.llamadasPorNif.get(nif);
        }
        throw new ExcepcionClienteNoEncontrado();
    }

    public Collection<Llamada> mostrarListadoLlamadasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionListaLlamadasVacia, ExcepcionFechas, ExcepcionClienteNoEncontrado{
        if(fechaInicio.after(fechaFin))
            throw new ExcepcionFechas();

        if(!this.llamadasPorNif.containsKey(nif))
            throw new ExcepcionClienteNoEncontrado();

        Collection<Llamada> llamadas = this.llamadasPorNif.get(nif);
        llamadas = FechaGenerico.getConjuntoPorFecha(llamadas, fechaInicio, fechaFin);
        if(llamadas.isEmpty())
            throw new ExcepcionListaLlamadasVacia();

        return llamadas;
    }


    public Factura emitirFactura(String nif, Calendar fechaFacturacion) throws ExcepcionClienteNoEncontrado{
        if(!this.clientes.containsKey(nif))
            throw new ExcepcionClienteNoEncontrado();

        int codigo = this.facturasPorCodigo.size();
        Tarifa tarifa =this.clientes.get(nif).getTarifa();
        Calendar fechaEmision = Calendar.getInstance();

        int duracionLlamadas = 0;
        List<Llamada> listaLlamadas = this.llamadasPorNif.get(nif);
        for (Llamada llamada : listaLlamadas) {
            if (llamada.getFecha().after(fechaFacturacion) && llamada.getFecha().before(fechaEmision))
                duracionLlamadas += llamada.getDuracion();
        }

        //double importe = (this.clientes.get(nif).getTarifa().getPrecioCorrecto() / 60) * duracionLlamadas;

        //Factura factura = new Factura(codigo, tarifa, fechaEmision,1, importe);

        if (this.facturasPorNif.get(nif) == null)
            this.facturasPorNif.put(nif, new ArrayList<Factura>());

//        this.facturas.get(nif).add(factura);
//        this.facturasPorCodigo.put(codigo, factura);

        return null;

    }

    public Factura recuperarDatosFacturaCodigo(Integer codigo) throws ExcepcionFacturaNoEncontrada {
        if (this.facturasPorCodigo.containsKey(codigo))
            return (Factura) this.facturasPorCodigo.get(codigo);

        throw new ExcepcionFacturaNoEncontrada();
    }

    public List<Factura> recuperarFacturas(String nif) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia {
        if (!this.facturasPorNif.containsKey(nif))
            throw new ExcepcionClienteNoEncontrado();

        List<Factura> listaFacturas = this.facturasPorNif.get(nif);
        if(listaFacturas.isEmpty())
            throw new ExcepcionListaFacturasVacia();

        return listaFacturas;
    }

    public Collection<Factura> mostrarListadoFacturasFechas(String nif, Calendar fechaInicio, Calendar fechaFin) throws ExcepcionClienteNoEncontrado, ExcepcionListaFacturasVacia, ExcepcionFechas {
        if(fechaInicio.after(fechaFin))
            throw new ExcepcionFechas();

        if(!this.facturasPorNif.containsKey(nif))
            throw new ExcepcionClienteNoEncontrado();

        Collection<Factura> facturas = this.facturasPorNif.get(nif);
        facturas = FechaGenerico.getConjuntoPorFecha(facturas, fechaInicio, fechaFin);

        if(facturas.isEmpty())
            throw new ExcepcionListaFacturasVacia();

        return facturas;
    }


    public void guardarDatos() {
        try {
            File file = new File("datos.bin");
            if (!file.exists())
                new FileOutputStream("datos.bin").close();

            FileOutputStream fileOutputStream = new FileOutputStream("datos.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(clientes);
            objectOutputStream.writeObject(facturas);
            objectOutputStream.writeObject(facturasPorNif);
            objectOutputStream.writeObject(facturasPorCodigo);
            objectOutputStream.writeObject(llamadas);
            objectOutputStream.writeObject(llamadasPorNif);

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
            facturas = (List<Factura>) objectInputStream.readObject();
            facturasPorNif = (HashMap<String, List<Factura>>) objectInputStream.readObject();
            facturasPorCodigo= (HashMap<Integer, List<Factura>>) objectInputStream.readObject();
            llamadas = (List<Llamada>) objectInputStream.readObject();
            llamadasPorNif = (HashMap<String, List<Llamada>>) objectInputStream.readObject();

            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
