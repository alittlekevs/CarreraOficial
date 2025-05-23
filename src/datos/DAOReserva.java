/*
 * DAOUsusarios.
 * Operaciones de acceso a datos para la entidad Usuario
 */
package datos;

import entidades.Reserva;
import entidades.Silla;
import entidades.Usuario;
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

    /*
    * consultarReserva();
    * eliminarReserva();
    * insertarReserva();
    * consultarSillasReservadasPorId()
    * consultarSillasReservadas()
    */
    
    public List<Reserva> consultarReserva(){
        Connection conn = null;
        List<Reserva> reservas = new ArrayList();
        try {
            conn = ConexionBD.conectarBD();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from reserva");
            while (rs.next()) {
                int id = rs.getInt("id");
                Date fecha = rs.getDate("fecha_reserva");
                double precioTotal = rs.getDouble("precio_total");
                String login = rs.getString("login_usuario");
                Usuario usuario = new Usuario(login, null, null);

                // Recuperar las sillas asociadas a la reserva
                List<Silla> sillas = consultarSillasReservaPorId(conn, id);

                Reserva r = new Reserva(fecha.toLocalDate(), usuario, sillas);
                r.setId(id);
                reservas.add(r);
            }
        } catch (SQLException e) {
            System.err.println("consultarFacturas: " + e.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
        return reservas;
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
    
    private List<Silla> consultarSillasReservadas(Connection conn) throws SQLException{
        List<Silla> sillas = new ArrayList<>();
        String sql = "SELECT * FROM silla WHERE id_reserva is not null";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
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
    
    public void insertarReserva(Reserva r) {
        Connection conn = null;
        try {
            conn = ConexionBD.conectarBD();
            PreparedStatement pst = conn.prepareStatement("insert into reserva (id, fecha_reserva, precio_total, login_usuario) values(? ,?, ?, ?)");
            pst.setInt(1, r.getId());
            pst.setDate(2, Date.valueOf(r.getFechaReserva()));
            pst.setDouble(3, r.getPrecioTotal());
            pst.setString(4, r.getUsuario().getLogin());
            pst.execute();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select max(id) from reserva");
            if (rs.next()) {
                r.setId(rs.getInt(1));
            }
            
            // Actualizar las sillas asociadas a la reserva
            if (r.getSillas() != null) {
                for (Silla silla : r.getSillas()) {
                    PreparedStatement pstSilla = conn.prepareStatement(
                        "UPDATE silla SET login_usuario = ?, id_reserva = ? WHERE numero = ?");
                    pstSilla.setString(1, r.getUsuario().getLogin());
                    pstSilla.setInt(2, r.getId());
                    pstSilla.setInt(3, silla.getNumero());
                    pstSilla.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.err.println("insertarReserva: " + ex.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
    }
}
