public class Animal implements SerVivo {

    private int energia;
    private int edad;
    private final int ENERGIA_MAXIMA = 20;
    private final int EDAD_MAXIMA = 10;

    @Override
    public void nacer() {
        //la energia inicial es un valor aleatorio entre 1 y 5
        energia = (int) ((Math.random() * 5) + 1);
        edad = 0;
    }

    @Override
    public int getEnergia() {
        return energia;
    }

    @Override
    public int getEdad() {
        return edad;
    }

    @Override
    public void envejecer() {
        if (!haMuerto()) {
            edad++;
        }
    }

    public boolean haMuerto() {
        if( energia <= 0 || edad >= EDAD_MAXIMA) {
            return true;
        } else {
            return false;
        }
    }

    //imlementar la logica del metodo alimentarse cuando se encuentra con una planta

    //implementar la logica del metodo reproducirse cuando se encuentra con otro animal

    //implementar la logica del metodo morir cuando llega a su edad max o se queda sin energia

    //implementar la logica del metodo moverse
}
