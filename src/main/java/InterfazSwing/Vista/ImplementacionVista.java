package InterfazSwing.Vista;

import InterfazSwing.Controlador.Controlador;
import InterfazSwing.Modelo.Modelo;
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
import java.util.Calendar;
import java.util.Collection;

public class ImplementacionVista {
    private Controlador controlador;
    private Modelo modelo;
    private JFrame frame = null;
    Container contenedor = null;
    JPanel panelContenedor;
    JPanel panelButton;
    JPanel panelFinal = new JPanel();
    JTextField nif = null;
    JTextField nombre = null;
    JTextField apellido = null;
    JTextField codPos = null;
    JTextField pob = null;
    JTextField prov = null;
    JTextField correo = null;
    JTextField dia = null;
    JTextField mes = null;
    JTextField año = null;
    JTextField tarifa = null;
    JTextField dia2 = null;
    JTextField mes2 = null;
    JTextField año2 = null;
    JTextField telf = null;
    JTextField codFac = null;
    JTextField hora = null;
    JTextField minuto = null;
    JTextField dur = null;
    JTextField tipoTar;
    JButton submit = null;

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public ImplementacionVista() {
    }

    public void iniciaGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI();
            }
        });
    }
//------------------------------------------------MENÚ PRINCIPAL----------------------------------------
    public void GUI() {
        frame = new JFrame("Telefónica");
        contenedor = frame.getContentPane();
        MainListener escuchador = new MainListener();
        JPanel panelSuperior = new JPanel();
        JButton boton = new JButton("Gestionar clientes");
        boton.addActionListener(escuchador);
        panelSuperior.add(boton);
        boton = new JButton("Gestionar llamadas");
        boton.addActionListener(escuchador);
        panelSuperior.add(boton);
        boton = new JButton("Gestionar facturas");
        boton.addActionListener(escuchador);
        panelSuperior.add(boton);
        contenedor.add(panelSuperior, BorderLayout.NORTH);
        panelContenedor = new JPanel();
        panelContenedor.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panelSuperior.setPreferredSize(new Dimension(1080, 40));
        contenedor.add(panelContenedor, BorderLayout.CENTER);
        frame.setSize(1500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
    //------------------------------------------------MENÚ CLIENTE----------------------------------------
    public void VistaClientes() {
        panelContenedor.removeAll();
        JPanel cliente = new JPanel();
        cliente.setLayout(new BoxLayout(cliente, BoxLayout.LINE_AXIS));
        ClienteListener listener = new ClienteListener();
        JButton boton = new JButton("Nuevo particular");
        boton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        boton.addActionListener(listener);
        cliente.add(boton);
        boton = new JButton("Nueva empresa");
        boton.addActionListener(listener);
        cliente.add(boton);
        boton = new JButton("Borrar cliente");
        boton.addActionListener(listener);
        cliente.add(boton);
        boton = new JButton("Cambiar tarifa");
        boton.addActionListener(listener);
        cliente.add(boton);
        boton = new JButton("Recuperar datos cliente");
        boton.addActionListener(listener);
        cliente.add(boton);
        boton = new JButton("Recuperar listado clientes");
        boton.addActionListener(listener);
        cliente.add(boton);
        boton = new JButton("Mostrar listado clientes entre fechas");
        boton.addActionListener(listener);
        cliente.add(boton);





        panelContenedor.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panelContenedor.add(cliente, BorderLayout.NORTH);
        panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(1200, 600));
        panelContenedor.add(panelButton, BorderLayout.CENTER);
        panelContenedor.updateUI();
    }
    private void GUIDarAltaParticular() {
        panelButton.removeAll();

        JPanel panelEmpresa = new JPanel();


        panelButton.add(panelEmpresa);

        JPanel panelNombre = new JPanel();
        nombre = new JTextField(20);
        JLabel nombreLabel = new JLabel("Nombre: ");
        panelNombre.add(nombreLabel);
        panelNombre.add(nombre);

        panelButton.add(panelNombre);

        JPanel panelApellido = new JPanel();
        apellido = new JTextField(20);
        JLabel apellidoLabel = new JLabel("Apellido: ");
        panelApellido.add(apellidoLabel);
        panelApellido.add(apellido);

        panelButton.add(panelApellido);

        JPanel panelNif = new JPanel();
        nif = new JTextField(8);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelButton.add(panelNif);

        JPanel panelDireccion = new JPanel();
        codPos = new JTextField(5);
        prov = new JTextField(20);
        pob = new JTextField(30);
        JLabel direccionLabel = new JLabel("Dirección: ");
        JLabel codPosLabel = new JLabel("Código Postal: ");
        JLabel provLabel = new JLabel("Provincia: ");
        JLabel pobLabel = new JLabel("Población: ");

        panelDireccion.add(direccionLabel);
        panelDireccion.add(codPosLabel);
        panelDireccion.add(codPos);
        panelDireccion.add(provLabel);
        panelDireccion.add(prov);
        panelDireccion.add(pobLabel);
        panelDireccion.add(pob);

        panelButton.add(panelDireccion);

        JPanel panelCorreo = new JPanel();
        correo = new JTextField(30);
        JLabel correoLabel = new JLabel("Correo: ");
        panelCorreo.add(correoLabel);
        panelCorreo.add(correo);

        panelButton.add(panelCorreo);

        JPanel panelFecha = new JPanel();
        año = new JTextField(4);
        mes = new JTextField(2);
        dia = new JTextField(2);
        JLabel fechaLabel = new JLabel("Fecha de alta: ");
        JLabel añoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        panelFecha.add(fechaLabel);
        panelFecha.add(añoLabel);
        panelFecha.add(año);
        panelFecha.add(mesLabel);
        panelFecha.add(mes);
        panelFecha.add(diaLabel);
        panelFecha.add(dia);

        panelButton.add(panelFecha);

        JPanel panelTarifa = new JPanel();
        tarifa = new JTextField(5);
        JLabel tarifaLabel = new JLabel("Importe tarifa: ");
        panelTarifa.add(tarifaLabel);
        panelTarifa.add(tarifa);

        panelButton.add(panelTarifa);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        EscuchadorDarAltaParticular escuchadorDarAlta = new EscuchadorDarAltaParticular();
        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        submit.addActionListener(escuchadorDarAlta);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelTarifa.updateUI();
        panelFecha.updateUI();
        panelCorreo.updateUI();
        panelDireccion.updateUI();
        panelApellido.updateUI();
        panelNombre.updateUI();
        panelNif.updateUI();
        panelEmpresa.updateUI();
        panelButton.updateUI();
    }
    private void GUIDarAltaEmpresa() {
        panelButton.removeAll();

        JPanel panelEmpresa = new JPanel();


        panelButton.add(panelEmpresa);

        JPanel panelNombre = new JPanel();
        nombre = new JTextField(20);
        JLabel nombreLabel = new JLabel("Nombre: ");
        panelNombre.add(nombreLabel);
        panelNombre.add(nombre);

        panelButton.add(panelNombre);


        JPanel panelNif = new JPanel();
        nif = new JTextField(8);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelButton.add(panelNif);

        JPanel panelDireccion = new JPanel();
        codPos = new JTextField(5);
        prov = new JTextField(20);
        pob = new JTextField(30);
        JLabel direccionLabel = new JLabel("Dirección: ");
        JLabel codPosLabel = new JLabel("Código Postal: ");
        JLabel provLabel = new JLabel("Provincia: ");
        JLabel pobLabel = new JLabel("Población: ");

        panelDireccion.add(direccionLabel);
        panelDireccion.add(codPosLabel);
        panelDireccion.add(codPos);
        panelDireccion.add(provLabel);
        panelDireccion.add(prov);
        panelDireccion.add(pobLabel);
        panelDireccion.add(pob);

        panelButton.add(panelDireccion);

        JPanel panelCorreo = new JPanel();
        correo = new JTextField(30);
        JLabel correoLabel = new JLabel("Correo: ");
        panelCorreo.add(correoLabel);
        panelCorreo.add(correo);

        panelButton.add(panelCorreo);

        JPanel panelFecha = new JPanel();
        año = new JTextField(4);
        mes = new JTextField(2);
        dia = new JTextField(2);
        JLabel fechaLabel = new JLabel("Fecha de alta: ");
        JLabel añoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        panelFecha.add(fechaLabel);
        panelFecha.add(añoLabel);
        panelFecha.add(año);
        panelFecha.add(mesLabel);
        panelFecha.add(mes);
        panelFecha.add(diaLabel);
        panelFecha.add(dia);

        panelButton.add(panelFecha);

        JPanel panelTarifa = new JPanel();
        tarifa = new JTextField(5);
        JLabel tarifaLabel = new JLabel("Importe tarifa: ");
        panelTarifa.add(tarifaLabel);
        panelTarifa.add(tarifa);

        panelButton.add(panelTarifa);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        EscuchadorDarAltaEmpresa escuchadorDarAlta = new EscuchadorDarAltaEmpresa();
        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        submit.addActionListener(escuchadorDarAlta);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelTarifa.updateUI();
        panelFecha.updateUI();
        panelCorreo.updateUI();
        panelDireccion.updateUI();
        panelNombre.updateUI();
        panelNif.updateUI();
        panelEmpresa.updateUI();
        panelButton.updateUI();
    }

    private void GUIBorrarCliente() {
        panelButton.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(8);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelButton.add(panelNif);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorBorrarCliente escuchadorBorrarCliente = new EscuchadorBorrarCliente();
        submit.addActionListener(escuchadorBorrarCliente);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelNif.updateUI();
        panelButton.updateUI();

    }

    private void GUICambiarTarifa() {
        panelButton.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(8);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelButton.add(panelNif);

        JPanel panelTipoTar = new JPanel();
        JLabel tipoTarLabel = new JLabel("Tarifa	(0 Básica) (1 Domingo) (2 Tardes): ");
        tipoTar = new JTextField(5);
        panelTipoTar.add(tipoTarLabel);
        panelTipoTar.add(tipoTar);

        panelButton.add(panelTipoTar);

        JPanel panelTarifa = new JPanel();
        JLabel tarifaLabel = new JLabel("Importe: ");
        tarifa = new JTextField(5);
        panelTarifa.add(tarifaLabel);
        panelTarifa.add(tarifa);

        panelButton.add(panelTarifa);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorCambiarTarifa escuchadorCambiarTarifa = new EscuchadorCambiarTarifa();
        submit.addActionListener(escuchadorCambiarTarifa);
        panelSubmit.add(submit);

        panelButton.add(submit);

        panelTipoTar.updateUI();
        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelTarifa.updateUI();
        panelNif.updateUI();
        panelButton.updateUI();
    }

    private void GUIRecuperarCliente() {
        panelButton.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(8);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelButton.add(panelNif);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarCliente escuchadorRecuperarCliente = new EscuchadorRecuperarCliente();
        submit.addActionListener(escuchadorRecuperarCliente);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelSubmit.updateUI();
        panelEspacio.updateUI();
        panelNif.updateUI();
        panelButton.updateUI();
    }

    private void GUIRecuperarListado() {
        panelButton.removeAll();

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Recuperar");
        EscuchadorRecuperarTodos escuchadorTodos = new EscuchadorRecuperarTodos();
        submit.addActionListener(escuchadorTodos);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelButton.updateUI();
    }

    private void GUIRecuperarListadoEntreFechas() {
        panelButton.removeAll();

        JPanel panelFecha = new JPanel();
        año = new JTextField(4);
        mes = new JTextField(2);
        dia = new JTextField(2);
        JLabel fechaLabel = new JLabel("Fecha de inicio: ");
        JLabel añoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        panelFecha.add(fechaLabel);
        panelFecha.add(añoLabel);
        panelFecha.add(año);
        panelFecha.add(mesLabel);
        panelFecha.add(mes);
        panelFecha.add(diaLabel);
        panelFecha.add(dia);

        panelButton.add(panelFecha);

        JPanel panelFecha2 = new JPanel();
        año2 = new JTextField(4);
        mes2 = new JTextField(2);
        dia2 = new JTextField(2);
        JLabel fechaLabel2 = new JLabel("Fecha de fin: ");
        JLabel añoLabel2 = new JLabel("Año: ");
        JLabel mesLabel2 = new JLabel("Mes(numérico): ");
        JLabel diaLabel2 = new JLabel("Día: ");
        panelFecha2.add(fechaLabel2);
        panelFecha2.add(añoLabel2);
        panelFecha2.add(año2);
        panelFecha2.add(mesLabel2);
        panelFecha2.add(mes2);
        panelFecha2.add(diaLabel2);
        panelFecha2.add(dia2);

        panelButton.add(panelFecha2);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodosEntreFechas escuchadorFechas = new EscuchadorRecuperarTodosEntreFechas();
        submit.addActionListener(escuchadorFechas);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelFecha2.updateUI();
        panelButton.updateUI();
    }
    //------------------------------------------------MENÚ LLAMADA----------------------------------------
    private void GUILlamada() {
        JPanel menu = new JPanel();
        panelContenedor.removeAll();
        EscuchadorLlamada escuchador = new EscuchadorLlamada();
        menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
        JButton boton = new JButton("Dar alta llamada");
        boton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Listar llamadas cliente");
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Listar llamadas cliente entre fechas");

        boton.addActionListener(escuchador);
        menu.add(boton);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelContenedor.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panelContenedor.add(menu, BorderLayout.NORTH);
        panelContenedor.add(panelEspacio);
        panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(1200, 600));
        panelContenedor.add(panelButton, BorderLayout.CENTER);
        panelContenedor.updateUI();
    }
    private void GUIDarAltaLlamada() {
        panelButton.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(8);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelButton.add(panelNif);

        JPanel panelTelf = new JPanel();
        telf = new JTextField(12);
        JLabel telfLabel = new JLabel("Teléfono: ");
        panelTelf.add(telfLabel);
        panelTelf.add(telf);

        panelButton.add(panelTelf);

        JPanel panelFecha = new JPanel();
        año = new JTextField(4);
        mes = new JTextField(2);
        dia = new JTextField(2);
        hora = new JTextField(6);
        minuto = new JTextField(6);
        JLabel fechaLabel = new JLabel("Fecha de alta: ");
        JLabel añoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        JLabel horaLabel = new JLabel("Hora: ");
        JLabel minutoLabel = new JLabel("Minuto: ");
        panelFecha.add(fechaLabel);
        panelFecha.add(añoLabel);
        panelFecha.add(año);
        panelFecha.add(mesLabel);
        panelFecha.add(mes);
        panelFecha.add(diaLabel);
        panelFecha.add(dia);
        panelFecha.add(horaLabel);
        panelFecha.add(hora);
        panelFecha.add(minutoLabel);
        panelFecha.add(minuto);

        panelButton.add(panelFecha);

        JPanel panelDur = new JPanel();
        dur = new JTextField(5);
        JLabel durLabel = new JLabel("Duración: ");
        panelDur.add(durLabel);
        panelDur.add(dur);

        panelButton.add(panelDur);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorDarAltaLlamada escuchadorAltaLlamada = new EscuchadorDarAltaLlamada();
        submit.addActionListener(escuchadorAltaLlamada);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelDur.updateUI();
        panelFecha.updateUI();
        panelTelf.updateUI();
        panelNif.updateUI();
        panelButton.updateUI();

    }

    private void GUIListarLlamadas() {
        panelButton.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(8);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelButton.add(panelNif);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodasLlamadas escuchadorRecuperarTodasLlamadas = new EscuchadorRecuperarTodasLlamadas();
        submit.addActionListener(escuchadorRecuperarTodasLlamadas);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelNif.updateUI();
        panelButton.updateUI();
    }

    private void GUIListarLlamadasEntreFechas() {
        panelButton.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(8);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelButton.add(panelNif);

        JPanel panelFecha = new JPanel();
        año = new JTextField(4);
        mes = new JTextField(2);
        dia = new JTextField(2);
        JLabel fechaLabel = new JLabel("Fecha de inicio: ");
        JLabel añoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        panelFecha.add(fechaLabel);
        panelFecha.add(añoLabel);
        panelFecha.add(año);
        panelFecha.add(mesLabel);
        panelFecha.add(mes);
        panelFecha.add(diaLabel);
        panelFecha.add(dia);

        panelButton.add(panelFecha);

        JPanel panelFecha2 = new JPanel();
        año2 = new JTextField(4);
        mes2 = new JTextField(2);
        dia2 = new JTextField(2);
        JLabel fechaLabel2 = new JLabel("Fecha de fin: ");
        JLabel añoLabel2 = new JLabel("Año: ");
        JLabel mesLabel2 = new JLabel("Mes(numérico): ");
        JLabel diaLabel2 = new JLabel("Día: ");
        panelFecha2.add(fechaLabel2);
        panelFecha2.add(añoLabel2);
        panelFecha2.add(año2);
        panelFecha2.add(mesLabel2);
        panelFecha2.add(mes2);
        panelFecha2.add(diaLabel2);
        panelFecha2.add(dia2);

        panelButton.add(panelFecha2);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodasLlamadasEntreFechas escRTL = new EscuchadorRecuperarTodasLlamadasEntreFechas();
        submit.addActionListener(escRTL);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelNif.updateUI();
        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelFecha2.updateUI();
        panelButton.updateUI();
    }




    //------------------------------------------------MENÚ FACTURA----------------------------------------
    private void GUIFactura() {
        JPanel menu = new JPanel();
        panelContenedor.removeAll();
        menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
        EscuchadorFactura escuchador = new EscuchadorFactura();
        JButton boton = new JButton("Emitir factura");
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Recuperar datos factura");
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Recuperar todas las facturas");
        boton.addActionListener(escuchador);
        menu.add(boton);
        boton = new JButton("Recuperar listado facturas entre fechas");
        boton.addActionListener(escuchador);
        menu.add(boton);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelContenedor.add(menu, BorderLayout.NORTH);
        panelContenedor.add(panelEspacio);
        panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(1200, 600));
        panelContenedor.add(panelButton, BorderLayout.CENTER);
        panelContenedor.updateUI();
    }

    private void GUIEmitirFactura() {
        panelButton.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(8);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelButton.add(panelNif);

        JPanel panelFecha = new JPanel();
        año = new JTextField(4);
        mes = new JTextField(2);
        dia = new JTextField(2);
        JLabel fechaLabel = new JLabel("Fecha: ");
        JLabel añoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        panelFecha.add(fechaLabel);
        panelFecha.add(añoLabel);
        panelFecha.add(año);
        panelFecha.add(mesLabel);
        panelFecha.add(mes);
        panelFecha.add(diaLabel);
        panelFecha.add(dia);

        panelButton.add(panelFecha);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorEmitirFactura escEF = new EscuchadorEmitirFactura();
        submit.addActionListener(escEF);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelNif.updateUI();
        panelButton.updateUI();

    }

    private void GUIRecuperarDatosFactura() {
        panelButton.removeAll();

        JPanel panelcodFac = new JPanel();
        codFac = new JTextField(20);
        JLabel codFacLabel = new JLabel("Código de factura: ");
        panelcodFac.add(codFacLabel);
        panelcodFac.add(codFac);

        panelButton.add(panelcodFac);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarFacturas escRF = new EscuchadorRecuperarFacturas();
        submit.addActionListener(escRF);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelSubmit.updateUI();
        panelEspacio.updateUI();
        panelcodFac.updateUI();
        panelButton.updateUI();
    }

    private void GUIRecuperarTodasFacturas() {
        panelButton.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(8);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelButton.add(panelNif);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodasFacturas escRTF = new EscuchadorRecuperarTodasFacturas();
        submit.addActionListener(escRTF);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelSubmit.updateUI();
        panelEspacio.updateUI();
        panelNif.updateUI();
        panelButton.updateUI();
    }

    private void GUIRecuperarFacturasEntreFechas() {
        panelButton.removeAll();

        JPanel panelNif = new JPanel();
        nif = new JTextField(8);
        JLabel nifLabel = new JLabel("NIF: ");
        panelNif.add(nifLabel);
        panelNif.add(nif);

        panelButton.add(panelNif);

        JPanel panelFecha = new JPanel();
        año = new JTextField(4);
        mes = new JTextField(2);
        dia = new JTextField(2);
        JLabel fechaLabel = new JLabel("Fecha de inicio: ");
        JLabel añoLabel = new JLabel("Año: ");
        JLabel mesLabel = new JLabel("Mes(numérico): ");
        JLabel diaLabel = new JLabel("Día: ");
        panelFecha.add(fechaLabel);
        panelFecha.add(añoLabel);
        panelFecha.add(año);
        panelFecha.add(mesLabel);
        panelFecha.add(mes);
        panelFecha.add(diaLabel);
        panelFecha.add(dia);

        panelButton.add(panelFecha);

        JPanel panelFecha2 = new JPanel();
        año2 = new JTextField(4);
        mes2 = new JTextField(2);
        dia2 = new JTextField(2);
        JLabel fechaLabel2 = new JLabel("Fecha de fin: ");
        JLabel añoLabel2 = new JLabel("Año: ");
        JLabel mesLabel2 = new JLabel("Mes(numérico): ");
        JLabel diaLabel2 = new JLabel("Día: ");
        panelFecha2.add(fechaLabel2);
        panelFecha2.add(añoLabel2);
        panelFecha2.add(año2);
        panelFecha2.add(mesLabel2);
        panelFecha2.add(mes2);
        panelFecha2.add(diaLabel2);
        panelFecha2.add(dia2);

        panelButton.add(panelFecha2);

        JPanel panelEspacio = new JPanel();
        JLabel espacioLabel = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio.add(espacioLabel);

        panelButton.add(panelEspacio);

        JPanel panelSubmit = new JPanel();
        submit = new JButton("Enviar");
        EscuchadorRecuperarTodasFacturasEntreFechas escTFEF = new EscuchadorRecuperarTodasFacturasEntreFechas();
        submit.addActionListener(escTFEF);
        panelSubmit.add(submit);

        panelButton.add(submit);

        JPanel panelEspacio2 = new JPanel();
        JLabel espacioLabel2 = new JLabel(
                "                                                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                                                                                 ");
        panelEspacio2.add(espacioLabel2);

        panelButton.add(panelEspacio2);

        panelNif.updateUI();
        panelEspacio.updateUI();
        panelSubmit.updateUI();
        panelFecha.updateUI();
        panelFecha2.updateUI();
        panelButton.updateUI();
    }



    //------------------------------------------------LISTENERS------------------------------------------
    public class MainListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Gestionar clientes")) {
                VistaClientes();
            }
            if (texto.equals("Gestionar llamadas")) {
                GUILlamada();
            }
            if (texto.equals("Gestionar facturas")) {
                GUIFactura();
            }
        }
    }
    public class ClienteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Nuevo particular")) {
                GUIDarAltaParticular();
            }
            if (texto.equals("Nueva empresa")) {
                GUIDarAltaEmpresa();
            }
            if (texto.equals("Borrar cliente")) {
                GUIBorrarCliente();
            }
            if (texto.equals("Cambiar tarifa")) {
                GUICambiarTarifa();
            }
            if (texto.equals("Recuperar datos cliente")) {
                GUIRecuperarCliente();
            }
            if (texto.equals("Recuperar listado clientes")) {
                GUIRecuperarListado();
            }
            if (texto.equals("Mostrar listado clientes entre fechas")) {
                GUIRecuperarListadoEntreFechas();
            }
        }
    }
    class EscuchadorDarAltaEmpresa implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                Calendar fecha = Calendar.getInstance();
                int añoLocal = Integer.parseInt(año.getText().trim());
                int mesLocal = Integer.parseInt(mes.getText().trim());
                int diaLocal = Integer.parseInt(dia.getText().trim());
                fecha.set(añoLocal, mesLocal, diaLocal);
                String codPosLocal = codPos.getText().trim();
                Direccion dir = new Direccion(prov.getText(), pob.getText(),codPosLocal);
                Tarifa tarifaLocal = null;
                tarifaLocal = FactoriaTarifa.basica();
                try {
                    controlador.creaEmpresa( nombre.getText(), nif.getText(), dir,
                    correo.getText(), fecha, tarifaLocal);
                } catch (ExcepcionClienteYaRegistrado excepcionClienteYaRegistrado) {
                    excepcionClienteYaRegistrado.printStackTrace();
                }
                modelo.guardarDatos();

                JLabel clienteRegistrado = new JLabel("Cliente registrado con éxito");
                panelFinal.add(clienteRegistrado);
                panelButton.add(panelFinal,BorderLayout.CENTER);
                panelFinal.updateUI();
                panelButton.updateUI();
            }
        }
    }
    class EscuchadorDarAltaParticular implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                Calendar fecha = Calendar.getInstance();
                int añoLocal = Integer.parseInt(año.getText().trim());
                int mesLocal = Integer.parseInt(mes.getText().trim());
                int diaLocal = Integer.parseInt(dia.getText().trim());
                fecha.set(añoLocal, mesLocal, diaLocal);
                String codPosLocal = codPos.getText().trim();
                Direccion dir = new Direccion(prov.getText(), pob.getText(),codPosLocal);
                Tarifa tarifaLocal = null;
                tarifaLocal = FactoriaTarifa.basica();
                try {
                    controlador.creaParticular( nombre.getText(), apellido.getText(), nif.getText(), dir,
                    correo.getText(), fecha, tarifaLocal);
                } catch (ExcepcionClienteYaRegistrado excepcionClienteYaRegistrado) {
                    excepcionClienteYaRegistrado.printStackTrace();
                }
                modelo.guardarDatos();

                JLabel clienteRegistrado = new JLabel("Cliente registrado con éxito");
                panelFinal.add(clienteRegistrado);
                panelButton.add(panelFinal,BorderLayout.CENTER);
                panelFinal.updateUI();
                panelButton.updateUI();
            }
        }
    }
    class EscuchadorBorrarCliente implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    controlador.borraCliente(nif.getText());
                    modelo.guardarDatos();
                    JLabel clienteBorrado = new JLabel("Cliente borrado con éxito");
                    panelFinal.add(clienteBorrado);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionClienteNoEncontrado e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                }
            }
        }
    }
    class EscuchadorCambiarTarifa implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            Tarifa tarifaLocal = null;
            tarifaLocal = FactoriaTarifa.basica();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    controlador.cambiaTarifa(nif.getText(), tarifaLocal);
                } catch (ExcepcionClienteNoEncontrado excepcionClienteNoEncontrado) {
                    excepcionClienteNoEncontrado.printStackTrace();
                }
                modelo.guardarDatos();
                panelFinal = new JPanel();
                JLabel tarifaCambiada = new JLabel("Tarifa cambiada con éxito");
                panelFinal.add(tarifaCambiada);
                panelButton.add(panelFinal);
                panelFinal.updateUI();
                panelButton.updateUI();
            }
        }
    }
    class EscuchadorRecuperarCliente implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    JLabel cliente = new JLabel(controlador.recuperarDatosCliente(nif.getText()).toString());
                    JScrollPane scroll = new JScrollPane(cliente);
                    scroll.setPreferredSize(new Dimension(1080, 100));
                    panelFinal.add(scroll);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionClienteNoEncontrado e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                }
            }
        }
    }
    class EscuchadorRecuperarTodos implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Recuperar")) {
                panelFinal.removeAll();
                try {
                    Collection<Cliente> col = controlador.recuperaListadoClientes().values();
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Cliente cliente : col) {
                        datos.addElement(cliente.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    clientes.setVisibleRowCount(30);
                    panelFinal.add(scroll);
                    panelButton.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch ( ExcepcionListaClientesVacia e1) {
                    JLabel clientesNoEncontrados = new JLabel("Clientes no encontrados");
                    panelFinal.add(clientesNoEncontrados);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                }
            }
        }
    }
    class EscuchadorRecuperarTodosEntreFechas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            Calendar fechaInicio = Calendar.getInstance();
            int añoLocal = Integer.parseInt(año.getText().trim());
            int mesLocal = Integer.parseInt(mes.getText().trim());
            int diaLocal = Integer.parseInt(dia.getText().trim());
            fechaInicio.set(añoLocal, mesLocal, diaLocal);
            Calendar fechaFin = Calendar.getInstance();
            int añoLocal2 = Integer.parseInt(año2.getText().trim());
            int mesLocal2 = Integer.parseInt(mes2.getText().trim());
            int diaLocal2 = Integer.parseInt(dia2.getText().trim());
            fechaFin.set(añoLocal2, mesLocal2, diaLocal2);
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Cliente> col = controlador.recuperaListadoClientesEntreFechas(fechaInicio, fechaFin);
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Cliente cliente : col) {
                        datos.addElement(cliente.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    clientes.setVisibleRowCount(30);
                    panelFinal.add(scroll);
                    panelButton.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionListaClientesVacia e1) {
                    JLabel clientesNoEncontrados = new JLabel("Clientes no encontrados");
                    panelFinal.add(clientesNoEncontrados);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionFechas e1) {
                    JLabel clientesNoEncontrados = new JLabel("Fecha no válida");
                    panelFinal.add(clientesNoEncontrados);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                }
            }
        }
    }
    class EscuchadorLlamada implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Dar alta llamada")) {
                GUIDarAltaLlamada();
            }
            if (texto.equals("Listar llamadas cliente")) {
                GUIListarLlamadas();
            }
            if (texto.equals("Listar llamadas cliente entre fechas")) {
                GUIListarLlamadasEntreFechas();
            }
        }
    }
    class EscuchadorDarAltaLlamada implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                Calendar fecha = Calendar.getInstance();
                int añoLocal = Integer.parseInt(año.getText().trim());
                int mesLocal = Integer.parseInt(mes.getText().trim());
                int diaLocal = Integer.parseInt(dia.getText().trim());
                int horaLocal = Integer.parseInt(hora.getText().trim());
                int minLocal = Integer.parseInt(minuto.getText().trim());
                fecha.set(añoLocal, mesLocal, diaLocal, horaLocal, minLocal);
                String telefono = telf.getText().trim();
                int dura = Integer.parseInt(dur.getText().trim());

                Llamada llamada = new Llamada(telefono, fecha, dura);
                try {
                    controlador.darDeAltaLlamada(nif.getText(), llamada);
                    modelo.guardarDatos();

                    JLabel llamadaRegistrada = new JLabel("Llamada registrada con éxito");
                    panelFinal.add(llamadaRegistrada);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionClienteNoEncontrado e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                }
            }
        }
    }
    class EscuchadorRecuperarTodasLlamadas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Llamada> col = controlador.listarLlamadasCliente(nif.getText());
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Llamada llamada : col) {
                        datos.addElement(llamada.toString());
                    }
                    JList<String> llamadas = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(llamadas);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    llamadas.setVisibleRowCount(30);
                    panelFinal.add(scroll);
                    panelButton.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionClienteNoEncontrado e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                }
            }
        }
    }
    class EscuchadorRecuperarTodasLlamadasEntreFechas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            Calendar fechaInicio = Calendar.getInstance();
            int añoLocal = Integer.parseInt(año.getText().trim());
            int mesLocal = Integer.parseInt(mes.getText().trim());
            int diaLocal = Integer.parseInt(dia.getText().trim());
            fechaInicio.set(añoLocal, mesLocal, diaLocal);
            Calendar fechaFin = Calendar.getInstance();
            int añoLocal2 = Integer.parseInt(año2.getText().trim());
            int mesLocal2 = Integer.parseInt(mes2.getText().trim());
            int diaLocal2 = Integer.parseInt(dia2.getText().trim());
            fechaFin.set(añoLocal2, mesLocal2, diaLocal2);
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Llamada> col = controlador.mostrarListadoLlamadasFechas(nif.getText(), fechaInicio, fechaFin);
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Llamada cliente : col) {
                        datos.addElement(cliente.toString());
                    }
                    JList<String> clientes = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(clientes);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    clientes.setVisibleRowCount(30);
                    panelFinal.add(scroll);
                    panelButton.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionFechas e1) {
                    JLabel fechaNoValida = new JLabel("Fecha no válida");
                    panelFinal.add(fechaNoValida);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionListaLlamadasVacia e1) {
                    JLabel listaVacia = new JLabel("Lista vacía");
                    panelFinal.add(listaVacia);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionClienteNoEncontrado e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                }
            }
        }
    }
    class EscuchadorFactura implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            if (texto.equals("Emitir factura")) {
                GUIEmitirFactura();
            }
            if (texto.equals("Recuperar datos factura")) {
                GUIRecuperarDatosFactura();
            }
            if (texto.equals("Recuperar todas las facturas")) {
                GUIRecuperarTodasFacturas();
            }
            if (texto.equals("Recuperar listado facturas entre fechas")) {
                GUIRecuperarFacturasEntreFechas();
            }
        }
    }
    class EscuchadorEmitirFactura implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                Calendar fecha = Calendar.getInstance();
                int añoLocal = Integer.parseInt(año.getText().trim());
                int mesLocal = Integer.parseInt(mes.getText().trim());
                int diaLocal = Integer.parseInt(dia.getText().trim());
                fecha.set(añoLocal, mesLocal, diaLocal);

                try {
                    controlador.emitirFactura(nif.getText(), fecha);
                } catch (ExcepcionClienteNoEncontrado excepcionClienteNoEncontrado) {
                    excepcionClienteNoEncontrado.printStackTrace();
                }
                modelo.guardarDatos();

                JLabel facturaRegistrada = new JLabel("Factura registrada con éxito");
                panelFinal.add(facturaRegistrada);
                panelButton.add(panelFinal);
                panelFinal.updateUI();
                panelButton.updateUI();
            }
        }
    }

    class EscuchadorRecuperarTodasFacturas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Factura> col = controlador.recuperarFacturas(nif.getText());
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Factura factura : col) {
                        datos.addElement(factura.toString());
                    }
                    JList<String> facturas = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(facturas);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    facturas.setVisibleRowCount(30);
                    panelFinal.add(scroll);
                    panelButton.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionListaFacturasVacia e1) {
                    JLabel listaVacia = new JLabel("Lista vacía");
                    panelFinal.add(listaVacia);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionClienteNoEncontrado e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                }
            }
        }
    }

    class EscuchadorRecuperarFacturas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    int codFactura = Integer.parseInt(codFac.getText().trim());
                    JLabel fac = new JLabel(controlador.recuperarDatosFacturaCodigo(codFactura).toString());
                    JScrollPane scroll = new JScrollPane(fac);
                    scroll.setPreferredSize(new Dimension(1080, 100));
                    panelFinal.add(scroll);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionFacturaNoEncontrada e1) {
                    JLabel facturaNoEncontrada = new JLabel("Factura no encontrada");
                    panelFinal.add(facturaNoEncontrada);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                }
            }
        }
    }

    class EscuchadorRecuperarTodasFacturasEntreFechas implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submit = (JButton) e.getSource();
            String texto = submit.getText();
            Calendar fechaInicio = Calendar.getInstance();
            int añoLocal = Integer.parseInt(año.getText().trim());
            int mesLocal = Integer.parseInt(mes.getText().trim());
            int diaLocal = Integer.parseInt(dia.getText().trim());
            fechaInicio.set(añoLocal, mesLocal, diaLocal);
            Calendar fechaFin = Calendar.getInstance();
            int añoLocal2 = Integer.parseInt(año2.getText().trim());
            int mesLocal2 = Integer.parseInt(mes2.getText().trim());
            int diaLocal2 = Integer.parseInt(dia2.getText().trim());
            fechaFin.set(añoLocal2, mesLocal2, diaLocal2);
            if (texto.equals("Enviar")) {
                panelFinal.removeAll();
                try {
                    Collection<Factura> col = controlador.mostrarListadoFacturasFechas(nif.getText(), fechaInicio, fechaFin);
                    DefaultListModel<String> datos = new DefaultListModel<>();
                    for (Factura fac : col) {
                        datos.addElement(fac.toString());
                    }
                    JList<String> facturas = new JList<String>(datos);
                    JScrollPane scroll = new JScrollPane(facturas);
                    scroll.setPreferredSize(new Dimension(1080, 400));
                    facturas.setVisibleRowCount(30);
                    panelFinal.add(scroll);
                    panelButton.add(panelFinal);
                    scroll.updateUI();
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionFechas e1) {
                    JLabel fechaNoValida = new JLabel("Fecha no válida");
                    panelFinal.add(fechaNoValida);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionClienteNoEncontrado e1) {
                    JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
                    panelFinal.add(clienteNoEncontrado);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                } catch (ExcepcionListaFacturasVacia e1) {
                    JLabel listaVacia = new JLabel("Lista vacía");
                    panelFinal.add(listaVacia);
                    panelButton.add(panelFinal);
                    panelFinal.updateUI();
                    panelButton.updateUI();
                }
            }
        }
    }
}


