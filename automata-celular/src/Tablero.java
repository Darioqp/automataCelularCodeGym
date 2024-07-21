import java.util.Random;

public class Tablero {

    private int numeroFilas;
    private int numeroColumnas;
    private Celda[][] tablero;


    public Tablero() {
        this.numeroFilas = Configuracion.NUMERO_FILAS;
        this.numeroColumnas = Configuracion.NUMERO_COLUMNAS;
        this.tablero = crearTablero();
    }

    public Celda[][] crearTablero() {
        Celda[][] nuevoTablero = new Celda[numeroFilas][numeroColumnas];
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                nuevoTablero[i][j] = new Celda(i, j, EstadoCelda.VACIO);
            }
        }
        return nuevoTablero;
    }

    public Celda getCelda(int fila, int columna) {
        if (fila >= 0 && fila < numeroFilas && columna >= 0 && columna < numeroColumnas) {
            return tablero[fila][columna];
        } else {
            throw new IllegalArgumentException("Indice de fila y/o columna fuera de rango");
        }
    }

    public void actualizarTablero() {
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                Celda celdaActual = tablero[i][j];
                if (celdaActual.tieneAnimal()) {
                    Animal animal = (Animal) celdaActual.getSerVivo();
                    //logica para el comportamiento
                } else if (celdaActual.tienePlanta()) {
                    Planta planta = (Planta) celdaActual.getSerVivo();
                    //logica para el comportamiento
                }
                //logica para eliminar un animal o planta
            }
        }
    }

    public void poblarTableroInicial() {
        Random random = new Random();
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                int tipoSerVivo = random.nextInt(3);
                if (tipoSerVivo == 0) {
                    tablero[i][i].setEstado(EstadoCelda.VACIO);
                } else if (tipoSerVivo == 1) {
                    tablero[i][j].setSerVivo(new Animal());
                    tablero[i][j].setEstado(EstadoCelda.ANIMAL);
                } else {
                    tablero[i][j].setSerVivo(new Planta());
                    tablero[i][j].setEstado(EstadoCelda.PLANTA);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder salidaTablero = new StringBuilder();

        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                Celda celda = tablero[i][j];
                salidaTablero.append(celda.getEstado().toString());
                salidaTablero.append(" ");
            }
            salidaTablero.append("\n");
        }
        return salidaTablero.toString();
    }
}
