package RMI2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;


public class ServidorRMI {
    private static final String ARCHIVO_PELICULAS = "C:\\Users\\mfer-\\ProyectosParalela\\Peliculas.txt";
    private static final int PUERTO_RMI = 1099;

    public void iniciarServidor() {
        try {
            List<String> todasLasPeliculas = leerPeliculasDesdeArchivo(ARCHIVO_PELICULAS);

            ClasificacionRMI clasificacionRemota = new ClasificacionRemota(todasLasPeliculas);

            // Crea el registro en la dirección IP específica
            Registry registry = LocateRegistry.createRegistry(PUERTO_RMI);
            registry.rebind("ClasificacionRMI", clasificacionRemota);

            System.out.println("Servidor RMI listo en la dirección IP: " + registry);
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<String> leerPeliculasDesdeArchivo(String nombreArchivo) {
        List<String> peliculas = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                peliculas.add(linea);
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de películas: " + e.getMessage());
            e.printStackTrace();
        }

        return peliculas;
    }


}

