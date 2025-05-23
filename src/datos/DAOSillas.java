/*
 * DAOUsusarios.
 * Operaciones de acceso a datos para la entidad Usuario
 */
package datos;

import entidades.Silla;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class DAOSillas {

    //búsqueda
    public List<Silla> consultarSillasSinReserva() {
        Connection conn = null;
        List<Silla> sillasSinReserva = new ArrayList();
        try {
            conn = ConexionBD.conectarBD();
            PreparedStatement pst = conn.prepareStatement("select * from silla where id_reserva is null");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Silla s = new Silla(rs.getInt("numero"), rs.getString("tramo"), rs.getInt("precio") );
                sillasSinReserva.add(s);
            }
        } catch (SQLException e) {
            System.err.println("consultarSillasSinReserva: " + e.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
        return sillasSinReserva;
    }
    
    //consultar todas las sillas
    public List<Silla> consultarSillas() {
        Connection conn = null;
        List<Silla> sillasSinReserva = new ArrayList();
        try {
            conn = ConexionBD.conectarBD();
            PreparedStatement pst = conn.prepareStatement("select * from silla");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Silla s = new Silla(rs.getInt("numero"), rs.getString("tramo"), rs.getInt("precio") );
                sillasSinReserva.add(s);
            }
        } catch (SQLException e) {
            System.err.println("consultarSillas: " + e.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
        return sillasSinReserva;
    } 
    
    public List<String> consultarTramos(){
        Connection conn = null;
        List<String> tramos = new ArrayList();
        try {
            conn = ConexionBD.conectarBD();
            PreparedStatement pst = conn.prepareStatement("SELECT DISTINCT tramo from silla");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tramos.add(rs.getString("tramo"));
            }
        } catch (SQLException e) {
            System.err.println("consultarTramo: " + e.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
        return tramos;
    }
    
    //TODO
    public void actualizarSillas(Silla s){
    
    }
}
