/*
 * Entidad Usuario.
 */
package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author kevin
 */
public class Usuario extends Rol{
    
    //Herencia Usuario -> Rol
    private String login;
    private String password;
    
    //Constructor
    public Usuario(String login, String password, String rol)  {
        super(rol);
        this.login = login;
        this.password = password;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return login;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.login);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.login, other.login);
    }
    
}
