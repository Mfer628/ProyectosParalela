

import RMI2.ClienteRMI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrdenarPeliculas extends JFrame implements ActionListener {
    private JLabel labelSec;
    private JLabel labelConcu;
    private JScrollPane JSTerror;
    private JTextArea txtTerror;

    private JScrollPane JSRomanticas;
    private JTextArea txtRomanticas;
    private JScrollPane JSFamiliares;
    private JTextArea txtFamiliares;

    private JScrollPane JSDocumentales;
    private JTextArea txtDocumentales;

    private JScrollPane JSOtrasPelis;
    private JTextArea txtOtrasPelis;




    public OrdenarPeliculas (){
        setTitle("Ordenar Peliculas");
        setSize(1000, 900);
        getContentPane().setBackground(Color.CYAN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        labelSec = new JLabel("Tiempo de Ejecución");
        labelConcu = new JLabel("Tiempo de Ejecución");
        //Terror
        txtTerror = new JTextArea("");
        JSTerror =new JScrollPane(txtTerror);
        //Romanticas
        txtRomanticas= new JTextArea("");
        JSRomanticas =new JScrollPane(txtRomanticas);

        //Familiares
        txtFamiliares = new JTextArea("");
        JSFamiliares= new JScrollPane(txtFamiliares);

        //Documentales
        txtDocumentales = new JTextArea("");
        JSDocumentales= new JScrollPane(txtDocumentales);

        //Otras Cetagorias
        txtOtrasPelis = new JTextArea("");
        JSOtrasPelis= new JScrollPane(txtOtrasPelis);

        JButton btnSec = new JButton("Boton Secuencial");
        JButton btnConcu = new JButton("Boton Concurrente");
        JButton btnLimpiar = new JButton("Limpiar Cajas");
        JButton btnCliente = new JButton("Iniciar Cliente");

        btnSec.addActionListener(this); // Este objeto OrdenarPeliculas es el ActionListener
        btnConcu.addActionListener(this);
        btnLimpiar.addActionListener(this);
        btnCliente.addActionListener(this);






        // Establecer la posición y el tamaño de los botones
        btnSec.setBounds(50, 50, 150, 30); // (x, y, ancho, alto)
        btnConcu.setBounds(300, 50, 150, 30);
        btnCliente.setBounds(500, 50, 150, 30);
        btnLimpiar.setBounds(700, 50, 150, 30);

        labelSec.setBounds(50, 100, 200, 30);
        labelConcu.setBounds(300, 100, 200, 30);
        JSTerror.setBounds(50, 190,250, 250);
        JSRomanticas.setBounds(350, 190, 250,250);
        JSFamiliares.setBounds(700, 190, 250,250);
        JSDocumentales.setBounds(100, 490, 250,250);
        JSOtrasPelis.setBounds(500, 490, 250,250);
        add(btnSec);
        add(btnConcu);
        add(btnLimpiar);
        add(btnCliente);
        add(labelSec);
        add(labelConcu);
        add(JSTerror);
        add(JSRomanticas);
        add(JSFamiliares);
        add(JSDocumentales);
        add(JSOtrasPelis);

        // Establecer un layout (distribución) para el JFrame (puedes elegir uno que se adapte a tus necesidades)
        setLayout(null);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Boton Secuencial")) {
            System.out.println("Secuencial");
            PeliculasSecuencial obj = new PeliculasSecuencial();
            obj.leerDatos(labelSec,txtTerror,txtRomanticas, txtFamiliares,txtDocumentales,txtOtrasPelis);
        } else if (e.getActionCommand().equals("Boton Concurrente")) {

            System.out.println("Concurrente");
            List<String> todasLasPeliculas = new ArrayList<>(); // Lista de todas las películas

            // Crea una lista para cada categoría
            List<String> terror = new ArrayList<>();
            List<String> romantica = new ArrayList<>();
            List<String> familiar = new ArrayList<>();
            List<String> documentales = new ArrayList<>();
            List<String> otrasCategorias = new ArrayList<>();

            // Crea un hilo para procesar películas de terror
            PeliculasConcurrente hiloTerror = new PeliculasConcurrente("C:\\Users\\mfer-\\ProyectosParalela\\Peliculas.txt", todasLasPeliculas, terror, "Terror");

            // Crea un hilo para procesar películas románticas
            PeliculasConcurrente hiloRomantica = new PeliculasConcurrente("C:\\Users\\mfer-\\ProyectosParalela\\Peliculas.txt", todasLasPeliculas, romantica, "Romantica");

            // Crea un hilo para procesar películas familiares
            PeliculasConcurrente hiloFamiliar = new PeliculasConcurrente("C:\\Users\\mfer-\\ProyectosParalela\\Peliculas.txt", todasLasPeliculas, familiar, "Familiares");

            PeliculasConcurrente hiloDocumentales = new PeliculasConcurrente("C:\\Users\\mfer-\\ProyectosParalela\\Peliculas.txt", todasLasPeliculas, documentales, "Documentales");

            // Crea un hilo para procesar otras categorías
            PeliculasConcurrente hiloOtrasCategorias = new PeliculasConcurrente("C:\\Users\\mfer-\\ProyectosParalela\\Peliculas.txt", todasLasPeliculas, otrasCategorias, "OtrasCategorias");

            long tiempoInicio = System.currentTimeMillis();
            // Inicia los hilos
            hiloTerror.start();
            hiloRomantica.start();
            hiloFamiliar.start();
            hiloDocumentales.start();
            hiloOtrasCategorias.start();

            // Espera a que todos los hilos terminen
            try {
                hiloTerror.join();
                hiloRomantica.join();
                hiloFamiliar.join();
                hiloDocumentales.join();
                hiloOtrasCategorias.join();
            } catch (InterruptedException a) {
                a.printStackTrace();
            }
            //Tiempo Final
            long tiempoFin = System.currentTimeMillis();



            // Imprime los resultados
            System.out.println("Peliculas de Terror:");
            for (String pelicula : terror) {
                txtTerror.append(pelicula+"\n");
                System.out.println(pelicula);
            }

            System.out.println("\nPeliculas Románticas:");
            for (String pelicula : romantica) {
                txtRomanticas.append(pelicula+"\n");
                System.out.println(pelicula);
            }

            System.out.println("\nPeliculas Familiares:");
            for (String pelicula : familiar) {
                txtFamiliares.append(pelicula+"\n");
                System.out.println(pelicula);
            }
            System.out.println("\nDocumentales: ");
            for (String pelicula : documentales) {
                txtDocumentales.append(pelicula+"\n");
                System.out.println(pelicula);
            }

            System.out.println("\nOtras Peliculas:");
            for (String pelicula : otrasCategorias) {
                txtOtrasPelis.append(pelicula+"\n");
                System.out.println(pelicula);
            }

            long tiempoTotal = tiempoFin - tiempoInicio;
            double tiempoTotalSegundos = tiempoTotal / 1000.0;
            labelConcu.setText("Tiempo: " + tiempoTotalSegundos + " ms");
            System.out.println("Tiempo total de ejecución: " + tiempoTotalSegundos + " ms");
        } else if (e.getActionCommand().equals("Limpiar Cajas")) {
            limpiarCajas();
        }
        else if(e.getActionCommand().equals("Iniciar Cliente")){
            //ClientRMI objClient = new ClientRMI();
           // objClient.IniciarClienteRMI();
            System.out.println("Boton Cliente");

            ClienteRMI objCliente = new ClienteRMI();
            objCliente.iniciarCliente();


        }
    }

    public void limpiarCajas(){
        txtTerror.setText("");
        txtRomanticas.setText("");
        txtFamiliares.setText("");
        txtDocumentales.setText("");
        txtOtrasPelis.setText("");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
