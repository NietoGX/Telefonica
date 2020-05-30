package Swing;

import InterfazSwing.Controlador.Controlador;
import InterfazSwing.Controlador.ImplementaciónControlador;
import InterfazSwing.Modelo.ImplementacionModelo;
import InterfazSwing.Vista.ImplementacionVista;
import Swing.Controlador.ControladorEmpresa;
import Swing.Controlador.ImplementacionControlador;
import Swing.Modelo.EmpresaTelefonia;
import Swing.Vista.Vista;

public class Main {
    public static void main(String args[]) {
        EmpresaTelefonia modelo= new EmpresaTelefonia();

        Vista vista = new Vista();
        ImplementacionControlador controlador = new ImplementacionControlador();

        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
//        modelo.cargarDatos();
        vista.iniciaGUI();
    }
}
