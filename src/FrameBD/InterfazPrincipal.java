package FrameBD;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InterfazPrincipal extends JFrame {

    private JPanel Interfaz;
    private JButton ComBolet;
    private JButton Salir;
    private JButton Registrar;
    private JPanel Decora;


    public InterfazPrincipal() {

        setTitle("Interfaz Principal");
        setSize(720, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        setContentPane(Interfaz);

        ComBolet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerAeropuertos verAeropuertos = new VerAeropuertos();
                verAeropuertos.setVisible(true);
                dispose();
            }
        });

        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        Registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarAeropuertos register = new RegistrarAeropuertos();
                register.setVisible(true);
                dispose();
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InterfazPrincipal frame = new InterfazPrincipal();
                frame.setVisible(true);
            }
        });
    }

}