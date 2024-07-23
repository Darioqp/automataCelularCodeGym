import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animal implements SerVivo {
    //declaracion de variables
    private int energia;
    private int edad;
    private boolean estaVivo;
    private Celda celda;
    private boolean reprodujo = false;

    //constructor de la clase animal
    public Animal(Celda celda) {
        this.energia = Configuracion.ENERGIA_INICIAL_ANIMAL;
        this.edad = 0;
        this.estaVivo = true;
        this.celda = celda;
    }

    public Celda getCelda() {
        return celda;
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
        return this.energia > 0 && this.edad < Configuracion.EDAD_MAXIMA_ANIMAL;
    }

    @Override
    public void morir() {
        this.estaVivo = false;
    }


    public void alimentarse(Celda celdaVecina) {
        if (celdaVecina.tienePlanta()) {
            Planta planta = (Planta) celdaVecina.getSerVivo();
            int energiaConsumida = Configuracion.ENERGIA_POR_COMIDA;

            planta.reducirEnergia(energiaConsumida);
            this.energia = Math.min(this.energia + energiaConsumida, Configuracion.ENERGIA_MAXIMA_ANIMAL);

            if (!planta.estaVivo()) {
                celdaVecina.setSerVivo(null);
                celdaVecina.setEstado(EstadoCelda.VACIO);
            }
        }
    }

    public void moverse(List<Celda> celdasVecinas) {
        Random random = new Random();
        Celda celdaVecina = celdasVecinas.get(random.nextInt(celdasVecinas.size()));

        celdaVecina.setSerVivo(this);
        this.celda.setEstado(EstadoCelda.VACIO);
        this.celda.setSerVivo(null);
        this.celda = celdaVecina;

        this.energia -= Configuracion.ENERGIA_POR_MOVIMIENTO;

        if (this.energia <= 0) {
            this.morir();
            celdaVecina.setSerVivo(null); // Se elimina el animal de la nueva celda si murió
            celdaVecina.setEstado(EstadoCelda.VACIO); // Marcar la celda como vacía
        } else {

            if (celdaVecina.tienePlanta()) {
                celdaVecina.setEstado(EstadoCelda.ALIMENTACION); // Si hay una planta, el animal se alimenta
                this.alimentarse(celdaVecina);
            } else if (celdaVecina.tieneAnimal()) {
                // Hay reproduccion solo si hay exactamente dos animales en la celda
                if (contarAnimalesEnCelda(celdaVecina) == 2) {
                    celdaVecina.setEstado(EstadoCelda.REPRODUCCION);
                    Animal pareja = (Animal) celdaVecina.getSerVivo();
                    this.reproducirse(pareja);
                }
                if (contarAnimalesEnCelda(celdaVecina) == 3 || contarAnimalesEnCelda(celdaVecina) == 4) {
                    celdaVecina.setEstado(EstadoCelda.INDEFINIDO); // Si hay más de dos animales, marcar como INDEFINIDO
                }
            } else {
                celdaVecina.setEstado(EstadoCelda.ANIMAL); // Si está vacía, solo se mueve
            }
        }
    }

    public void reproducirse(Animal pareja) {
        if (this.energia > Configuracion.ENERGIA_POR_REPRODUCCION && pareja.getEnergia() > Configuracion.ENERGIA_POR_REPRODUCCION) {

            List<Celda> celdasVecinasVacias = Vecindad.getCeldasVecinasVacias(this.celda.getTablero(), this.celda);

            if (!celdasVecinasVacias.isEmpty()) {
                Celda celdaCria = celdasVecinasVacias.get(0);

                Animal cria = new Animal(celdaCria);

                celdaCria.setSerVivo(cria);
                celdaCria.setEstado(EstadoCelda.ANIMAL);

                this.energia -= Configuracion.ENERGIA_POR_REPRODUCCION;
                pareja.reducirEnergia(Configuracion.ENERGIA_POR_REPRODUCCION);

                this.reprodujo = true; // Marcar que se reprodujo
            }
        }
    }

    private void reducirEnergia(int cantidad) {
        this.energia -= cantidad;
        if (this.energia <= 0) {
            this.morir();
        }
    }

    public void reiniciarReproduccion() {
        this.reprodujo = false;
    }


    public boolean seReprodujo() {
        return reprodujo;
    }

    private int contarAnimalesEnCelda(Celda celda) {
        return celda.tieneAnimal() ? 1 : 0; // 1 si hay un animal, 0 si no
    }
}
