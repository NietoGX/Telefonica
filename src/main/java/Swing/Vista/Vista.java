package Swing.Vista;

import InterfazSwing.Controlador.Controlador;
import InterfazSwing.Modelo.Modelo;
import InterfazSwing.Vista.ImplementacionVista;
import Swing.Controlador.ControladorEmpresa;
import Swing.Modelo.EmpresaTelefonia;
import datos.Factura;
import excepciones.ExcepcionClienteNoEncontrado;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        text.setLayout(new GridLayout(0,1,1,5));
        text.setBackground(Color.DARK_GRAY);

        JLabel nombre= new JLabel("Nombre: ");
        JLabel apellido= new JLabel("Apellido: ");
        JLabel nif= new JLabel("NIF: ");
        JLabel dir= new JLabel("Dirección: ");
        JLabel email= new JLabel("E-mail: ");
        JLabel fecha= new JLabel("Fecha: ");
        JLabel tarifa= new JLabel("Tarifa: ");

        labels.add(nombre);
        labels.add(apellido);
        labels.add(nif);
        labels.add(dir);
        labels.add(email);
        labels.add(fecha);
        labels.add(tarifa);

        nombreT= new JTextField(30);
        apellidoT= new JTextField(30);
        nifT= new JTextField(30);
        dirT= new JTextField(30);
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
        text.add(dirT);
        text.add(correoT);
        text.add(fecha1);
        text.add(radios);



        altaCliente.add(labels,BorderLayout.WEST);
        altaCliente.add(text,BorderLayout.CENTER);

        JButton submit= new JButton("Enviar");
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
        text.setLayout(new GridLayout(0,1,1,5));
        text.setBackground(Color.DARK_GRAY);

        JLabel nombre= new JLabel("Nombre: ");
        JLabel nif= new JLabel("NIF: ");
        JLabel dir= new JLabel("Dirección: ");
        JLabel email= new JLabel("E-mail: ");
        JLabel fecha= new JLabel("Fecha: ");
        JLabel tarifa= new JLabel("Tarifa: ");

        labels.add(nombre);
        labels.add(nif);
        labels.add(dir);
        labels.add(email);
        labels.add(fecha);
        labels.add(tarifa);

        nombreT= new JTextField(30);
        nifT= new JTextField(30);
        dirT= new JTextField(30);
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
        text.add(dirT);
        text.add(correoT);
        text.add(fecha1);
        text.add(radios);

        altaEmpresa.add(labels,BorderLayout.WEST);
        altaEmpresa.add(text,BorderLayout.CENTER);

        JButton submit= new JButton("Enviar");
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
        JButton submit= new JButton("Enviar");
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
        boton.setBackground(Color.gray);
        boton.addActionListener(listener);
        mostrarCLiente.add(boton);

        mid.add(mostrarCLiente);
        mostrarCLiente.updateUI();
        mid.updateUI();
    }
    private void VistaMostrarClientes(){

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
                    JLabel cliente= new JLabel((Icon) controlador.mostrarDatos(nifT.getText().toString()));
                    System.out.println(nifT.getText());
                    JScrollPane scroll = new JScrollPane(cliente);
                    scroll.setPreferredSize(new Dimension(1080, 100));
                    res.add(scroll);
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


}
