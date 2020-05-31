package test;

import Swing.Modelo.ImplementacionModelo;
import datos.*;
import excepciones.ExcepcionClienteNoEncontrado;
import es.uji.www.GeneradorDatosINE;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tarifa.FactoriaTarifa;
import tarifa.Tarifa;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmpresaTelefoniaTest {
    private static GeneradorDatosINE generador = new GeneradorDatosINE();
    private static ImplementacionModelo modeloEmpresa;
    private static Set<String> nifs = new HashSet<>();
    private Random random = new Random();

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
    void altaCliente(String nombre, String apellido, String nif, int edad) {
        String provincia = generador.getProvincia();
        Direccion direccion = new Direccion(provincia, generador.getPoblacion(provincia), Integer.toString(random.nextInt(52999)));

        String correo = nombre + edad + "@uji.es";
        FactoriaTarifa factoriaTarifa = null;
        Tarifa tarifa = FactoriaTarifa.basica();

        if (random.nextBoolean()) {
            Particular particular = new Particular(nombre, apellido, nif, direccion, correo, Calendar.getInstance(), tarifa);
            assertThat(particular, instanceOf(Cliente.class));
            modeloEmpresa.clientes.put(particular.getNif(), particular);
            assertEquals(modeloEmpresa.clientes.get(particular.getNif()), particular);
        } else {
            Empresa empresa = new Empresa(nombre, nif, direccion, correo, Calendar.getInstance(), tarifa);
            assertThat(empresa, instanceOf(Cliente.class));
            modeloEmpresa.clientes.put(empresa.getNif(), empresa);
            assertEquals(modeloEmpresa.clientes.get(empresa.getNif()), empresa);
        }

        nifs.add(nif);
    }

    @Before
    @Test
    @Order(2)
    void altaLlamadas() throws ExcepcionClienteNoEncontrado {
        for (String nif : nifs) {
            int size = modeloEmpresa.llamadas.size();
            String numDestino = Integer.toString(random.nextInt(999999999));
            modeloEmpresa.darDeAltaLlamada(nif, numDestino, Calendar.getInstance(), random.nextInt(3600));
            assertEquals(modeloEmpresa.llamadas.size(), size+1);
        }
    }

    @Test
    @Order(3)
    void emitirFacturas() throws ExcepcionClienteNoEncontrado {
        for (String nif : nifs) {
            int size = modeloEmpresa.facturas.size(); size++;
            modeloEmpresa.emitirFacturas(nif);
            assertEquals(modeloEmpresa.facturas.size(), size);
        }
    }

    @Test
    @Order(4)
    void borrarCliente() throws ExcepcionClienteNoEncontrado {
        for (String nif : nifs) {
            assertThat(modeloEmpresa.clientes.get(nif), instanceOf(Cliente.class));
            modeloEmpresa.borrarCliente(nif);
            assertNull(modeloEmpresa.clientes.get(nif));
        }
    }
}