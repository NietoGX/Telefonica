package tarifa;

public abstract class FactoriaTarifa {
    public static Tarifa basica() {
        return new TarifaBasica();
    }
    public static Tarifa tarde(Tarifa tarifa) {
        return new TarifaTarde(tarifa);
    }
    public static Tarifa domingo(Tarifa tarifa) {
        return new TarifaDomingo(tarifa);
    }
}
