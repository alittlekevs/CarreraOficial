/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package entidades;

/**
 *
 * @author Kevin
 */
public class Rol {
    
    //Clase que permite añadir los que roles que sean necesarios. En este programa solo he contemplado dos (admin, usuario)
    //He pensado que pueda ser escalable
    
    private String rol;
    
    public Rol (String rol){
       this.rol = rol;
    }
    
    //Getters & Setters
    public String getRol(){
        return rol;
    }
    
    public void setRol(String rol){
        this.rol = rol;
    }
    
    public String toString() {
        // Representar el objeto como una cadena
        return "Rol{" + "rol='" + rol + '\'' + '}';
    }

}
