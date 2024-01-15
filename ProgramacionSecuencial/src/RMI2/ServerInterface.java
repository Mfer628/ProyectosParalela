package RMI2;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ServerInterface extends JFrame {
    public ServerInterface(){
        setTitle("Servidor RMI");
        setSize(300,300);
        JButton miBoton = new JButton("Presionar");
        // Agregar un ActionListener para manejar eventos del botón
        miBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServidorRMI obkServer = new ServidorRMI();
                obkServer.iniciarServidor();

                JOptionPane.showMessageDialog(null,"Corriendo Servidor");
            }
        });

        // Agregar el botón al JFrame
        getContentPane().add(miBoton);

        // Hacer visible el JFrame
        setVisible(true);
    }

    public static void main(String[] args) {
        new ServerInterface();
    }

}
