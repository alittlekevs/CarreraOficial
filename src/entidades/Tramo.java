/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Kevin
 */
public class Tramo implements Comparable<Tramo>{
    
    //Clase en la cual se asigna el precio de todas las sillas de ese tramo. Según la documentación oficial
    //de carrera oficial https://gestionabonos.hermandades-de-sevilla.org/configuracion/inicio.do;jsessionid=32B8CE91A3681DF37D889675E6F8F5E5
    //Cada tramo tiene asignado un precio con todas las sillas a ese precio.
    
    //Eliminada hija Silla, todo lo demás igual.
    
    //Atributos
    private String tramo;
    private double precio;
    
    //Constructor
    public Tramo(String tramo, double precio){
        this.tramo = tramo;
        this.precio = precio;
    }
    
    //Getters & Setters
    public String getTramo(){
        return tramo;
    }
    
    public void setTramo(String tramo){
        this.tramo = tramo;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public void setPrecio(Double precio){
        this.precio = precio;
    }
    
    //Ordenamiento por precio 
    public int compareTo(Tramo o) {
        return Double.compare(this.precio, o.precio);
    }
    
    public boolean equals(Object obj) {
        // Verificar referencia
        if (this == obj) return true;
        // Verificar que no sea null y que sea de la misma clase
        if (obj == null || getClass() != obj.getClass()) return false;
        // Comparar los atributos relevantes (tramo y precio)
        Tramo other = (Tramo) obj;
        return tramo.equals(other.tramo) && Double.compare(precio, other.precio) == 0;
    }

    public int hashCode() {
        // Generar código hash basado en tramo y precio
        int hash = 7;
        hash = 31 * hash + (tramo != null ? tramo.hashCode() : 0);
        hash = 31 * hash + Double.hashCode(precio);
        return hash;
    }

    public String toString() {
        return "Tramo{" + "tramo='" + tramo + '\'' + ", precio=" + precio + '}';
    }
}
