/*
 * DAOUsusarios.
 * Operaciones de acceso a datos para la entidad Usuario
 */
package datos;

import com.mysql.cj.jdbc.Driver;
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
public class DAOUsuarios {

    public Usuario buscarPorLogin(String login) {
        Usuario u = null;
        Connection conn = null;
        try {
            conn = ConexionBD.conectarBD();
            PreparedStatement pst = conn.prepareStatement("select * from usuario where login = ?");
            pst.setString(1, login);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                u = new Usuario(rs.getString("login"), rs.getString("contraseña"), rs.getString("rol"));
            }
        } catch (SQLException e) {
            System.err.println("buscarPorLogin: " + e.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
        return u;
    }
    
    public void insertarUsuario(Usuario u) {
        Connection conn = null;
        try {
            conn = ConexionBD.conectarBD();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO usuario (login, contraseña, rol) VALUES (?, ?, ?)");
            pst.setString(1, u.getLogin());
            pst.setString(2, u.getPassword());
            pst.setString(3, u.getRol());
            pst.execute();
        } catch (SQLException e) {
            System.err.println("insertarUsuario: " + e.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
    }
    
    public List<Usuario> consultarUsuarios() {
        Connection conn = null;
        List<Usuario> usuarios = new ArrayList();
        try {
            conn = ConexionBD.conectarBD();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from usuario");
            while (rs.next()) {
                Usuario u = new Usuario(rs.getString("login"), rs.getString("contraseña"), rs.getString("rol"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.err.println("consultarUsuarios: " + e.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
        return usuarios;
    }
    
    public void eliminarUsuario(String login) {
        Connection conn = null;
        try {
            conn = ConexionBD.conectarBD();
            PreparedStatement pst = conn.prepareStatement("DELETE FROM usuario WHERE login = ?");
            pst.setString(1, login);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("eliminarUsuario: " + e.getMessage());
        } finally {
            ConexionBD.desconectarBD(conn);
        }
    }
}
