import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PeliculasConcurrente extends Thread {
    private String nombreArchivo;
    private List<String> todasLasPeliculas;
    private List<String> peliculasCategoria;
    private String categoria;

    public PeliculasConcurrente(String nombreArchivo, List<String> todasLasPeliculas, List<String> peliculasCategoria, String categoria) {
        this.nombreArchivo = nombreArchivo;
        this.todasLasPeliculas = todasLasPeliculas;
        this.peliculasCategoria = peliculasCategoria;
        this.categoria = categoria;
    }

    public void run() {
        try {
            FileReader fileReader = new FileReader(nombreArchivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                todasLasPeliculas.add(linea); // Agregar a la lista de todas las películas

                // Procesar y clasificar la película en la categoría correspondiente
                String[] partes = linea.split("-");
                if (partes.length == 2) {
                    String titulo = partes[0].trim().toLowerCase();
                    String descripcion = partes[1].trim().toLowerCase();

                    if (titulo.contains(categoria.toLowerCase()) || descripcion.contains(categoria.toLowerCase())) {
                        peliculasCategoria.add(linea);
                    }else {
                        // Manejar líneas que no tienen el formato esperado
                        //System.err.println("Línea incorrecta: " + linea);
                    }
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
