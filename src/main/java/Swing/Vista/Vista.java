package Swing.Vista;

import InterfazSwing.Controlador.Controlador;
import InterfazSwing.Modelo.Modelo;
import InterfazSwing.Vista.ImplementacionVista;
import Swing.Controlador.ControladorEmpresa;
import Swing.Modelo.EmpresaTelefonia;
import datos.Cliente;
import datos.Direccion;
import datos.Factura;
import excepciones.*;
import tarifa.TarifaBasica;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

public class Vista {
    private EmpresaTelefonia modelo;
    private ControladorEmpresa controlador;
    private JFrame ventana;
    Container contenedor;
    JPanel menu;
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


    JTextField añoT = null;
    JTextField tarifaT = null;
    JTextField codFacT = null;
    JTextField durT = null;
    JTextField tipoTaT;
    JButton submit = null;







    public void setModelo(EmpresaTelefonia modelo) {
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
    public void MenuPrincipal(){

        JLabel ESPACIO= new JLabel("                                                                ");
        ventana= new JFrame("EmpresaTelefónica");
        contenedor=ventana.getContentPane();
        menu=new JPanel();
        menu.add(ESPACIO);
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
        submenu.setLayout(new BoxLayout(submenu,BoxLayout.Y_AXIS));
        contenedor.add(submenu,BorderLayout.WEST);
        mid= new JPanel();
        res=new JPanel();
        contenedor.add(mid, BorderLayout.CENTER);
        contenedor.add(res,BorderLayout.SOUTH);

        ventana.setSize(1500, 600);
        menu.setBackground(Color.DARK_GRAY);
        submenu.setBackground(Color.DARK_GRAY);
        mid.setBackground(Color.DARK_GRAY);

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);


    }

    private void VistaEmitirFactura() {
        mid.removeAll();
        JPanel emitirFactura= new JPanel();
//        emitirFactura.setAlignmentX(JComponent.CENTER_ALIGNMENT);
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
        JPanel mostrarFactura= new JPanel();
        mostrarFactura.setLayout(new BoxLayout(mostrarFactura,BoxLayout.X_AXIS));

        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);
        JLabel codFac= new JLabel("NIF: ");
        codFacT= new JTextField(12);



        mostrarFactura.add(nif);
        mostrarFactura.add(nifT);
        mostrarFactura.add(codFac);
        mostrarFactura.add(codFacT);
        JButton submit= new JButton("Enviar");

        mostrarFactura.add(submit);

        mid.add(mostrarFactura);
        mostrarFactura.updateUI();
        mid.updateUI();

    }
    private void VistaMostrarFacturasEntreFechas() {
        mid.removeAll();

        JPanel mostrarFacturasEntreFechas= new JPanel();
        mostrarFacturasEntreFechas.setLayout(new BoxLayout(mostrarFacturasEntreFechas,BoxLayout.X_AXIS));


        JLabel fechaIni= new JLabel("Fecha inicio: ");
        fecha1= new JTextField(12);
        JLabel fechaFin= new JLabel("          Fecha fin: ");
        fecha2= new JTextField(12);


        mostrarFacturasEntreFechas.add(fechaIni);
        mostrarFacturasEntreFechas.add(fecha1);
        mostrarFacturasEntreFechas.add(fechaFin);
        mostrarFacturasEntreFechas.add(fecha2);

        JButton submit= new JButton("Enviar");
        mostrarFacturasEntreFechas.add(submit);

        mid.add(mostrarFacturasEntreFechas);
        mostrarFacturasEntreFechas.updateUI();
        mid.updateUI();

    }
    private void VistaDarDeAltaLLamada() {
        mid.removeAll();

        JPanel altaLlamada= new JPanel();
        altaLlamada.setLayout(new BoxLayout(altaLlamada,BoxLayout.X_AXIS));
        JPanel labels= new JPanel();

        labels.setLayout(new GridLayout(0,1,1,15));
        JPanel text= new JPanel();
        text.setLayout(new GridLayout(0,1,1,5));

        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);
        JLabel fechaAlta= new JLabel("Fecha de alta: ");
        fecha1= new JTextField(12);
        JLabel tiempo= new JLabel("Tiempo: ");
        durT= new JTextField(12);

        labels.add(nif);
        labels.add(fechaAlta);
        labels.add(tiempo);

        text.add(nifT);
        text.add(fecha1);
        text.add(durT);



        altaLlamada.add(labels);
        altaLlamada.add(text);
        JButton submit= new JButton("Enviar");

        altaLlamada.add(submit);

        mid.add(altaLlamada);
        altaLlamada.updateUI();
        mid.updateUI();

    }
    public void VistaMostrarLlamadas(){

    }
    public void VistaMostrarLlamadasEntreFechas(){
        mid.removeAll();

        JPanel mostrarLlamadasEntreFechas= new JPanel();
        mostrarLlamadasEntreFechas.setLayout(new BoxLayout(mostrarLlamadasEntreFechas,BoxLayout.X_AXIS));

        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);
        JLabel fechaIni= new JLabel("Fecha inicio: ");
        fecha1= new JTextField(12);
        JLabel fechaFin= new JLabel("          Fecha fin: ");
        fecha2= new JTextField(12);

        mostrarLlamadasEntreFechas.add(nif);
        mostrarLlamadasEntreFechas.add(nifT);
        mostrarLlamadasEntreFechas.add(fechaIni);
        mostrarLlamadasEntreFechas.add(fecha1);
        mostrarLlamadasEntreFechas.add(fechaFin);
        mostrarLlamadasEntreFechas.add(fecha2);

        JButton submit= new JButton("Enviar");
        mostrarLlamadasEntreFechas.add(submit);

        mid.add(mostrarLlamadasEntreFechas);
        mostrarLlamadasEntreFechas.updateUI();
        mid.updateUI();

    }
    private void VistaFacturas() {
        mid.removeAll();
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

        mid.removeAll();
        submenu.add(vistaFactura, BorderLayout.WEST);
        submenu = new JPanel();
        submenu.setBackground(Color.DARK_GRAY);
        mid.add(submenu, BorderLayout.CENTER);
        submenu.updateUI();


    }
    public void VistaClientes() {
        mid.removeAll();
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



        mid.removeAll();
        submenu.add(vistaCliente, BorderLayout.WEST);
        submenu = new JPanel();
        submenu.setBackground(Color.DARK_GRAY);
        mid.add(submenu, BorderLayout.CENTER);
        submenu.updateUI();


    }
    public void VistaLlamadas() {
        mid.removeAll();
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



        mid.removeAll();
        submenu.add(vistaLlamadas, BorderLayout.WEST);
        submenu = new JPanel();
        submenu.setBackground(Color.DARK_GRAY);
        mid.add(submenu, BorderLayout.CENTER);
        submenu.updateUI();


    }
    public void VistaDarDeAltaParticular(){
        mid.removeAll();
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
        JLabel fecha= new JLabel("Fecha: ");
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
        JRadioButton r1= new JRadioButton("Básica");
        JRadioButton r2= new JRadioButton("Noche");
        JRadioButton r3= new JRadioButton("Domingo");
        ButtonGroup tarifaT= new ButtonGroup();
        tarifaT.add(r1);
        tarifaT.add(r2);
        tarifaT.add(r3);

        JPanel radios= new JPanel();
        radios.add(r1);
        radios.add(r2);
        radios.add(r3);

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
        JLabel fecha= new JLabel("Fecha: ");
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
        JRadioButton r1= new JRadioButton("Básica");
        JRadioButton r2= new JRadioButton("Noche");
        JRadioButton r3= new JRadioButton("Domingo");
        ButtonGroup tarifaT= new ButtonGroup();
        tarifaT.add(r1);
        tarifaT.add(r2);
        tarifaT.add(r3);

        JPanel radios= new JPanel();
        radios.add(r1);
        radios.add(r2);
        radios.add(r3);

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
        JPanel cambiarTarifa= new JPanel();
        cambiarTarifa.setLayout(new BoxLayout(cambiarTarifa,BoxLayout.X_AXIS));
        JLabel nif= new JLabel("NIF: ");
        nifT= new JTextField(12);
        cambiarTarifa.add(nif);
        cambiarTarifa.add(nifT);

        JRadioButton r1= new JRadioButton("Básica");
        JRadioButton r2= new JRadioButton("Noche");
        JRadioButton r3= new JRadioButton("Domingo");
        ButtonGroup tarifaT= new ButtonGroup();
        tarifaT.add(r1);
        tarifaT.add(r2);
        tarifaT.add(r3);
        JButton submit= new JButton("Enviar");

        cambiarTarifa.add(r1);
        cambiarTarifa.add(r2);
        cambiarTarifa.add(r3);
        cambiarTarifa.add(submit);
        mid.add(cambiarTarifa);
        cambiarTarifa.updateUI();
        mid.updateUI();
    }

    public void VistaMostrarCliente(){
        mid.removeAll();
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
        JPanel mostrarClienteEntreFechas= new JPanel();
        mostrarClienteEntreFechas.setLayout(new BoxLayout(mostrarClienteEntreFechas,BoxLayout.X_AXIS));

        JLabel fechaIni= new JLabel("Fecha inicio: ");
        fecha1= new JTextField(12);
        JLabel fechaFin= new JLabel("          Fecha fin: ");
        fecha2= new JTextField(12);
        mostrarClienteEntreFechas.add(fechaIni);
        mostrarClienteEntreFechas.add(fecha1);
        mostrarClienteEntreFechas.add(fechaFin);
        mostrarClienteEntreFechas.add(fecha2);

        JButton submit= new JButton("Enviar");
        mostrarClienteEntreFechas.add(submit);

        mid.add(mostrarClienteEntreFechas);
        mostrarClienteEntreFechas.updateUI();
        mid.updateUI();
    }







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
    public class EscuchadorMostrarCliente implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Enviar")) {
                res.removeAll();
                try{
                    System.out.println("PRUEBAAAAAAAAAAA");
//                    JLabel cliente= new JLabel(controlador.mostrarDatos(nifT.getText().toString()).toString());
                    System.out.println(nifT.getText());
//                    JScrollPane scroll = new JScrollPane(cliente);
//                    scroll.setPreferredSize(new Dimension(1080, 100));
//                    res.add(scroll);
                    JOptionPane.showMessageDialog(ventana, controlador.mostrarDatos(nifT.getText().toString()).toString());
//                    panelButton.add(res);
                    res.updateUI();
//                    panelButton.updateUI();
                } catch (ExcepcionClienteNoEncontrado excepcionClienteNoEncontrado) {
                    JOptionPane.showMessageDialog(ventana, "Cliente no encontrado");

                }
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
                    System.out.println("Emitiendo factura");
                    Factura fac= controlador.emitirFacturas(nifT.getText().toString());
                    System.out.println(nifT.getText());
                    JOptionPane.showMessageDialog(ventana, "Factura: "+ fac.getCodFactura()+" creada con éxito.");

//                    panelButton.add(res);
                    res.updateUI();
//                    panelButton.updateUI();
                } catch (ExcepcionClienteNoEncontrado excepcionClienteNoEncontrado) {
                    JOptionPane.showMessageDialog(ventana, "No se ha podido crear la factura debido a que no se ha encontrado el cliente");

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
                    System.out.println("Mostrando facturas");
                    System.out.println(nifT.getText());
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
//                    panelButton.add(res);
                    res.updateUI();
//                    panelButton.updateUI();
                } catch (ExcepcionListaFacturasVacia excepcionClienteNoEncontrado) {
                    JOptionPane.showMessageDialog(ventana, "No se ha podido crear la factura debido a que no se ha encontrado el cliente");

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
                    System.out.println("Mostrando factura");
                    System.out.println(nifT.getText());
                    Factura fac= (Factura) controlador.mostrarFactura(nifT.getText(),Integer.parseInt(codFacT.getText().toString()));
                    JLabel facLabel= new JLabel(fac.toString());
                    mid.add(facLabel);
                    mid.updateUI();
                } catch (ExcepcionListaFacturasVacia excepcionClienteNoEncontrado) {
                    JOptionPane.showMessageDialog(ventana, "No se ha podido encontrar la factura");

                }
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
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(df.parse(fecha1.getText().toString()));
                    if(controlador.creaParticular(nombreT.getText().toString(), apellidoT.getText().toString(), nifT.getText().toString(), dir,correoT.getText().toString(),cal,new TarifaBasica()))
                        JOptionPane.showMessageDialog(ventana, "Usuario creado con éxito!");
                    else{
                        JOptionPane.showMessageDialog(ventana, "Ha habido un error al crear el usuario");
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(ventana, "La fecha introducida es incorrecta");
                } catch (ExcepcionClienteYaRegistrado excepcionClienteYaRegistrado) {
                    JOptionPane.showMessageDialog(ventana, "Cliente ya registrado");
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
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(df.parse(fecha1.getText().toString()));
                    if(controlador.creaEmpresa(nombreT.getText().toString(), nifT.getText().toString(), dir,correoT.getText().toString(),cal,new TarifaBasica()))
                        JOptionPane.showMessageDialog(ventana, "Usuario creado con éxito!");
                    else{
                        JOptionPane.showMessageDialog(ventana, "Ha habido un error al crear el usuario");
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(ventana, "La fecha introducida es incorrecta");
                } catch (ExcepcionClienteYaRegistrado excepcionClienteYaRegistrado) {
                    JOptionPane.showMessageDialog(ventana, "Cliente ya registrado");
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

                } catch (ExcepcionClienteNoEncontrado excepcionClienteNoEncontrado) {
                    JOptionPane.showMessageDialog(ventana, "Cliente no encontrado");
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


                } catch (ExcepcionListaClientesVacia excepcionListaClientesVacia) {
                    JOptionPane.showMessageDialog(ventana, "Lista de clientes vacía");
                }
            }
        }

    }
}
