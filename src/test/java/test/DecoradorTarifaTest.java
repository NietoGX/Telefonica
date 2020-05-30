package test;

import datos.*;
import Swing.Modelo.EmpresaTelefonia;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tarifa.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecoradorTarifaTest {
    private static EmpresaTelefonia empresaTelefonia;
    private Random random = new Random();

    @BeforeAll
    @Test
    static void inicializacion() {
        empresaTelefonia = new EmpresaTelefonia();
    }

    @Test
    void calculoImporteLlamada() throws ParseException {
        String numDestino = Integer.toString(random.nextInt(999999999));
        int duracionLlamada = random.nextInt(3600);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar fecha = Calendar.getInstance();
        String string = "19/04/2020 18:42";
        fecha.setTime(formatter.parse(string));

        Llamada llamada = new Llamada(numDestino, fecha, duracionLlamada);

        Tarifa tarifa = FactoriaTarifa.basica();
        assertEquals(tarifa.calcularCoste(llamada), 0.15 * duracionLlamada);

        tarifa = FactoriaTarifa.tarde(tarifa);
        assertEquals(tarifa.calcularCoste(llamada), 0.05 * duracionLlamada);

        tarifa = FactoriaTarifa.domingo(tarifa);
        assertEquals(tarifa.calcularCoste(llamada), 0);
    }
}
