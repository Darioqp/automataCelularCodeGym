public class Planta implements SerVivo {

    private int energia;
    private int edad;
    private boolean estaVivo;
    private Celda celda;

    public Planta(Celda celda) {
        this.energia = Configuracion.ENERGIA_INICIAL_PLANTA;
        this.edad = 0;
        this.estaVivo = true;
        this.celda = celda;
    }

    public Celda getCelda() {
        return celda;
    }

    public void setCelda(Celda celda) {
        this.celda = celda;
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

    public void reducirEnergia(int cantidad) {
        this.energia -= cantidad;
        if (this.energia <= 0) {
            this.morir();
        }
    }

    @Override
    public boolean estaVivo() {
        return this.energia > 0 && this.edad < Configuracion.ENERGIA_MAXIMA_PLANTA;
    }

    @Override
    public void morir() {
        this.estaVivo = false;
    }
}
