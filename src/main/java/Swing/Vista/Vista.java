package Swing.Vista;

import Swing.Controlador.ControladorEmpresa;
import Swing.Modelo.ImplementacionModelo;
import datos.Cliente;
import datos.Direccion;
import datos.Factura;
import datos.Llamada;
import excepciones.*;
import tarifa.FactoriaTarifa;
import tarifa.Tarifa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class Vista {
    private ImplementacionModelo modelo;
    private ControladorEmpresa controlador;
    private JFrame ventana;
    Container contenedor;

    JPanel submenu=new JPanel();

    JPanel mid;
    JPanel res;
//VARIABLES PARA LOS LISTENERS
    JTextField nifT = null;
    JTextField nombreT = null;
    JTextField apellidoT = null;
    JTextField codPosT = null;
    JTextField dirT=null;
    JTextField correoT = null;
    JTextField fecha1 = null;
    JTextField fecha2 = null;
    JTextField poblacionT=null;
    JTextField provinciaT=null;
    JTextField telefonoT=null;

    JTextField añoT = null;
    JTextField tarifaT = null;
    JTextField codFacT = null;
    JTextField durT = null;
    JTextField tipoTaT;
    JRadioButton button=null;
    JButton submit = null;
    JRadioButton r1= null;
    JRadioButton r2= null;
    JRadioButton r3= null;
    JRadioButton r4=null;
    String patternFechaClientes = "dd/MM/yyyy";

    String patternFecha = "dd/MM/yyyy HH:mm";

    public void setModelo(ImplementacionModelo modelo) {
        this.modelo = modelo;
    }

    public void setControlador(ControladorEmpresa controlador) {
        this.controlador = controlador;
    }

    public Vista() {
    }

    public void iniciaGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MenuPrincipal();
            }
        });
    }

    //MENUS-------------------------------------------------------------------------------------------------------
    public void MenuPrincipal(){
        ventana= new JFrame("EmpresaTelefónica");
        contenedor=ventana.getContentPane();
        JPanel menu=new JPanel();

        EscuchadorMenu listener= new EscuchadorMenu();
        JButton boton= new JButton("Clientes");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        menu.add(boton);
        boton= new JButton("Llamadas");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        menu.add(boton);
        boton= new JButton("Facturas");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        menu.add(boton);


        contenedor.add(menu,BorderLayout.NORTH);
        submenu= new JPanel();
        mid= new JPanel();
        res=new JPanel();
        submenu.setLayout(new BoxLayout(submenu,BoxLayout.Y_AXIS));
        contenedor.add(submenu,BorderLayout.WEST);
        contenedor.add(mid, BorderLayout.CENTER);
        contenedor.add(res,BorderLayout.SOUTH);

        ventana.setSize(1500, 600);
        menu.setBackground(Color.DARK_GRAY);
        submenu.setBackground(Color.DARK_GRAY);
        mid.setBackground(Color.DARK_GRAY);
        res.setBackground(Color.DARK_GRAY);

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            modelo.guardarDatos();
        }));


    }
    public void VistaClientes() {
        submenu.removeAll();
        contenedor.remove(submenu);
        submenu=new JPanel();
        submenu.setLayout(new BoxLayout(submenu,BoxLayout.Y_AXIS));
        contenedor.add(submenu,BorderLayout.WEST);


        res.removeAll();
        JPanel vistaCliente = new JPanel();
        vistaCliente.setLayout(new GridLayout(0,1,1,10));
        vistaCliente.setBackground(Color.DARK_GRAY);
        EscuchadorCliente listener = new EscuchadorCliente();
        JButton boton = new JButton("Nuevo particular");
        boton.setBackground(Color.gray);

        boton.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        boton.addActionListener(listener);
        vistaCliente.add(boton);
        boton = new JButton("Nueva empresa");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        vistaCliente.add(boton);
        boton = new JButton("Borrar cliente");
        boton.setBackground(Color.gray);

        boton.addActionListener(listener);
        vistaCliente.add(boton);
        boton = new JButton("Cambiar tarifa");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        vistaCliente.add(boton);
        boton = new JButton("Recuperar datos cliente");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        vistaCliente.add(boton);
        boton = new JButton("Recuperar listado clientes");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        vistaCliente.add(boton);
        boton = new JButton("Mostrar listado clientes entre fechas");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        vistaCliente.add(boton);

        Component[] components = submenu.getComponents();

        for (Component component : components) {
            submenu.remove(component);
        }
        components = mid.getComponents();

        for (Component component : components) {
            mid.remove(component);
        }


        submenu.repaint();
        mid.repaint();
        submenu.add(vistaCliente, BorderLayout.WEST);
        submenu.setBackground(Color.DARK_GRAY);
        mid.add(submenu, BorderLayout.WEST);
        submenu.updateUI();
        mid.updateUI();


    }

    public void VistaLlamadas() {
        mid.removeAll();
        res.removeAll();
        contenedor.remove(submenu);
        submenu=new JPanel();
        contenedor.add(submenu,BorderLayout.WEST);
        JPanel vistaLlamadas = new JPanel();
        vistaLlamadas.setLayout(new GridLayout(0,1,1,10));
        vistaLlamadas.setBackground(Color.DARK_GRAY);
        EscuchadorLlamada listener = new EscuchadorLlamada();
        JButton boton = new JButton("Añadir llamada");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        vistaLlamadas.add(boton);
        boton = new JButton("Listar llamadas");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        vistaLlamadas.add(boton);

        boton = new JButton("Listar llamadas entre fechas");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        vistaLlamadas.add(boton);

        vistaLlamadas.add(boton);



        Component[] components = submenu.getComponents();

        for (Component component : components) {
            submenu.remove(component);
        }
        components = mid.getComponents();

        for (Component component : components) {
            mid.remove(component);
        }

        submenu.repaint();

        mid.repaint();
        submenu.add(vistaLlamadas, BorderLayout.WEST);
        submenu.setBackground(Color.DARK_GRAY);
        mid.add(submenu, BorderLayout.WEST);
        submenu.updateUI();
        mid.updateUI();


    }
    private void VistaFacturas() {
        mid.removeAll();
        submenu.removeAll();
        submenu= new JPanel();
        submenu.setLayout(new BoxLayout(submenu,BoxLayout.Y_AXIS));
        contenedor.remove(contenedor);
        contenedor.add(submenu,BorderLayout.WEST);
        res.removeAll();
        JPanel vistaFactura= new JPanel();
        vistaFactura.setLayout(new GridLayout(0,1,1,10));
        vistaFactura.setBackground(Color.DARK_GRAY);
        EscuchadorFacturas listener = new EscuchadorFacturas();

        JButton boton = new JButton("Emitir facturas");
        boton.setBackground(Color.gray);
        boton.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        boton.addActionListener(listener);
        vistaFactura.add(boton);
        boton = new JButton("Mostrar factura");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        vistaFactura.add(boton);
        boton = new JButton("Mostrar facturas");
        boton.setBackground(Color.gray);

        boton.addActionListener(listener);
        vistaFactura.add(boton);
        boton = new JButton("Mostrar facturas entre fechas");
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        vistaFactura.add(boton);

        Component[] components = submenu.getComponents();

        for (Component component : components) {
            submenu.remove(component);
        }
        components = mid.getComponents();

        for (Component component : components) {
            mid.remove(component);
        }


        submenu.repaint();


        mid.repaint();

        submenu.add(vistaFactura, BorderLayout.WEST);
        submenu.setBackground(Color.DARK_GRAY);
        mid.add(submenu, BorderLayout.WEST);
        submenu.updateUI();
        mid.updateUI();



    }
    //VISTAS CLIENTES
    public void VistaDarDeAltaParticular(){
        mid.removeAll();
        res.removeAll();
        JPanel altaCliente= new JPanel();

        JPanel labels= new JPanel();
        labels.setLayout(new GridLayout(0,1,1,15));
        JPanel text= new JPanel();
        text.setLayout(new GridLayout(0,1,1,6));
        text.setBackground(Color.DARK_GRAY);

        JLabel nombre= new JLabel("Nombre: ");
        JLabel apellido= new JLabel("Apellido: ");
        JLabel nif= new JLabel("NIF: ");
        JLabel poblacion= new JLabel("Población: ");
        JLabel provincia= new JLabel("Provincia: ");
        JLabel codPos= new JLabel("Código postal: ");
        JLabel email= new JLabel("E-mail: ");
        JLabel fecha= new JLabel("Fecha(dd/mm/yyyy): ");
        JLabel tarifa= new JLabel("Tarifa: ");

        labels.add(nombre);
        labels.add(apellido);
        labels.add(nif);
        labels.add(poblacion);
        labels.add(provincia);
        labels.add(codPos);
        labels.add(email);
        labels.add(fecha);
        labels.add(tarifa);

        nombreT= new JTextField(30);
        apellidoT= new JTextField(30);
        nifT= new JTextField(30);
        poblacionT= new JTextField(30);
        provinciaT= new JTextField(30);
        codPosT= new JTextField(30);
        correoT= new JTextField(30);
        fecha1= new JTextField(30);
        r1= new JRadioButton("Básica");
        r2= new JRadioButton("Tarde");
        r3= new JRadioButton("Domingo");
        r4= new JRadioButton("Tarde y Domingo");
        ButtonGroup tarifaT= new ButtonGroup();
        tarifaT.add(r1);
        tarifaT.add(r2);
        tarifaT.add(r3);
        tarifaT.add(r4);

        JPanel radios= new JPanel();
        radios.add(r1);
        radios.add(r2);
        radios.add(r3);
        radios.add(r4);

        radios.setLayout(new BoxLayout(radios,BoxLayout.X_AXIS));

        text.add(nombreT);
        text.add(apellidoT);
        text.add(nifT);
        text.add(poblacionT);
        text.add(provinciaT);
        text.add(codPosT);
        text.add(correoT);
        text.add(fecha1);
        text.add(radios);



        altaCliente.add(labels,BorderLayout.WEST);
        altaCliente.add(text,BorderLayout.CENTER);
        EscuchadorCreaParticular listener= new EscuchadorCreaParticular();
        JButton submit= new JButton("Enviar");
        submit.addActionListener(listener);
        altaCliente.add(submit,BorderLayout.SOUTH);
        altaCliente.setBackground(Color.DARK_GRAY);

        mid.add(altaCliente);

        altaCliente.updateUI();
        labels.updateUI();
        text.updateUI();
        mid.updateUI();



    }
    public void VistaDarDeAltaEmpresa(){
        mid.removeAll();
        res.removeAll();
        JPanel altaEmpresa= new JPanel();

        JPanel labels= new JPanel();
        labels.setLayout(new GridLayout(0,1,1,15));
        JPanel text= new JPanel();
        text.setLayout(new GridLayout(0,1,1,6));
        text.setBackground(Color.DARK_GRAY);

        JLabel nombre= new JLabel("Nombre: ");
        JLabel nif= new JLabel("NIF: ");
        JLabel poblacion= new JLabel("Población: ");
        JLabel provincia= new JLabel("Provincia: ");
        JLabel codPos= new JLabel("Código postal: ");
        JLabel email= new JLabel("E-mail: ");
        JLabel fecha= new JLabel("Fecha(dd/mm/yyyy): ");
        JLabel tarifa= new JLabel("Tarifa: ");

        labels.add(nombre);
        labels.add(nif);
        labels.add(poblacion);
        labels.add(provincia);
        labels.add(codPos);
        labels.add(email);
        labels.add(fecha);
        labels.add(tarifa);

        nombreT= new JTextField(30);
        nifT= new JTextField(30);
        poblacionT= new JTextField(30);
        provinciaT= new JTextField(30);
        codPosT= new JTextField(30);
        correoT= new JTextField(30);
        fecha1= new JTextField(30);
        r1= new JRadioButton("Básica");
        r2= new JRadioButton("Tarde");
        r3= new JRadioButton("Domingo");
        r4=new JRadioButton("Tarde y Domingo");
        ButtonGroup tarifaT= new ButtonGroup();
        tarifaT.add(r1);
        tarifaT.add(r2);
        tarifaT.add(r3);
        tarifaT.add(r4);

        JPanel radios= new JPanel();
        radios.add(r1);
        radios.add(r2);
        radios.add(r3);
        radios.add(r4);

        radios.setLayout(new BoxLayout(radios,BoxLayout.X_AXIS));

        text.add(nombreT);
        text.add(nifT);
        text.add(poblacionT);
        text.add(provinciaT);
        text.add(codPosT);
        text.add(correoT);
        text.add(fecha1);
        text.add(radios);

        altaEmpresa.add(labels,BorderLayout.WEST);
        altaEmpresa.add(text,BorderLayout.CENTER);
        EscuchadorCreaEmpresa listener= new EscuchadorCreaEmpresa();
        JButton submit= new JButton("Enviar");
        submit.addActionListener(listener);
        altaEmpresa.add(submit,BorderLayout.SOUTH);
        altaEmpresa.setBackground(Color.DARK_GRAY);

        mid.add(altaEmpresa);

        altaEmpresa.updateUI();
        labels.updateUI();
        text.updateUI();
        mid.updateUI();



    }
    public void VistaBorrarCliente(){
        mid.removeAll();
        res.removeAll();
        JPanel borrarCliente= new JPanel();
        borrarCliente.setLayout(new BoxLayout(borrarCliente,BoxLayout.X_AXIS));
        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(30);
        borrarCliente.add(nif);
        borrarCliente.add(nifT);
        EscuchadorBorrarCliente listener= new EscuchadorBorrarCliente();
        JButton submit= new JButton("Enviar");
        submit.addActionListener(listener);
        borrarCliente.add(submit);

        mid.add(borrarCliente);
        borrarCliente.updateUI();
        mid.updateUI();
    }
    public void VistaCambiarTarifa(){
        mid.removeAll();
        res.removeAll();
        JPanel cambiarTarifa= new JPanel();
        cambiarTarifa.setLayout(new BoxLayout(cambiarTarifa,BoxLayout.X_AXIS));
        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);
        cambiarTarifa.add(nif);
        cambiarTarifa.add(nifT);

        r1= new JRadioButton("Básica");
        r2= new JRadioButton("Tarde");
        r3= new JRadioButton("Domingo");
        r4= new JRadioButton("Tarde y Domingo");
        ButtonGroup tarifaT= new ButtonGroup();
        tarifaT.add(r1);
        tarifaT.add(r2);
        tarifaT.add(r3);
        EscuchadorCambiarTarifa listener= new EscuchadorCambiarTarifa();
        JButton submit= new JButton("Enviar");
        submit.addActionListener(listener);

        cambiarTarifa.add(r1);
        cambiarTarifa.add(r2);
        cambiarTarifa.add(r3);
        cambiarTarifa.add(r4);
        cambiarTarifa.add(submit);
        mid.add(cambiarTarifa);
        cambiarTarifa.updateUI();
        mid.updateUI();
    }
    public void VistaMostrarCliente(){
        mid.removeAll();
        res.removeAll();
        JPanel mostrarCLiente= new JPanel();
        mostrarCLiente.setLayout(new BoxLayout(mostrarCLiente,BoxLayout.X_AXIS));
        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);
        mostrarCLiente.add(nif);
        mostrarCLiente.add(nifT);
        EscuchadorMostrarCliente listener = new EscuchadorMostrarCliente();

        JButton boton = new JButton("Enviar");

        boton.addActionListener(listener);
        mostrarCLiente.add(boton);

        mid.add(mostrarCLiente);
        mostrarCLiente.updateUI();
        mid.updateUI();
    }
    private void VistaMostrarClientes(){
        res.removeAll();
        mid.removeAll();
        EscuchadorMostrarClientes listener= new EscuchadorMostrarClientes();
        JButton boton= new JButton("Mostrar listado clientes");
        boton.addActionListener(listener);
        mid.add(boton);
        res.updateUI();
        mid.updateUI();


    }
    private void VistaMostrarClientesEntreFechas() {
        mid.removeAll();
        res.removeAll();
        JPanel mostrarClienteEntreFechas= new JPanel();
        mostrarClienteEntreFechas.setLayout(new BoxLayout(mostrarClienteEntreFechas,BoxLayout.X_AXIS));

        JLabel fechaIni= new JLabel("Fecha inicio(dd/mm/yyyy):  ");
        fecha1= new JTextField(12);
        JLabel fechaFin= new JLabel("          Fecha fin(dd/mm/yyyy): ");
        fecha2= new JTextField(12);
        mostrarClienteEntreFechas.add(fechaIni);
        mostrarClienteEntreFechas.add(fecha1);
        mostrarClienteEntreFechas.add(fechaFin);
        mostrarClienteEntreFechas.add(fecha2);
        EscuchadorListarClientesEntreFechas listener= new EscuchadorListarClientesEntreFechas();
        JButton submit= new JButton("Enviar");
        submit.addActionListener(listener);
        mostrarClienteEntreFechas.add(submit);

        mid.add(mostrarClienteEntreFechas);
        mostrarClienteEntreFechas.updateUI();
        mid.updateUI();
    }
    //VISTAS FACTURAS--------------------------------------------
    private void VistaEmitirFactura() {
        mid.removeAll();
        res.removeAll();
        JPanel emitirFactura= new JPanel();
        emitirFactura.setLayout(new BoxLayout(emitirFactura,BoxLayout.X_AXIS));

        JPanel labels= new JPanel();

        labels.setLayout(new GridLayout(0,1,1,15));
        JPanel text= new JPanel();
        text.setLayout(new GridLayout(0,1,1,5));

        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);

        labels.add(nif);
        text.add(nifT);

        emitirFactura.add(labels);
        emitirFactura.add(text);
        EscuchadorEmitirFactura listener = new EscuchadorEmitirFactura();
        JButton submit= new JButton("Enviar");
        submit.addActionListener(listener);
        emitirFactura.add(submit);

        mid.add(emitirFactura);
        emitirFactura.updateUI();
        mid.updateUI();
    }
    public void VistaMostrarFacturas(){
        mid.removeAll();
        res.removeAll();
        JPanel mostrarFactura= new JPanel();
        mostrarFactura.setLayout(new BoxLayout(mostrarFactura,BoxLayout.X_AXIS));

        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);



        mostrarFactura.add(nif);
        mostrarFactura.add(nifT);
        JButton submit= new JButton("Enviar");
        EscuchadorMostrarFacturas listener= new EscuchadorMostrarFacturas();
        submit.addActionListener(listener);
        mostrarFactura.add(submit);

        mid.add(mostrarFactura);
        mostrarFactura.updateUI();
        mid.updateUI();

    }
    public void VistaMostrarFactura(){
        mid.removeAll();
        res.removeAll();
        JPanel mostrarFactura= new JPanel();
        mostrarFactura.setLayout(new BoxLayout(mostrarFactura,BoxLayout.X_AXIS));

        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);
        JLabel codFac= new JLabel("Código de la factura: ");
        codFacT= new JTextField(12);



        mostrarFactura.add(nif);
        mostrarFactura.add(nifT);
        mostrarFactura.add(codFac);
        mostrarFactura.add(codFacT);
        EscuchadorMostrarFactura listener= new EscuchadorMostrarFactura();
        JButton submit= new JButton("Enviar");
        submit.addActionListener(listener);

        mostrarFactura.add(submit);

        mid.add(mostrarFactura);
        mostrarFactura.updateUI();
        mid.updateUI();

    }
    private void VistaMostrarFacturasEntreFechas() {
        mid.removeAll();
        res.removeAll();
        JPanel mostrarFacturasEntreFechas= new JPanel();
        mostrarFacturasEntreFechas.setLayout(new BoxLayout(mostrarFacturasEntreFechas,BoxLayout.X_AXIS));

        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);
        JLabel fechaIni= new JLabel("Fecha inicio(dd/mm/yyyy): ");
        fecha1= new JTextField(12);
        JLabel fechaFin= new JLabel("          Fecha fin(dd/mm/yyyy): ");
        fecha2= new JTextField(12);

        mostrarFacturasEntreFechas.add(nif);
        mostrarFacturasEntreFechas.add(nifT);
        mostrarFacturasEntreFechas.add(fechaIni);
        mostrarFacturasEntreFechas.add(fecha1);
        mostrarFacturasEntreFechas.add(fechaFin);
        mostrarFacturasEntreFechas.add(fecha2);
        EscuchadorListarFacturasEntreFechas listener= new EscuchadorListarFacturasEntreFechas();
        JButton submit= new JButton("Enviar");
        submit.addActionListener(listener);
        mostrarFacturasEntreFechas.add(submit);

        mid.add(mostrarFacturasEntreFechas);
        mostrarFacturasEntreFechas.updateUI();
        mid.updateUI();

    }
    //VISTA LLAMADAS--------------------------------------------------------------
    private void VistaDarDeAltaLLamada() {
        mid.removeAll();
        res.removeAll();
        JPanel altaLlamada= new JPanel();
        altaLlamada.setLayout(new BoxLayout(altaLlamada,BoxLayout.X_AXIS));
        JPanel labels= new JPanel();

        labels.setLayout(new GridLayout(0,1,1,15));
        JPanel text= new JPanel();
        text.setLayout(new GridLayout(0,1,1,5));

        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);
        JLabel telefono= new JLabel("Teléfono: ");
        telefonoT= new JTextField(12);
        JLabel fechaAlta= new JLabel("Fecha de alta(dd/mm/yyyy hh:mm): ");
        fecha1= new JTextField(12);
        JLabel tiempo= new JLabel("Tiempo: ");
        durT= new JTextField(12);

        labels.add(nif);
        labels.add(telefono);
        labels.add(fechaAlta);
        labels.add(tiempo);

        text.add(nifT);
        text.add(telefonoT);
        text.add(fecha1);
        text.add(durT);



        altaLlamada.add(labels);
        altaLlamada.add(text);
        EscuchadorDarDeAltaLlamada listener= new EscuchadorDarDeAltaLlamada();
        JButton submit= new JButton("Enviar");
        submit.addActionListener(listener);

        altaLlamada.add(submit);

        mid.add(altaLlamada);
        altaLlamada.updateUI();
        mid.updateUI();

    }
    public void VistaMostrarLlamadas(){
        mid.removeAll();
        res.removeAll();
        JPanel mostrarLlamadas= new JPanel();
        mostrarLlamadas.setLayout(new BoxLayout(mostrarLlamadas,BoxLayout.X_AXIS));

        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);



        mostrarLlamadas.add(nif);
        mostrarLlamadas.add(nifT);
        JButton submit= new JButton("Enviar");
        EscuchadorListarLlamadas listener= new EscuchadorListarLlamadas();
        submit.addActionListener(listener);
        mostrarLlamadas.add(submit);

        mid.add(mostrarLlamadas);
        mostrarLlamadas.updateUI();
        mid.updateUI();

    }
    public void VistaMostrarLlamadasEntreFechas(){
        mid.removeAll();
        res.removeAll();
        JPanel mostrarLlamadasEntreFechas= new JPanel();
        mostrarLlamadasEntreFechas.setLayout(new BoxLayout(mostrarLlamadasEntreFechas,BoxLayout.X_AXIS));

        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);
        JLabel fechaIni= new JLabel("Fecha inicio(dd/mm/yyyy): ");
        fecha1= new JTextField(12);
        JLabel fechaFin= new JLabel("          Fecha fin(dd/mm/yyyy): ");
        fecha2= new JTextField(12);

        mostrarLlamadasEntreFechas.add(nif);
        mostrarLlamadasEntreFechas.add(nifT);
        mostrarLlamadasEntreFechas.add(fechaIni);
        mostrarLlamadasEntreFechas.add(fecha1);
        mostrarLlamadasEntreFechas.add(fechaFin);
        mostrarLlamadasEntreFechas.add(fecha2);
        EscuchadorListarLlamadasEntreFechas listener= new EscuchadorListarLlamadasEntreFechas();
        JButton submit= new JButton("Enviar");
        submit.addActionListener(listener);
        mostrarLlamadasEntreFechas.add(submit);

        mid.add(mostrarLlamadasEntreFechas);
        mostrarLlamadasEntreFechas.updateUI();
        mid.updateUI();

    }


    //LISTENERS
    //LISTENERS CLIENTE
    public class EscuchadorCliente implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Nuevo particular")) {
                VistaDarDeAltaParticular();
            }
            if (texto.equals("Nueva empresa")) {
                VistaDarDeAltaEmpresa();
            }
            if (texto.equals("Borrar cliente")) {
                VistaBorrarCliente();
            }
            if (texto.equals("Cambiar tarifa")) {
                VistaCambiarTarifa();
            }
            if (texto.equals("Recuperar datos cliente")) {
                VistaMostrarCliente();
            }
            if (texto.equals("Recuperar listado clientes")) {
                VistaMostrarClientes();
            }
            if (texto.equals("Mostrar listado clientes entre fechas")) {
                VistaMostrarClientesEntreFechas();
            }
        }
    }
    public class EscuchadorCreaParticular implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();

            if (texto.equals("Enviar")) {
                mid.removeAll();
                try {
                    Direccion dir = new Direccion(provinciaT.getText().toString(), poblacionT.getText().toString(), codPosT.getText().toString());
                    DateFormat df = new SimpleDateFormat(patternFechaClientes);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(df.parse(fecha1.getText().toString()));
                    if (r1.isSelected()) {
                        if(controlador.creaParticular(nombreT.getText().toString(), apellidoT.getText().toString(), nifT.getText().toString(), dir,correoT.getText().toString(),cal, FactoriaTarifa.basica()))
                            JOptionPane.showMessageDialog(ventana, "Usuario creado con éxito!");
                        else {
                            VistaDarDeAltaParticular();
                            JOptionPane.showMessageDialog(ventana, "Ha habido un error al crear el usuario");
                        }
                    }

                    else if (r2.isSelected()) {

                        if(controlador.creaParticular(nombreT.getText().toString(), apellidoT.getText().toString(), nifT.getText().toString(), dir,correoT.getText().toString(),cal, FactoriaTarifa.tarde(FactoriaTarifa.basica()))){
                            JOptionPane.showMessageDialog(ventana, "Usuario creado con éxito!");
                        } else{
                            VistaDarDeAltaParticular();
                            JOptionPane.showMessageDialog(ventana, "Ha habido un error al crear el usuario");
                        }
                    }

                    else if (r3.isSelected()){
                        if(controlador.creaParticular(nombreT.getText().toString(), apellidoT.getText().toString(), nifT.getText().toString(), dir,correoT.getText().toString(),cal, FactoriaTarifa.domingo(FactoriaTarifa.basica()))){
                            JOptionPane.showMessageDialog(ventana, "Usuario creado con éxito!");
                        } else {
                            VistaDarDeAltaParticular();
                            JOptionPane.showMessageDialog(ventana, "Ha habido un error al crear el usuario");
                        }
                    }else if (r4.isSelected()){
                        if(controlador.creaParticular(nombreT.getText().toString(),apellidoT.getText(), nifT.getText().toString(), dir,correoT.getText().toString(),cal, FactoriaTarifa.domingo(FactoriaTarifa.tarde(FactoriaTarifa.basica())))){
                            JOptionPane.showMessageDialog(ventana, "Usuario creado con éxito!");
                        }
                        else{
                            VistaDarDeAltaEmpresa();
                            JOptionPane.showMessageDialog(ventana, "Ha habido un error al crear el usuario");
                        }

                    } else {
                        VistaDarDeAltaParticular();
                        JOptionPane.showMessageDialog(ventana, "Selecciona un tipo de tarifa");
                    }

                } catch (ParseException ex) {
                    VistaDarDeAltaParticular();
                    JOptionPane.showMessageDialog(ventana, "La fecha introducida es incorrecta");
                } catch (ExcepcionClienteYaRegistrado ex) {
                    VistaDarDeAltaParticular();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());
                }
            }
        }
    }
    public class EscuchadorCreaEmpresa implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                mid.removeAll();
                try {
                    Direccion dir = new Direccion(provinciaT.getText().toString(), poblacionT.getText().toString(), codPosT.getText().toString());
                    DateFormat df = new SimpleDateFormat(patternFechaClientes);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(df.parse(fecha1.getText()));
                    if (r1.isSelected()) {
                        if(controlador.creaEmpresa(nombreT.getText().toString(), nifT.getText().toString(), dir,correoT.getText().toString(),cal, FactoriaTarifa.basica()))
                            JOptionPane.showMessageDialog(ventana, "Usuario creado con éxito!");
                        else{
                            JOptionPane.showMessageDialog(ventana, "Ha habido un error al crear el usuario");
                            VistaDarDeAltaEmpresa();
                        }

                    }

                    else if (r2.isSelected()) {

                        if(controlador.creaEmpresa(nombreT.getText().toString(), nifT.getText().toString(), dir,correoT.getText().toString(),cal, FactoriaTarifa.tarde(FactoriaTarifa.basica()))){
                            JOptionPane.showMessageDialog(ventana, "Usuario creado con éxito!");
                        }
                        else{
                            VistaDarDeAltaEmpresa();
                            JOptionPane.showMessageDialog(ventana, "Ha habido un error al crear el usuario");
                        }

                    }
                    else if (r3.isSelected()){
                        if(controlador.creaEmpresa(nombreT.getText().toString(), nifT.getText().toString(), dir,correoT.getText().toString(),cal, FactoriaTarifa.domingo(FactoriaTarifa.basica()))){
                            JOptionPane.showMessageDialog(ventana, "Usuario creado con éxito!");
                        }
                        else{
                            VistaDarDeAltaEmpresa();
                            JOptionPane.showMessageDialog(ventana, "Ha habido un error al crear el usuario");
                        }

                    }else if (r4.isSelected()){
                        if(controlador.creaEmpresa(nombreT.getText().toString(), nifT.getText().toString(), dir,correoT.getText().toString(),cal, FactoriaTarifa.domingo(FactoriaTarifa.tarde(FactoriaTarifa.basica())))){
                            JOptionPane.showMessageDialog(ventana, "Usuario creado con éxito!");
                        }
                        else{
                            VistaDarDeAltaEmpresa();
                            JOptionPane.showMessageDialog(ventana, "Ha habido un error al crear el usuario");
                        }

                    }else{
                        VistaDarDeAltaEmpresa();
                        JOptionPane.showMessageDialog(ventana, "Selecciona un tipo de tarifa");
                    }
                } catch (ParseException ex) {
                    VistaDarDeAltaEmpresa();
                    JOptionPane.showMessageDialog(ventana, "La fecha introducida es incorrecta");

                } catch (ExcepcionClienteYaRegistrado ex) {
                    VistaDarDeAltaEmpresa();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());
                }
            }
        }
    }
    public class EscuchadorBorrarCliente implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                mid.removeAll();
                try {
                    if(controlador.borrarCliente(nifT.getText().toString()))
                        JOptionPane.showMessageDialog(ventana, "Cliente borrado con éxito");

                } catch (ExcepcionClienteNoEncontrado ex) {
                    VistaBorrarCliente();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());
                }
            }
        }
    }

    public class EscuchadorCambiarTarifa implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                mid.removeAll();
                try {
                   String nif= nifT.getText();
                   Cliente cli=controlador.mostrarDatos(nif);
                   Tarifa tar= cli.getTarifa();
                   if(r1.isSelected()){
                       controlador.cambiarTarifa(nifT.getText(),0);

                       JOptionPane.showMessageDialog(ventana, "Tarifa del cliente: "+cli.getNombre()+" cambiada a básica");
                   }else if (r2.isSelected()){
                       controlador.cambiarTarifa(nifT.getText(),1);

                       JOptionPane.showMessageDialog(ventana, "Tarifa del cliente: "+cli.getNombre()+" cambiada a tarde");
                   } else if (r3.isSelected()) {
                       controlador.cambiarTarifa(nifT.getText(),2);

                       JOptionPane.showMessageDialog(ventana, "Tarifa del cliente: "+cli.getNombre()+" cambiada a domingo");
                   }else if (r4.isSelected()) {
                       controlador.cambiarTarifa(nifT.getText(),3);
                       JOptionPane.showMessageDialog(ventana, "Tarifa del cliente: "+cli.getNombre()+" cambiada a tarde/domingo");
                   }else{
                       VistaCambiarTarifa();
                       JOptionPane.showMessageDialog(ventana, "Selecciona un tipo de tarifa");
                   }

                } catch (ExcepcionClienteNoEncontrado ex) {
                    VistaCambiarTarifa();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());

                }
            }
        }
    }
    public class EscuchadorMostrarClientes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Mostrar listado clientes")) {
                mid.removeAll();
                try {
                    Collection<Cliente> col = controlador.mostrarListaClientes();
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Cliente cliente : col) {
                        datos.addElement(cliente.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1080, 100));
                    clientes.setVisibleRowCount(30);
                    res.add(scroll);

                    scroll.updateUI();
                    res.updateUI();


                } catch (ExcepcionListaClientesVacia ex) {
                    VistaMostrarClientes();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());
                }
            }
        }

    }
    public class EscuchadorMostrarCliente implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Enviar")) {
                res.removeAll();
                try{
                    JOptionPane.showMessageDialog(ventana, controlador.mostrarDatos(nifT.getText().toString()).toString());
                    res.updateUI();
                } catch (ExcepcionClienteNoEncontrado ex) {
                    VistaMostrarCliente();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());

                }
            }

        }
    }
    public class EscuchadorListarClientesEntreFechas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                mid.removeAll();
                try {
                    DateFormat df = new SimpleDateFormat(patternFechaClientes);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(df.parse(fecha1.getText().toString()));
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(df.parse(fecha2.getText().toString()));

                    Collection<Cliente> col = controlador.mostrarListadoClientesFechas(cal,cal2);
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Cliente cliente : col) {
                        datos.addElement(cliente.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1080, 100));
                    clientes.setVisibleRowCount(30);
                    res.add(scroll);

                    scroll.updateUI();
                    res.updateUI();




                } catch (ExcepcionListaClientesVacia | ExcepcionFechas ex) {
                    VistaMostrarClientesEntreFechas();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());
                } catch (ParseException ex) {
                    VistaMostrarClientesEntreFechas();
                    JOptionPane.showMessageDialog(ventana, "Error al transformar las fechas");
                }
            }
        }

    }
    //LISTENERS FACTURAS---------------------------------------------------------------------------------------------------------------------------------------
    public class EscuchadorFacturas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Emitir facturas")) {
                VistaEmitirFactura();
            }
            if (texto.equals("Mostrar factura")) {
                VistaMostrarFactura();
            }
            if (texto.equals("Mostrar facturas")) {
                VistaMostrarFacturas();
            }
            if (texto.equals("Mostrar facturas entre fechas")) {
                VistaMostrarFacturasEntreFechas();
            }


        }
    }
    public class EscuchadorEmitirFactura implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Enviar")) {
                res.removeAll();
                try{
                    Factura fac= controlador.emitirFacturas(nifT.getText());
                    if(fac==null){
                        VistaEmitirFactura();
                        JOptionPane.showMessageDialog(ventana, "No se ha podido crear la factura.");
                    }
                    JOptionPane.showMessageDialog(ventana, "Factura: "+ fac.getCodFactura()+" creada con éxito.");

                    res.updateUI();
                } catch (ExcepcionClienteNoEncontrado ex) {
                    VistaEmitirFactura();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());

                }
            }

        }
    }
    public class EscuchadorMostrarFacturas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Enviar")) {
                res.removeAll();
                try{
                    Collection<Factura> facs= controlador.mostrarFacturas(nifT.getText().toString());

                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Factura factura : facs) {
                        datos.addElement(factura.toString());
                    }
                    JList<String> facturas = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(facturas);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    facturas.setVisibleRowCount(30);
                    mid.add(scroll);
                    scroll.updateUI();
                    mid.updateUI();
                    res.updateUI();
                } catch (ExcepcionListaFacturasVacia ex) {
                    VistaMostrarFacturas();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());
                }
            }
        }
    }
    public class EscuchadorMostrarFactura implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Enviar")) {
                res.removeAll();
                try{
                    Factura fac= controlador.mostrarFactura(nifT.getText(),Integer.parseInt(codFacT.getText().toString()));
                    JOptionPane.showMessageDialog(ventana, fac.toString());
                    res.updateUI();
                    mid.updateUI();
                } catch (ExcepcionListaFacturasVacia | ExcepcionFacturaNoEncontrada ex) {
                    VistaMostrarFactura();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());

                }
            }
        }
    }
    public class EscuchadorListarFacturasEntreFechas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                mid.removeAll();
                try {
                    DateFormat df = new SimpleDateFormat(patternFechaClientes);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(df.parse(fecha1.getText().toString()));
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(df.parse(fecha2.getText().toString()));

                    Collection<Factura> col = controlador.mostrarFacturasFechas(nifT.getText().toString(),cal,cal2);
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Factura factura : col) {
                        datos.addElement(factura.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1080, 100));
                    clientes.setVisibleRowCount(30);
                    res.add(scroll);

                    scroll.updateUI();
                    res.updateUI();

                } catch (ExcepcionFechas | ExcepcionClienteNoEncontrado | ExcepcionListaFacturasVacia ex) {
                    VistaMostrarFacturasEntreFechas();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());
                } catch (ParseException ex) {
                    VistaMostrarFacturasEntreFechas();
                    JOptionPane.showMessageDialog(ventana, "Error al transformar las fechas");
                }
            }
        }

    }
    //LISTENERS LLAMADAS


    public class EscuchadorLlamada implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Añadir llamada")) {
                VistaDarDeAltaLLamada();
            }
            if (texto.equals("Listar llamadas")) {
                VistaMostrarLlamadas();
            }
            if (texto.equals("Listar llamadas entre fechas")) {
                VistaMostrarLlamadasEntreFechas();
            }

        }
    }

    public class EscuchadorDarDeAltaLlamada implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                mid.removeAll();
                try {
                    DateFormat df = new SimpleDateFormat(patternFecha);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(df.parse(fecha1.getText().toString()));
                    controlador.darDeAltaLlamada(nifT.getText().toString(), telefonoT.getText().toString(), cal, Integer.parseInt(durT.getText().toString()));
                    JOptionPane.showMessageDialog(ventana, "Llamada creada con éxito");


                } catch (ParseException ex) {
                    VistaDarDeAltaLLamada();
                    JOptionPane.showMessageDialog(ventana, "Error al convertir fecha");
                } catch (ExcepcionClienteNoEncontrado ex) {
                    VistaDarDeAltaLLamada();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());
                }
            }

        }
    }
    public class EscuchadorListarLlamadas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                mid.removeAll();
                try {
                    List<Llamada> col = controlador.listarLlamadas(nifT.getText().toString());
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Llamada llamada : col) {
                        datos.addElement(llamada.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1080, 100));
                    clientes.setVisibleRowCount(30);
                    res.add(scroll);

                    scroll.updateUI();
                    res.updateUI();

                } catch (ExcepcionListaLlamadasVacia ex) {
                    VistaMostrarLlamadas();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());
                }
            }
        }

    }

    public class EscuchadorListarLlamadasEntreFechas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                mid.removeAll();
                try {
                    DateFormat df = new SimpleDateFormat(patternFechaClientes);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(df.parse(fecha1.getText().toString()));
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(df.parse(fecha2.getText().toString()));

                    Collection<Llamada> col = controlador.mostrarListadoLlamadasFechas(nifT.getText().toString(),cal,cal2);
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Llamada llamada : col) {
                        datos.addElement(llamada.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1080, 100));
                    clientes.setVisibleRowCount(30);
                    res.add(scroll);

                    scroll.updateUI();
                    res.updateUI();


                } catch (ExcepcionFechas | ExcepcionListaLlamadasVacia | ExcepcionClienteNoEncontrado ex) {
                    VistaMostrarLlamadasEntreFechas();
                    JOptionPane.showMessageDialog(ventana, ex.getMessage());
                } catch (ParseException ex) {
                    VistaMostrarLlamadasEntreFechas();
                    JOptionPane.showMessageDialog(ventana, "Error al transformar las fechas");
                }
            }
        }

    }

    public class EscuchadorMenu implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Clientes")) {


                VistaClientes();
            }
            if (texto.equals("Llamadas")) {


                VistaLlamadas();
            }
            if (texto.equals("Facturas")) {


                VistaFacturas();
            }
        }
    }
}
