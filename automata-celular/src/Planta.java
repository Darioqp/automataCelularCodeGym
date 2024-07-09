public class Planta implements SerVivo {

    private int energia;
    private int edad;
    private final int ENERGIA_MAXIMA = 30;

    public Planta(int energia, int edad) {
        this.energia = energia;
        this.edad = edad;
    }

    @Override
    public void nacer() {
        energia = 3;
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
