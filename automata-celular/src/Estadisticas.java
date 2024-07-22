import java.io.FileWriter;
import java.io.IOException;

public class Estadisticas {

    public void guardarEstadisticas(int tiempo,
                                    int cantidadAnimales,
                                    int cantidadPlantas,
                                    int cantidadNacimientos,
                                    int cantidadMuertes,
                                    String eventos) {
        String resgistroEstadisticas = String.format("%d; %d; %d; %d; %d; %s \n",
                tiempo, cantidadAnimales, cantidadPlantas, cantidadNacimientos, cantidadMuertes, eventos);

        try (FileWriter salidaEstadisticas = new FileWriter(Configuracion.ARCHIVO_DE_SALIDA, true)) {
            salidaEstadisticas.write(resgistroEstadisticas);
        } catch (IOException e) {
            System.out.println("Error al guardar Estadisticas" + e.getMessage());
        }
    }
}
