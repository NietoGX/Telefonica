package test;

import Swing.Modelo.EmpresaTelefonia;
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
    private static EmpresaTelefonia empresaTelefonia;
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
        empresaTelefonia = new EmpresaTelefonia();
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
            empresaTelefonia.clientes.put(particular.getNif(), particular);
            assertEquals(empresaTelefonia.clientes.get(particular.getNif()), particular);
        } else {
            Empresa empresa = new Empresa(nombre, nif, direccion, correo, Calendar.getInstance(), tarifa);
            assertThat(empresa, instanceOf(Cliente.class));
            empresaTelefonia.clientes.put(empresa.getNif(), empresa);
            assertEquals(empresaTelefonia.clientes.get(empresa.getNif()), empresa);
        }

        nifs.add(nif);
    }

    @Before
    @Test
    @Order(2)
    void altaLlamadas() {
        for (String nif : nifs) {
            int size = empresaTelefonia.llamadas.size();
            String numDestino = Integer.toString(random.nextInt(999999999));
            empresaTelefonia.darDeAltaLlamada(nif, numDestino, Calendar.getInstance(), random.nextInt(3600));
            assertEquals(empresaTelefonia.llamadas.size(), size+1);
        }
    }

    @Test
    @Order(3)
    void emitirFacturas() throws ExcepcionClienteNoEncontrado {
        for (String nif : nifs) {
            int size = empresaTelefonia.facturas.size(); size++;
            empresaTelefonia.emitirFacturas(nif);
            assertEquals(empresaTelefonia.facturas.size(), size);
        }
    }

    @Test
    @Order(4)
    void borrarCliente() throws ExcepcionClienteNoEncontrado {
        for (String nif : nifs) {
            assertThat(empresaTelefonia.clientes.get(nif), instanceOf(Cliente.class));
            empresaTelefonia.borrarCliente(nif);
            assertNull(empresaTelefonia.clientes.get(nif));
        }
    }
}