public class SimulacionAutomataCelular {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        tablero.poblarTableroInicial();

        int numeroDeIteracion = 0;
        while (true) {
            System.out.println("Tiempo: " + (numeroDeIteracion + 1));
            System.out.println(tablero);
            tablero.actualizarTablero(numeroDeIteracion);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            numeroDeIteracion++; // Incrementar el contador de tiempo
        }
    }
}
