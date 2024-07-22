import java.util.ArrayList;
import java.util.List;

public class Vecindad {

    public static List<Celda> obtenerVecinos(Tablero tablero, Celda celda) {
        List<Celda> vecinos = new ArrayList<>();

        int x = celda.getIndiceFila();
        int y = celda.getIndiceColumna();

        if (x > 0) {
            vecinos.add(tablero.getCelda(x - 1, y));
        }

        if (x < Configuracion.NUMERO_FILAS - 1) {
            vecinos.add(tablero.getCelda(x + 1, y));
        }

        if (y > 0) {
            vecinos.add(tablero.getCelda(x, y - 1));
        }

        if (y < Configuracion.NUMERO_COLUMNAS - 1) {
            vecinos.add(tablero.getCelda(x, y + 1));
        }

        return vecinos;
    }
}
