public class Planta implements SerVivo {

    private int energia;
    private int edad;
    private boolean estaVivo;

    public Planta() {
        this.energia = Configuracion.ENERGIA_INICIAL_PLANTA;
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
        if (estaVivo() && edad < Configuracion.EDAD_MAXIMA_PLANTA) {
            edad++;
        }
    }

    public void incrementarEnergia() {
        if (estaVivo() && energia < Configuracion.ENERGIA_MAXIMA_PLANTA) {
            energia++;
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
}
