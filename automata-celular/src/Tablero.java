import java.util.List;
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

    public void actualizarTablero(int tiempo) {

        int cantidadAnimales = 0;
        int cantidadPlantas = 0;
        int cantidadNacimientos = 0;
        int cantidadMuertes = 0;
        StringBuilder eventos = new StringBuilder();

        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                Celda celdaActual = tablero[i][j];

                switch (celdaActual.getEstado()) {
                    case ANIMAL:
                        Animal animal = (Animal) celdaActual.getSerVivo();
                        List<Celda> vecinos = Vecindad.obtenerVecinos(this, celdaActual);

                        animal.moverse(vecinos); // Intenta moverse (puede morir al moverse)
                        if (animal.estaVivo()) {
                            animal.incrementarEdad();
                        }

                        if (!animal.estaVivo()) {
                            animal.getCelda().setSerVivo(null);
                            animal.getCelda().setEstado(EstadoCelda.VACIO);
                            cantidadMuertes++;
                            eventos.append("Muere animal en [").append(animal.getCelda().getIndiceFila()).append(", ").append(animal.getCelda().getIndiceColumna()).append("] / ");
                        } else {
                            cantidadAnimales++;
                        }
                        break;

                    case PLANTA:
                        Planta planta = (Planta) celdaActual.getSerVivo();
                        planta.incrementarEdad();
                        planta.incrementarEnergia();

                        if (!planta.estaVivo()) {
                            celdaActual.setSerVivo(null);
                            celdaActual.setEstado(EstadoCelda.VACIO);
                            cantidadMuertes++;
                            eventos.append("Muere planta en [").append(planta.getCelda().getIndiceFila()).append(", ").append(planta.getCelda().getIndiceColumna()).append("] / ");
                        } else {
                            cantidadPlantas++;
                        }
                        break;

                    case REPRODUCCION:
                        Animal animalReproducion = (Animal) celdaActual.getSerVivo();
                        if (animalReproducion.seReprodujo()) { // Verificar si el animal ya se reprodujo en esta iteración
                            animalReproducion.reiniciarReproduccion(); // Reiniciar el estado de reproducción
                            celdaActual.setEstado(EstadoCelda.ANIMAL); // Volver al estado ANIMAL
                        } else {
                            // Buscar pareja y reproducirse si es posible
                            for (Celda vecina : Vecindad.obtenerVecinos(this, celdaActual)) {
                                if (vecina.tieneAnimal()) {
                                    Animal pareja = (Animal) vecina.getSerVivo();
                                    animalReproducion.reproducirse(pareja); // Llamar al método reproducirse()
                                    if (animalReproducion.seReprodujo()) {
                                        cantidadNacimientos++;
                                        eventos.append("Nacimiento en celda [").append(animalReproducion.getCelda().getIndiceFila()).append(", ").append(animalReproducion.getCelda().getIndiceColumna()).append("] / ");
                                        break; // Salir del bucle si se reprodujo
                                    }
                                }
                            }
                            if (!animalReproducion.seReprodujo()) {
                                celdaActual.setEstado(EstadoCelda.INDEFINIDO); // Si no se pudo reproducir, marcar como INDEFINIDO
                            }
                        }
                        break;

                    case ALIMENTACION:
                        celdaActual.setEstado(EstadoCelda.ANIMAL); // Volver al estado ANIMAL después de la alimentación
                        break;
                }
            }
        }

        tiempo++;
        Estadisticas estadisticas = new Estadisticas();
        estadisticas.guardarEstadisticas(tiempo, cantidadAnimales, cantidadPlantas, cantidadNacimientos, cantidadMuertes, eventos.toString());
    }

    public void poblarTableroInicial() {
        Random random = new Random();
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                int tipoSerVivo = random.nextInt(3);
                if (tipoSerVivo == 0) {
                    tablero[i][i].setEstado(EstadoCelda.VACIO);
                } else if (tipoSerVivo == 1) {
                    tablero[i][j].setSerVivo(new Animal(tablero[i][j]));
                    tablero[i][j].setEstado(EstadoCelda.ANIMAL);
                } else {
                    tablero[i][j].setSerVivo(new Planta(tablero[i][j]));
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

    /*
    Animal animalReproduccion = (Animal) celdaActual.getSerVivo();
                        if (animalReproduccion.seReprodujo()) {
                            List<Celda> celdasVecinasVacias = Vecindad.getCeldasVecinasVacias(this, celdaActual);
                            if (!celdasVecinasVacias.isEmpty()) {
                                Celda celdaCria = celdasVecinasVacias.get(0);
                                Animal cria = new Animal(celdaCria);
                                celdaCria.setSerVivo(cria);
                                celdaCria.setEstado(EstadoCelda.ANIMAL);
                                cantidadNacimientos++;
                                eventos.append("Nacimiento en celda [").append(celdaCria.getIndiceFila()).append(", ").append(celdaCria.getIndiceColumna()).append("] / ");
                            }
                            animalReproduccion.reiniciarReproduccion();
                        }
                        celdaActual.setEstado(EstadoCelda.ANIMAL); // Volver al estado ANIMAL después de la reproducción
                        break;
     */
}
