public class Celda {

    private int indiceFila;
    private int indiceColumna;
    private EstadoCelda estado;
    private SerVivo serVivo;


    public Celda(int indiceFila, int indiceColumna, EstadoCelda estadoInicial) {
        this.indiceFila = indiceFila;
        this.indiceColumna = indiceColumna;
        this.estado = estadoInicial;
        this.serVivo = null;
    }

    //metodos get y set para los atributos
    public int getIndiceFila() {
        return indiceFila;
    }

    public int getIndiceColumna() {
        return indiceColumna;
    }

    public EstadoCelda getEstado() {
        return estado;
    }

    public void setEstado(EstadoCelda nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public SerVivo getSerVivo() {
        return serVivo;
    }

    public void setSerVivo(SerVivo serVivo) {
        this.serVivo = serVivo;
    }

    // Métodos auxiliares para verificar el estado de la celda
    public boolean estaVacia() {
        return estado == EstadoCelda.VACIO;
    }

    public boolean tienePlanta() {
        return estado == EstadoCelda.PLANTA;
    }

    public boolean tieneAnimal() {
        return estado == EstadoCelda.ANIMAL;
    }

    public boolean estaEnReproduccion() {
        return estado == EstadoCelda.REPRODUCCION;
    }

    public boolean estaEnAlimentacion() {
        return estado == EstadoCelda.ALIMENTACION;
    }

    public boolean esIndefinido() {
        return estado == EstadoCelda.INDEFINIDO;
    }
}
