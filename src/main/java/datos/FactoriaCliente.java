package datos;

import tarifa.Tarifa;

import java.util.Calendar;
import java.util.Set;

public class FactoriaCliente {

    public static Cliente empresa(String nombre, String nif, Direccion direccion, String correo, Calendar fechaAlta, Tarifa tarifa) {
        return new Empresa(nombre, nif, direccion, correo, fechaAlta, tarifa);
    }

    public static Cliente particular(String nombre, String apellido, String nif, Direccion direccion, String correo, Calendar fechaAlta, Tarifa tarifa) {
        return new Particular(nombre, apellido, nif, direccion, correo, fechaAlta, tarifa);
    }

}
