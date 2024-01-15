package RMI2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClasificacionRMI extends Remote {
    List<String> clasificarPeliculas(String categoria) throws RemoteException;
}
