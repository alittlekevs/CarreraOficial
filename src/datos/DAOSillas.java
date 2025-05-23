/*
 * DAOUsusarios.
 * Operaciones de acceso a datos para la entidad Usuario
 */
package datos;

import entidades.Reserva;
import entidades.Silla;
import entidades.Tramo;
import entidades.Usuario;
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

    //Búsqueda: consultarSillasSinReserva(Todas las sillas con idReserva = null)
    //Búsqueda: consultarSillas(Todas las sillas)
    //Búsqueda: consultarTramos()
    //Update: actualizarSillas() - pendiente
    
    //búsqueda
    public List<Silla> consultarSillasSinReserva() {
        Connection conn = null;
        List<Silla> sillasSinReserva = new ArrayList();
        try {
            conn = ConexionBD.conectarBD();
            PreparedStatement pst = conn.prepareStatement("select * from silla where id_reserva is null");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Silla s = new Silla(rs.getInt("numero"), new Tramo(rs.getString("tramo"), rs.getDouble("precio")),
                    new Usuario(rs.getString("login_usuario"), null, null), null);
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
                Silla s = new Silla(rs.getInt("numero"), new Tramo(rs.getString("tramo"), rs.getDouble("precio")),
                    new Usuario(rs.getString("login_usuario"), null, null), new Reserva(rs.getInt("id_reserva")));
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
    
    private List<Silla> consultarSillasReservaPorId(Connection conn, int idReserva) throws SQLException{
        List<Silla> sillas = new ArrayList<>();
        String sql = "SELECT numero, tramo, precio, login_usuario FROM silla WHERE id_reserva = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idReserva);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int numero = rs.getInt("numero");
                String tramoStr = rs.getString("tramo");
                double precio = rs.getDouble("precio");
                String loginUsuario = rs.getString("login_usuario");
                entidades.Tramo tramo = new entidades.Tramo(tramoStr, precio);
                Usuario usuario = new Usuario(loginUsuario, null, null);
                Silla silla = new Silla(numero, tramo, usuario, null);
                sillas.add(silla);
            }
        }
        return sillas;
    }
    
    //TODO
    public void actualizarSillas(Silla s){
    
    }
}
