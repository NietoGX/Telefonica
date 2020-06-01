package test;

import datos.*;
import es.uji.www.GeneradorDatosINE;
import Swing.Modelo.ImplementacionModelo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tarifa.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FactoriaTest {
    private static GeneradorDatosINE generador = new GeneradorDatosINE();
    private Random random = new Random();
    private static ImplementacionModelo modeloEmpresa;
    private Set<Cliente> clientes = new HashSet<>();

    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(generador.getNombre(), generador.getApellido(), generador.getNIF(), generador.getEdad()),
                Arguments.of(generador.getNombre(), generador.getApellido(), generador.getNIF(), generador.getEdad()),
                Arguments.of(generador.getNombre(), generador.getApellido(), generador.getNIF(), generador.getEdad()),
                Arguments.of(generador.getNombre(), generador.getApellido(), generador.getNIF(), generador.getEdad())
        );
    }

    @BeforeAll
    @Test
    static void inicializacion() {
        modeloEmpresa = new ImplementacionModelo();
    }

    @ParameterizedTest
    @MethodSource("data")
    @Order(1)
    void clientes(String nombre, String apellido, String nif, int edad) {
        String provincia = generador.getProvincia();
        Direccion direccion = new Direccion(provincia, generador.getPoblacion(provincia), Integer.toString(random.nextInt(52999)));

        String correo = nombre + edad + "@uji.es";
        FactoriaTarifa factoriaTarifa = null;
        Tarifa tarifa = FactoriaTarifa.basica();

        if (random.nextBoolean()) {
            Cliente cliente = FactoriaCliente.particular(nombre, apellido, nif, direccion, correo, Calendar.getInstance(), tarifa);
            Particular particular = new Particular(nombre, apellido, nif, direccion, correo, Calendar.getInstance(), tarifa);

            assertEquals(cliente.getNombre(), particular.getNombre());
            assertEquals(cliente.getNif(), particular.getNif());
            assertEquals(cliente.getDireccion(), particular.getDireccion());
            assertEquals(cliente.getTarifa(), particular.getTarifa());
        } else {
            Cliente cliente = FactoriaCliente.empresa(nombre, nif, direccion, correo, Calendar.getInstance(), tarifa);
            Empresa empresa = new Empresa(nombre, nif, direccion, correo, Calendar.getInstance(), tarifa);

            assertEquals(cliente.getNombre(), empresa.getNombre());
            assertEquals(cliente.getNif(), empresa.getNif());
            assertEquals(cliente.getDireccion(), empresa.getDireccion());
            assertEquals(cliente.getTarifa(), empresa.getTarifa());
        }

    }

    @Test
    @Order (2)
    public void tarifas() {
        // Tarifa Basica
        Tarifa tarifa = FactoriaTarifa.basica();
        TarifaBasica tarifaBasica = new TarifaBasica();
        assertEquals(tarifa.toString(), tarifaBasica.toString());

        // Tarifa Tarde
        tarifa = FactoriaTarifa.tarde(tarifa);
        TarifaTarde tarifaTarde = new TarifaTarde(tarifaBasica);
        assertEquals(tarifa.toString(), tarifaTarde.toString());

        // Tarifa Tarde y Domingo
        tarifa = FactoriaTarifa.domingo(tarifa);
        TarifaDomingo tarifaTardeDomingo = new TarifaDomingo(tarifaTarde);
        assertEquals(tarifa.toString(), tarifaTardeDomingo.toString());

        // Tarifa Domingo
        tarifa = FactoriaTarifa.basica();
        tarifa = FactoriaTarifa.domingo(tarifa);
        TarifaDomingo tarifaDomingo = new TarifaDomingo(tarifaBasica);
        assertEquals(tarifa.toString(), tarifaDomingo.toString());


    }
}
