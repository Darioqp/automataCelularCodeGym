public class Animal implements SerVivo {

    private int energia;
    private int edad;
    private boolean estaVivo;

    public Animal() {
        this.energia = Configuracion.ENERGIA_INICIAL_ANIMAL;
        this.edad = 0;
        this.estaVivo = true;

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
    public void incrementarEdad() {
        if (estaVivo()) {
            edad++;
        }
    }

    @Override
    public boolean estaVivo() {
        return this.estaVivo;
    }

    @Override
    public void morir() {
        this.estaVivo = false;
    }

    //imlementar la logica del metodo alimentarse cuando se encuentra con una planta

    //implementar la logica del metodo reproducirse cuando se encuentra con otro animal

    //implementar la logica del metodo moverse
}
