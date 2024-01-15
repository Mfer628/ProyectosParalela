package RMI2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

public class ClasificacionRemota extends UnicastRemoteObject implements ClasificacionRMI {
    private List<String> todasLasPeliculas;

    public ClasificacionRemota(List<String> todasLasPeliculas) throws RemoteException {
        this.todasLasPeliculas = todasLasPeliculas;
    }

    @Override
    public List<String> clasificarPeliculas(String categoria) throws RemoteException {
        return todasLasPeliculas.stream()
                .filter(pelicula -> cumpleCriterio(pelicula, categoria))
                .collect(Collectors.toList());
    }

    private boolean cumpleCriterio(String pelicula, String categoria) {
        String[] partes = pelicula.split("-");
        if (partes.length == 2) {
            String titulo = partes[0].trim();
            String descripcion = partes[1].trim();

            // Puedes eliminar la conversión a minúsculas si quieres conservar el caso original
            categoria = categoria.toLowerCase();
            titulo = titulo.toLowerCase();
            descripcion = descripcion.toLowerCase();

            return titulo.contains(categoria) || descripcion.contains(categoria);
        }
        return false;
    }
}
