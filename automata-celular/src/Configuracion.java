public final class Configuracion {

    //parametros del tablero
    public static final int NUMERO_FILAS = 10;
    public static final int NUMERO_COLUMNAS = 10;

    //parametros de las plantas
    public static final int ENERGIA_INICIAL_PLANTA = 5;
    public static final int ENERGIA_MAXIMA_PLANTA = 100;
    public static final int EDAD_MAXIMA_PLANTA = 25;
    public static final int ENERGIA_POR_COMIDA = 3;

    //parametros de los animales
    public static final int ENERGIA_INICIAL_ANIMAL = 30;
    public static final int ENERGIA_MAXIMA_ANIMAL = 100;
    public static final int EDAD_MAXIMA_ANIMAL = 30;
    public static final int ENERGIA_POR_MOVIMIENTO = 1;
    public static final int ENERGIA_POR_REPRODUCCION = 5;

    //parametros del archivo de salida
    public static final String ARCHIVO_DE_SALIDA = "estadisticas.csv";

    private Configuracion() {}

}
