package FrameBD;

import ConexionBD.Crud;

import javax.swing.*;
import java.awt.event.*;

public class ComprarBoletos extends JFrame {
    private JList Datos;
    private JTextField NacionalidadUsuario;
    private JTextField PasaporteUsuario;
    private JTextField NombreUsuario;
    private JButton Comprar;
    private JButton Volver;
    private JPanel FacturaBole;
    private JList Vuelos;
    private JButton A1;
    private JButton A4;
    private JButton A2;
    private JButton A3;
    private JButton B1;
    private JButton B4;
    private JButton B2;
    private JButton B3;
    private JButton C1;
    private JButton C4;
    private JButton C2;
    private JButton C3;
    private JButton D1;
    private JButton D4;
    private JButton D2;
    private JButton D3;
    private JButton E1;
    private JButton E4;
    private JButton E2;
    private JButton E3;
    private JButton F1;
    private JButton F2;
    private JButton G1;
    private JButton G2;
    int ultimopasajero = 0;
    String AsientoSeleccionado = " ";
    private JButton SelectAsie;
    public static Object VueloSelect;

    VerAeropuertos verAeropuertos = new VerAeropuertos();

    DefaultListModel modelo = new DefaultListModel();

    Crud operaciones = new Crud();


    public ComprarBoletos() {

        setTitle("Registrar Aeropuertos");
        setSize(1500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        System.out.println(verAeropuertos.indice);
        System.out.println(verAeropuertos.indice2);


        setContentPane(FacturaBole);

        modelo.clear();

        operaciones.leerVuelos();
        Vuelos.setModel(operaciones.getModeloVuelos());
        Vuelos.setVisible(true);
        modelo.addElement("DATOS SELECCIONADOS:  ");
        modelo.addElement(" ");

        if (VerAeropuertos.AeroSelect != null) {
            modelo.addElement("AEROPUERTO SELECCIONADO: ");
            modelo.addElement(" ");
            modelo.addElement(VerAeropuertos.AeroSelect.toString());
        }

        if (VerAeropuertos.CompaSelect != null) {
            modelo.addElement(" ");
            modelo.addElement("COMPAÑIA SELECCIONADA: ");
            modelo.addElement(VerAeropuertos.CompaSelect.toString());
        }


        Datos.setModel(modelo);

        Comprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(SelectAsie != null) {
                        ultimopasajero = operaciones.getUltimoIdPasajero() + 1 ;

                        operaciones.insertarDatos(ultimopasajero,NombreUsuario.getText(),
                                PasaporteUsuario.getText(),NacionalidadUsuario.getText());

                        operaciones.VueloPasaje( operaciones.getUltimoIdVueloPasa()+ 1, verAeropuertos.indice + 1 , verAeropuertos.indice2);

                        InterfazPrincipal interfazPrincipal = new InterfazPrincipal();
                        interfazPrincipal.setVisible(true);
                        dispose();

                    }else{
                        JOptionPane.showMessageDialog(null, "Seleccione un Asiento Primero");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error");
                }

            }

        });


        Volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerAeropuertos verAeropuertos = new VerAeropuertos();
                verAeropuertos.setVisible(true);
                dispose();
            }
        });


        Vuelos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount() == 1) {
                    VueloSelect = Vuelos.getSelectedValue();
                }
            }
        });

        ActionListener ListaAsiento = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedSeat = (JButton) e.getSource();
                AsientoSeleccionado = clickedSeat.getText();
            }
        };

        A1.addActionListener(ListaAsiento);
        A4.addActionListener(ListaAsiento);
        A2.addActionListener(ListaAsiento);
        A3.addActionListener(ListaAsiento);
        B1.addActionListener(ListaAsiento);
        B4.addActionListener(ListaAsiento);
        B2.addActionListener(ListaAsiento);
        B3.addActionListener(ListaAsiento);
        C1.addActionListener(ListaAsiento);
        C4.addActionListener(ListaAsiento);
        C2.addActionListener(ListaAsiento);
        C3.addActionListener(ListaAsiento);
        D1.addActionListener(ListaAsiento);
        D4.addActionListener(ListaAsiento);
        D2.addActionListener(ListaAsiento);
        D3.addActionListener(ListaAsiento);
        E1.addActionListener(ListaAsiento);
        E4.addActionListener(ListaAsiento);
        E2.addActionListener(ListaAsiento);
        E3.addActionListener(ListaAsiento);
        F1.addActionListener(ListaAsiento);
        F2.addActionListener(ListaAsiento);
        G1.addActionListener(ListaAsiento);
        G2.addActionListener(ListaAsiento);




        SelectAsie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!AsientoSeleccionado.equals(" ")) {
                    // Find the corresponding button
                    JButton selectedButton = null;
                    switch (AsientoSeleccionado) {
                        case "A1":
                            selectedButton = A1;
                            break;
                        case "A2":
                            selectedButton = A2;
                            break;
                        case "A3":
                            selectedButton = A3;
                            break;
                        case "A4":
                            selectedButton = A4;
                            break;
                        case "B1":
                            selectedButton = B1;
                            break;
                        case "B2":
                            selectedButton = B2;
                            break;
                        case "B3":
                            selectedButton = B3;
                            break;
                        case "B4":
                            selectedButton = B4;
                            break;
                        case "C1":
                            selectedButton = C1;
                            break;
                        case "C2":
                            selectedButton = C2;
                            break;
                        case "C3":
                            selectedButton = C3;
                            break;
                        case "C4":
                            selectedButton = C4;
                            break;
                        case "D1":
                            selectedButton = D1;
                            break;
                        case "D2":
                            selectedButton = D2;
                            break;
                        case "D3":
                            selectedButton = D3;
                            break;
                        case "D4":
                            selectedButton = D4;
                            break;
                        case "E1":
                            selectedButton = E1;
                            break;
                        case "E2":
                            selectedButton = E2;
                            break;
                        case "E3":
                            selectedButton = E3;
                            break;
                        case "E4":
                            selectedButton = E4;
                            break;
                        case "F1":
                            selectedButton = F1;
                            break;
                        case "F2":
                            selectedButton = F2;
                            break;
                        case "G1":
                            selectedButton = G1;
                            break;
                        case "G2":
                            selectedButton = G2;
                            break;
                    }
                    if (selectedButton != null) {
                        selectedButton.setEnabled(false);
                    }
                    JOptionPane.showMessageDialog(null, "ASIENTO: " + AsientoSeleccionado + " SELECCIONADO");

                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un asiento primero");
                }
            }
        });
    }
}
