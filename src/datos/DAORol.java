/*
 * DAOUsusarios.
 * Operaciones de acceso a datos para la entidad Usuario
 */
package datos;

import com.mysql.cj.jdbc.Driver;
import entidades.Rol;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class DAORol {

    public List<Rol> consultarRoles() {
        Connection conn = null;
        List<Rol> roles = new ArrayList();
        try {
            conn = ConexionBD.conectarBD();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from rol");
            while (rs.next()) {
                Rol u = new Rol(rs.getString("rol"));
                roles.add(u);
            }
        } catch (SQLException e) {
            System.err.println("consultarRoles: " + e.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
        return roles;
    }
    
}
