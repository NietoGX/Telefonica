package Swing;

import Swing.Controlador.ImplementacionControlador;
import Swing.Modelo.ModeloEmpresa;
import Swing.Vista.Vista;

public class Main {
    public static void main(String args[]) {
        ModeloEmpresa modelo= new ModeloEmpresa();

        Vista vista = new Vista();
        ImplementacionControlador controlador = new ImplementacionControlador();

        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
//        modelo.cargarDatos();
        vista.iniciaGUI();
    }
}
