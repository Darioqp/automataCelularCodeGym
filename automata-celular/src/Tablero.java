public class Tablero {

    private int numeroFilas;
    private int numeroColumnas;
    private Celda[][] tablero;


    public Tablero(int numeroFilas, int numeroColumnas) {
        this.numeroFilas = numeroFilas;
        this.numeroColumnas = numeroColumnas;
        this.tablero = crearTablero();
    }

    public Celda[][] crearTablero() {
        Celda[][] nuevoTablero = new Celda[numeroFilas][numeroColumnas];
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                nuevoTablero[i][j] = new Celda(i, j);
            }
        }
        return nuevoTablero;
    }

    public Celda getCelda(int fila, int columna) {
        if (fila >= 0 && fila <= numeroFilas && columna >= 0 && columna <= numeroColumnas) {
            return tablero[fila][columna];
        } else {
            throw new IllegalArgumentException("Indice de fila y/o columna fuera de rango");
        }
    }
}
