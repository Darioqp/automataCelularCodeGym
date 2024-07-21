public class SimulacionAutomataCelular {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        tablero.poblarTableroInicial();

        int tiempo = 0; // Contador de tiempo
        while (true) {
            System.out.println("Tiempo: " + tiempo);
            System.out.println(tablero);

            tablero.actualizarTablero();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tiempo++; // Incrementar el contador de tiempo
        }
    }
}
