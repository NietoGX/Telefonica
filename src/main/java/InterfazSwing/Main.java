package InterfazSwing;
import InterfazSwing.Controlador.ImplementaciónControlador;
import InterfazSwing.Modelo.ImplementacionModelo;
import InterfazSwing.Vista.ImplementacionVista;

public class Main {
    public static void main(String args[]) {
        ImplementaciónControlador controlador= new ImplementaciónControlador();
        ImplementacionVista vista = new ImplementacionVista();
        ImplementacionModelo modelo = new ImplementacionModelo();

        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        modelo.cargarDatos();
        vista.iniciaGUI();
    }
}
