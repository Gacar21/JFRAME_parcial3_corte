package FrameBD;

import ConexionBD.Crud;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarAeropuertos extends JFrame {
    private JTextField NombreAeroField;
    private JRadioButton PUBLICORadioButton;
    private JRadioButton PRIVADORadioButton;
    private JTextField CompaField;
    private JTextField CiudadAero;
    private JTextField PaisAeroField;
    private JTextField OrigenField;
    private JTextField DestinoField;
    private JButton Register;
    private JButton Volver;
    private JTextField SubvenField;
    private JPanel mainPanel;
    private JTextField IdentiField;
    private JTextField NumMaxField;
    private JTextField PreciField;

    Crud operaciones = new Crud();

    public RegistrarAeropuertos() {

        setTitle("Registrar Aeropuertos");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        setContentPane(mainPanel);


        ButtonGroup tipoAeropuerto = new ButtonGroup();
        tipoAeropuerto.add(PUBLICORadioButton);
        tipoAeropuerto.add(PRIVADORadioButton);


        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int nuevoId = operaciones.getUltimoIdAeropuerto() + 1;
                    int nuevoIdcompa = operaciones.getUltimoCompania() +1;
                    int nuevoIdVuelo = operaciones.getUltimoVuelo() +1;
                    int idAeroCompa = operaciones.getUltimoIdAeroCompania()+1;
                    int idCompaVuelo = operaciones.getUltimoIdCompaVuelo()+1;

                    if (!operaciones.insertarVuelo(nuevoIdVuelo,
                            IdentiField.getText(),
                            OrigenField.getText(),
                            DestinoField.getText(),
                            PreciField.getText(),
                            NumMaxField.getText())) {
                        return;
                    }


                    if (!operaciones.insertarCompania(nuevoIdcompa,
                            CompaField.getText())) {
                        return;
                    }

                    operaciones.insertar(nuevoId,
                            NombreAeroField.getText(),
                            CiudadAero.getText(),
                            PaisAeroField.getText(),
                            PUBLICORadioButton.isSelected() ? "1" : "0",
                            PRIVADORadioButton.isSelected() ? "1" : "0",
                            Integer.parseInt(SubvenField.getText()));


                    if (!operaciones.AeroCompa(idAeroCompa, nuevoId, nuevoIdcompa)) {
                        return;
                    }

                    if(!operaciones.CompaVuelo(idCompaVuelo, nuevoIdcompa, nuevoIdVuelo)) {
                        return;
                    }

                    JOptionPane.showMessageDialog(null, "Aeropuerto registrado: " + nuevoId);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ERRROR" );
                }
            }
        });

        Volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazPrincipal principal = new InterfazPrincipal();
                principal.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RegistrarAeropuertos frame = new RegistrarAeropuertos();
                frame.setVisible(true);
            }
        });
    }
}