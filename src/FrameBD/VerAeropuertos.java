package FrameBD;

import ConexionBD.Crud;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerAeropuertos extends JFrame {
    private JPanel AeroDispo;
    private JList Datos;
    private JList DatosCompa;
    private JButton SeleccAero;
    private JButton Volver;
    private JButton SeleccCompa;
    private JButton Continuar;
    private JFrame Mensaje;
    public static int indice = 0;
    public static int indice2 = 0;
    int permiso = 0;
    int permiso2 = 0;

    public static Object AeroSelect;
    public static Object IDaero;
    public static Object CompaSelect;
    public static Object IDCompa;
    Crud operaciones = new Crud();

    public VerAeropuertos() {
        setTitle("Ver Aeropuertos");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(AeroDispo);

        operaciones.leerDatos();
        Datos.setModel(operaciones.getModelo());
        Datos.setVisible(true);



        Continuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(permiso == 1 && permiso2 == 0) {
                    JOptionPane.showMessageDialog(Mensaje,
                            "No has seleccionado una Compañia",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }else  if(permiso == 0 && permiso2 == 1){
                    JOptionPane.showMessageDialog(Mensaje,
                            "No has seleccionado un Aeropuerto",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }else  if(permiso == 1 && permiso2 == 1){
                    ComprarBoletos comprarBoletos = new ComprarBoletos();
                    comprarBoletos.setVisible(true);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(Mensaje,
                            "No has seleccionado un Aeropuerto ni una Compañia",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }

                }
        });

        Volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazPrincipal interfazPrincipal = new InterfazPrincipal();
                interfazPrincipal.setVisible(true);
                dispose();
            }
        });


        Datos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount() == 1) {

                    AeroSelect = Datos.getSelectedValue();
                    indice = Datos.getSelectedIndex();
                    permiso = 1;

                }
            }
        });
        DatosCompa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount() == 1) {
                    CompaSelect = DatosCompa.getSelectedValue();
                    indice2 = DatosCompa.getSelectedIndex();
                    permiso2 = 1;
                }
            }


        });
        SeleccAero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(permiso == 1){
                    Datos.setSelectedValue(AeroSelect, true);
                    Datos.setSelectedIndex(indice);
                    System.out.println(indice);
                    JOptionPane.showMessageDialog(Mensaje,
                            "Aeropuerto Seleccionado",
                            "Registro Exitoso",
                            JOptionPane.INFORMATION_MESSAGE);

                    operaciones.leerDatosCompa();

                    DatosCompa.setModel(operaciones.getModeloCompa());
                    DatosCompa.setVisible(true);

                }
            }
        });


        SeleccCompa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             if(permiso2 == 1){
                 DatosCompa.setSelectedValue(CompaSelect, true);
                 DatosCompa.setSelectedIndex(indice2);
                 System.out.println(indice2);
                 JOptionPane.showMessageDialog(Mensaje,
                         "Compañia Seleccionada",
                         "Registro Exitoso",
                         JOptionPane.INFORMATION_MESSAGE);
             }
            }
        });

    }

}
