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
    
    private String tramo;
    private double precio;
    
    public Tramo(String tramo, int precio){
        this.tramo = tramo;
        this.precio = precio;
    }
    
    //Getters & Setters
    public String getTramo(){
        return tramo;
    }
    
    public void setTramo(){
        this.tramo = tramo;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public void setPrecio(){
        this.precio = precio;
    }
    
    //Ordenamiento por precio 
    public int compareTo(Tramo o) {
        return Double.compare(this.precio, o.precio);
    }
}
