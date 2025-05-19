/*
 * DAOUsusarios.
 * Operaciones de acceso a datos para la entidad Usuario
 */
package datos;

import com.mysql.cj.jdbc.Driver;
import entidades.Factura;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kevin
 */
public class DAOFactura {

    public List<Factura> consultarFacturas(){
        Connection conn = null;
        List<Factura> facturas = new ArrayList();
        try {
            conn = ConexionBD.conectarBD();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from factura");
            while (rs.next()) {
                Factura f = new Factura(rs.getInt("codigo"), rs.getDate("fecha_emision").toLocalDate());
                f.setIdReserva(rs.getInt("id_reserva"));
                facturas.add(f);
            }
        } catch (SQLException e) {
            System.err.println("consultarFacturas: " + e.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
        return facturas;
    }
}
