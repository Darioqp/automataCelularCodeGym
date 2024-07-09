public class Planta implements SerVivo {

    private int energia;
    private int edad;
    private final int ENERGIA_MAXIMA = 30;

    @Override
    public void nacer() {
        energia = 1;
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
        edad++;
        if (energia < ENERGIA_MAXIMA) {
            energia++;
        }
    }
}
