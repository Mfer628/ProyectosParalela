import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PeliculasSecuencial {

    String nombreArchivo = "C:\\Users\\mfer-\\ProyectosParalela\\Peliculas.txt";
    List<String> terrorPeliculas = new ArrayList<>();
    List<String> romanticaPeliculas = new ArrayList<>();
    List<String> familiarPeliculas = new ArrayList<>();
    List<String> documentalesPeliculas = new ArrayList<>();
    List<String> otrasPeliculas = new ArrayList();



    public void leerDatos(JLabel labelSec,JTextArea txtTerror, JTextArea txtRomant,JTextArea txtFamiliares, JTextArea txtDocu, JTextArea otrasCate ) {
        long startTime = System.nanoTime();

            try {
                FileReader fileReader = new FileReader(nombreArchivo);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String linea;

                while ((linea = bufferedReader.readLine()) != null) {
                    String[] partes = linea.split("-");
                    if (partes.length == 2) {
                        String titulo = partes[0].trim().toLowerCase();
                        String descripcion = partes[1].trim().toLowerCase();

                        if (titulo.contains("terror") || descripcion.contains("terror")) {
                            terrorPeliculas.add(linea);
                        } else if (titulo.contains("romantica") || descripcion.contains("romantica")) {
                            romanticaPeliculas.add(linea);
                        } else if (titulo.contains("familiares") || descripcion.contains("familiares")) {
                            familiarPeliculas.add(linea);
                        } else if (titulo.contains("documentales") || descripcion.contains("documentales")) {
                            documentalesPeliculas.add(linea);
                        } else if (titulo.contains("otrascategorias") || descripcion.contains("otrascategorias")) {
                            otrasPeliculas.add(linea);
                        }
                    }
                }

                bufferedReader.close();


            } catch (IOException e) {
                e.printStackTrace();

            }


        long endTime = System.nanoTime();

        // Imprime las películas clasificadas en las categorías
        System.out.println("Peliculas de Terror:");
        for (String pelicula : terrorPeliculas) {
            txtTerror.append(pelicula+"\n");
            System.out.println(pelicula);
        }

        System.out.println("\nPeliculas Románticas:");
        for (String pelicula : romanticaPeliculas) {
            txtRomant.append(pelicula+"\n");
            System.out.println(pelicula);
        }

        System.out.println("\nPeliculas Familiares:");
        for (String pelicula : familiarPeliculas) {
            txtFamiliares.append(pelicula+"\n");
            System.out.println(pelicula);
        }
        System.out.println("\n Domumentales:");
        for (String pelicula : documentalesPeliculas) {
            txtDocu.append(pelicula+"\n");
            System.out.println(pelicula);
        }

        System.out.println("\nOtras Peliculas:");
        for (String pelicula : otrasPeliculas) {
            otrasCate.append(pelicula+"\n");
            System.out.println(pelicula);
        }
        long tiempoTranscurrido = endTime - startTime;
        double tiempoSegundos = tiempoTranscurrido / 1_000_000.0;//Conviertiendo milisegundos
       labelSec.setText("Tiempo transcurrido: " + tiempoSegundos + " ms");
    }


}