/*
 * DAOUsusarios.
 * Operaciones de acceso a datos para la entidad Usuario
 */
package datos;

import entidades.Reserva;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList; 
import java.util.List;

/**
 *
 * @author kevin
 */
public class DAOReserva {

    public List<Reserva> consultarReserva(){
        Connection conn = null;
        List<Reserva> reservas = new ArrayList();
        try {
            conn = ConexionBD.conectarBD();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from reserva");
            while (rs.next()) {
                Reserva r = new Reserva(rs.getDate("fecha_reserva").toLocalDate(), rs.getDouble("precio_total"), rs.getString("login_usuario"));
                reservas.add(r);
            }
        } catch (SQLException e) {
            System.err.println("consultarFacturas: " + e.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
        return reservas;
    }
    
    public void eliminarReserva(Integer id) {
        Connection conn = null;
        try {
            conn = ConexionBD.conectarBD();
            PreparedStatement pst = conn.prepareStatement("DELETE FROM reserva WHERE id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("eliminarReserva: " + e.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
    }
    
    public void reservarSillas(Reserva r) {
        Connection conn = null;
        try {
            conn = ConexionBD.conectarBD();
            PreparedStatement pst = conn.prepareStatement("insert into reserva (id, fecha_reserva, precio_total, login_usuario) values(? ,?, ?, ?)");
            pst.setInt(1, r.getId());
            pst.setDate(2, Date.valueOf(r.getFechaReserva()));
            pst.setDouble(3, r.getPrecioTotal());
            pst.setString(4, r.getUsuario().getLogin()); //preguntar
            pst.execute();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select max(id) from reserva");
            if (rs.next()) {
                r.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.err.println("insertarReserva: " + ex.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
    }
}
