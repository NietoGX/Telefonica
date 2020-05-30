package fecha;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

public class FechaGenerico {
    public static <T extends TieneFecha> Collection<T> getConjuntoPorFecha(Collection<T> conjuntoObjetos, Calendar fechaInicio, Calendar fechaFinal){
        Collection<T> conjuntoFinal = new LinkedList<T>();
        for(T col : conjuntoObjetos){
            if(col.getFecha().after(fechaInicio) && col.getFecha().before(fechaFinal))
                conjuntoFinal.add(col);
        }
        return conjuntoFinal;
    }
}
