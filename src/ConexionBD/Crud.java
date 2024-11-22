package ConexionBD;

import FrameBD.ComprarBoletos;
import FrameBD.VerAeropuertos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;

public class Crud {
    Connection con;

    ArrayList IdPasajero = new ArrayList();
    DefaultListModel  modelIdPasajero = new DefaultListModel();


    ArrayList ID = new ArrayList();
    DefaultListModel IDm = new DefaultListModel();

    ArrayList datos = new ArrayList();
    DefaultListModel modelo = new DefaultListModel();

    ArrayList IDcompa = new ArrayList();
    DefaultListModel IDmCompa = new DefaultListModel();

    ArrayList datosCompa = new ArrayList();
    DefaultListModel modeloCompa = new DefaultListModel();

    ArrayList datosVuelos = new ArrayList();
    DefaultListModel modeloVuelos = new DefaultListModel();

    ArrayList IDvuelos = new ArrayList();
    DefaultListModel IDmVuelos = new DefaultListModel();

    DefaultListModel ModeCompaPar = new DefaultListModel();


    private boolean existeId(int id, String tabla, String columnaId) {
        String query = "SELECT COUNT(*) FROM u984447967_op2024b." + tabla + " WHERE " + columnaId + " = ?";
        try {
            Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertar(int id, String nombre, String ciudad, String Pais, String publico, String privado, int subvencion) {
        if (existeId(id, "aeropuertos", "idAeropuerto")) {
            JOptionPane.showMessageDialog(null, "Error: El ID " + id + " ya existe en la tabla aeropuertos");
            return false;
        }

        String query = "INSERT INTO u984447967_op2024b.aeropuertos (idAeropuerto, nombre, ciudad, pais, privado, publico, subvencion) VALUES (?,?,?,?,?,?,?)";
        try {
            Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, ciudad);
            ps.setString(4, Pais);
            ps.setString(5, publico);
            ps.setString(6, privado);
            ps.setInt(7, subvencion);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Aeropuerto registrado exitosamente");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar aeropuerto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertarCompania(int idCompania, String nombre) {
        if (existeId(idCompania, "companias", "idCompania")) {
            JOptionPane.showMessageDialog(null, "Error: El ID " + idCompania + " ya existe en la tabla companias");
            return false;
        }

        String query1 = "INSERT INTO u984447967_op2024b.companias (idCompania, nombre) VALUES (?,?)";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(query1);
            ps.setInt(1, idCompania);
            ps.setString(2, nombre);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Compañía registrada exitosamente");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar compañía: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertarVuelo(int idVuelo, String identificador, String ciudadOrigen, String ciudadDestino, String precio, String NumMaxPasajeros) {
        if (existeId(idVuelo, "vuelos", "idVuelo")) {
            JOptionPane.showMessageDialog(null, "Error: El ID " + idVuelo + " ya existe en la tabla vuelos");
            return false;
        }

        String query2 = "INSERT INTO u984447967_op2024b.vuelos (idVuelo, identificador, ciudadOrigen, ciudadDestino, precio, NumMaxPasajeros) VALUES (?,?,?,?,?,?)";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(query2);
            ps.setInt(1, idVuelo);
            ps.setString(2, identificador);
            ps.setString(3, ciudadOrigen);
            ps.setString(4, ciudadDestino);
            ps.setString(5, precio);
            ps.setString(6, NumMaxPasajeros);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vuelo registrado exitosamente");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar vuelo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }



    }
    public boolean AeroCompa(int id, int idAeropuerto, int idCompania){
        if (existeId(id, "aeropuertos_companias", "id")) {
            JOptionPane.showMessageDialog(null, "Error: El ID " + id + " ya existe en la relacion");
            return false;
        }


        String query3 = "INSERT INTO u984447967_op2024b.aeropuertos_companias (id, idAeropuerto, idCompania) VALUES (?,?,?)";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(query3);
            ps.setInt(1, id);
            ps.setInt(2, idAeropuerto);
            ps.setInt(3, idCompania);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Relacion registrada correctamente");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al relacionar aeropuerto con compañias: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean CompaVuelo(int id, int idCompania, int idVuelo){
        if (existeId(id, "compania_vuelos", "id")) {
            JOptionPane.showMessageDialog(null, "Error: El ID " + id + " ya existe en la relacion");
            return false;
        }


        String queryRV = "INSERT INTO u984447967_op2024b.compania_vuelos (id, idCompania, Idvuelo) VALUES (?,?,?)";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryRV);
            ps.setInt(1, id);
            ps.setInt(2, idCompania);
            ps.setInt(3, idVuelo);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Relacion registrada correctamente");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al relacionar Compañias con vuelos: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean VueloPasaje(int id, int idVuelo, int idPasajero){
        if (existeId(id, "vuelos_pasajeros", "id")) {
            JOptionPane.showMessageDialog(null, "Error: El ID " + id + " ya existe en la relacion");
            return false;
        }


        String queryRV = "INSERT INTO u984447967_op2024b.vuelos_pasajeros (id, idVuelo, IdPasajero) VALUES (?,?,?)";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryRV);
            ps.setInt(1, id);
            ps.setInt(2, idVuelo);
            ps.setInt(3, idPasajero);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Relacion registrada correctamente");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al relacionar Vuelos con Pasajeros: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertarDatos( int idPasajero, String nombre, String pasaporte, String nacionalidad){
        String queryI = "INSERT INTO  u984447967_op2024b.pasajeros (idPasajero, nombre, pasaporte, nacionalidad) VALUES (?,?,?,?)";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryI);
            ps.setInt(1, idPasajero);
            ps.setString(2, nombre);
            ps.setString(3, pasaporte);
            ps.setString(4, nacionalidad);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Boleto Registrado Correctamente");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Comprar el boleto " + e.getMessage());
            e.printStackTrace();
            return false;
        }


    }

    public VerAeropuertos leerDatos(){
        String queryD = "SELECT * FROM u984447967_op2024b.aeropuertos LIMIT 10";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idAeropuerto");
                String nombre = rs.getString("nombre");
                String ciudad = rs.getString("ciudad");
                String pais = rs.getString("pais");
                int privado = rs.getInt("privado");
                int publico = rs.getInt("publico");
                int subvencion = rs.getInt("subvencion");
                if(privado == 1){
                    ID.add(id);
                    datos.add("Aeropuerto Privado: " + nombre +" " +"Ubicado en: " + pais + " / " +
                            ciudad + " " );
                }else if (publico == 1){
                    ID.add(id);
                    datos.add("Aeropuerto Publico: \n" + nombre +" " +"Ubicado en: " + pais + " / " +
                            ciudad + " " + subvencion );
                }

            }

            for (int i = 0; i < datos.size() ; i++) {
                IDm.addElement(ID.get(i));
                modelo.addElement(datos.get(i));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Aeropuertos " + e.getMessage());
            e.printStackTrace();

        }
        return null;
    }
    public VerAeropuertos leerDatosCompa(){
        String queryD = "SELECT * FROM u984447967_op2024b.companias LIMIT 10";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryD);
            modeloCompa.removeAllElements();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idCompania");
                String nombre = rs.getString("nombre");
                    IDcompa.add(id);
                    datosCompa.add("Compañias: " + nombre);
            }

            for (int i = 0; i <ID.size() ; i++) {
                IDmCompa.addElement(ID.get(i));
                modeloCompa.addElement(datosCompa.get(i));
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Compañias " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public ComprarBoletos leerVuelos(){
        String queryE = "SELECT * FROM u984447967_op2024b.vuelos LIMIT 4";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryE);
            modeloVuelos.removeAllElements();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idVuelo");
                String identificador = rs.getString("identificador");
                String ciudadOrigen = rs.getString("ciudadOrigen");
                String ciudadDestino = rs.getString("ciudadDestino");
                String precio = rs.getString("precio");
                String numMaxPasajero = rs.getString("numMaxPasajeros");
                IDvuelos.add(id);
                datosVuelos.add("Identificador De vuelo: " + identificador + " " + " Ciudad Origen: " + ciudadOrigen +
                        " " + " Ciudad Destino: " + ciudadDestino + " " + " Precio : " + precio + " " +
                        " Numero Maximo de Pasajeros: " + numMaxPasajero);
            }
            for (int i = 0; i <IDvuelos.size() ; i++) {
                IDmVuelos.addElement(IDvuelos.get(i));
                modeloVuelos.addElement(datosVuelos.get(i));
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Vuelos " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public int getUltimoIdPasajero() {
        String queryID = "SELECT MAX(idPasajero) AS ultimo_id FROM u984447967_op2024b.pasajeros";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("ultimo_id");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener último ID " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
    public int getUltimoCompania() {
        String queryIDcompa = "SELECT MAX(idCompania) AS ultimo_idCompania FROM u984447967_op2024b.companias";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryIDcompa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("ultimo_idCompania");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener último ID " + e.getMessage());
            e.printStackTrace();
        }
        return 0;

    }
    public int getUltimoIdAeropuerto() {
        String queryID = "SELECT MAX(idAeropuerto) AS ultimo_idAero FROM u984447967_op2024b.aeropuertos";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("ultimo_idAero");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener último ID " + e.getMessage());
            e.printStackTrace();
        }
        return 0;

    }
    public int getUltimoVuelo() {
        String queryIDvuelo = "SELECT MAX(idVuelo) AS ultimo_idVuelo FROM u984447967_op2024b.vuelos";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryIDvuelo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("ultimo_idVuelo");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener último ID " + e.getMessage());
            e.printStackTrace();
        }
        return 0;

    }
    public int getUltimoIdAeroCompania() {
        String queryIDAeroCompa = "SELECT MAX(id) AS ultimo_idAeroCompa FROM u984447967_op2024b.aeropuertos_companias";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryIDAeroCompa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("ultimo_idAeroCompa");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener último ID " + e.getMessage());
            e.printStackTrace();
        }
        return 0;

    }
    public int getUltimoIdCompaVuelo() {
        String queryIDCompavuelo = "SELECT MAX(id) AS ultimo_idCompaVuelo FROM u984447967_op2024b.compania_vuelos";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryIDCompavuelo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("ultimo_idCompaVuelo");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener último ID " + e.getMessage());
            e.printStackTrace();
        }
        return 0;

    }
    public int getUltimoIdVueloPasa() {
        String queryIDvuelopasaje = "SELECT MAX(id) AS ultimo_idVueloPasaje FROM u984447967_op2024b.vuelos_pasajeros";
        try {
            con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(queryIDvuelopasaje);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("ultimo_idVueloPasaje");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener último ID " + e.getMessage());
            e.printStackTrace();
        }
        return 0;

    }



    public ArrayList getID() {
        return ID;
    }

    public void setID(ArrayList ID) {
        this.ID = ID;
    }

    public DefaultListModel getIDm() {
        return IDm;
    }

    public void setIDm(DefaultListModel IDm) {
        this.IDm = IDm;
    }

    public ArrayList getIDcompa() {
        return IDcompa;
    }

    public void setIDcompa(ArrayList IDcompa) {
        this.IDcompa = IDcompa;
    }

    public DefaultListModel getIDmCompa() {
        return IDmCompa;
    }

    public void setIDmCompa(DefaultListModel IDmCompa) {
        this.IDmCompa = IDmCompa;
    }

    public DefaultListModel getModeCompaPar() {
        return ModeCompaPar;
    }

    public void setModeCompaPar(DefaultListModel modeCompaPar) {
        ModeCompaPar = modeCompaPar;
    }

    public ArrayList getDatos() {
        return datos;
    }

    public void setDatos(ArrayList datos) {
        this.datos = datos;
    }

    public DefaultListModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultListModel modelo) {
        this.modelo = modelo;
    }

    public ArrayList getDatosCompa() {
        return datosCompa;
    }

    public void setDatosCompa(ArrayList datosCompa) {
        this.datosCompa = datosCompa;
    }

    public DefaultListModel getModeloCompa() {
        return modeloCompa;
    }

    public void setModeloCompa(DefaultListModel modeloCompa) {
        this.modeloCompa = modeloCompa;
    }

    public ArrayList getIDvuelos() {
        return IDvuelos;
    }

    public void setIDvuelos(ArrayList IDvuelos) {
        this.IDvuelos = IDvuelos;
    }

    public DefaultListModel getIDmVuelos() {
        return IDmVuelos;
    }

    public void setIDmVuelos(DefaultListModel IDmVuelos) {
        this.IDmVuelos = IDmVuelos;
    }

    public DefaultListModel getModeloVuelos() {
        return modeloVuelos;
    }

    public void setModeloVuelos(DefaultListModel modeloVuelos) {
        this.modeloVuelos = modeloVuelos;
    }

    public ArrayList getDatosVuelos() {
        return datosVuelos;
    }

    public void setDatosVuelos(ArrayList datosVuelos) {
        this.datosVuelos = datosVuelos;
    }

    public ArrayList getIdPasajero() {
        return IdPasajero;
    }

    public void setIdPasajero(ArrayList idPasajero) {
        IdPasajero = idPasajero;
    }

    public DefaultListModel getModelIdPasajero() {
        return modelIdPasajero;
    }

    public void setModelIdPasajero(DefaultListModel modelIdPasajero) {
        this.modelIdPasajero = modelIdPasajero;
    }
}