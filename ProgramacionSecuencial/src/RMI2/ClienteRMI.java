package RMI2;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ClienteRMI {

    private static final List<String> CATEGORIAS = List.of("Terror", "Familiares", "Documentales", "OtrasCategorias");
    private static final String SERVER_IP = "192.168.84.240";
    private static final int RMI_PORT = 1099;

    public void iniciarCliente() {
        try {
            String serverURL = "//" + SERVER_IP + ":" + RMI_PORT + "/ClasificacionRMI";
            ClasificacionRMI clasificacionRemota = (ClasificacionRMI) Naming.lookup(serverURL);

            long tiempoInicio = System.currentTimeMillis();

            List<CompletableFuture<List<String>>> futures = CATEGORIAS.stream()
                    .map(categoria -> CompletableFuture.supplyAsync(() -> clasificarPeliculas(clasificacionRemota, categoria)))
                    .toList();

            List<List<String>> resultados = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApplyAsync(v -> futures.stream().map(f -> {
                        try {
                            return f.get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList())
                    .join();

            long tiempoFin = System.currentTimeMillis();

            imprimirResultados(resultados);

            long tiempoTotal = tiempoFin - tiempoInicio;
            double tiempoTotalSegundos = tiempoTotal / 1000.0;
            System.out.println("Tiempo total de ejecución: " + tiempoTotalSegundos + " ms");
        } catch (Exception e) {
            manejarExcepcion(e);
        }
    }

    private List<String> clasificarPeliculas(ClasificacionRMI clasificacionRemota, String categoria) {
        try {
            return clasificacionRemota.clasificarPeliculas(categoria);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private void manejarExcepcion(Exception e) {
        System.err.println("Error en el cliente: " + e.toString());
        e.printStackTrace();
    }

    private void imprimirResultados(List<List<String>> resultados) {
        for (int i = 0; i < CATEGORIAS.size(); i++) {
            String categoria = CATEGORIAS.get(i);
            List<String> peliculas = resultados.get(i);

            System.out.println("Peliculas de " + categoria + ":");
            peliculas.forEach(System.out::println);
        }
    }


}

