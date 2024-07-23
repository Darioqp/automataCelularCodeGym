public enum EstadoCelda {

    VACIO("_"),
    PLANTA("P"),
    ANIMAL("A"),
    REPRODUCCION("R"),
    ALIMENTACION("C"),
    INDEFINIDO("?");

    private final String representacion;

    EstadoCelda(String representacion) {
        this.representacion = representacion;
    }

    @Override
    public String toString() {
        return representacion;
    }
}
