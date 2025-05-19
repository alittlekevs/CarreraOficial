/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Kevin
 */
public class Silla extends Tramo {
    private double numero;
    private List<Silla> sillas;
    
    public Silla(int numero, String tramo, int precio) {
        super(tramo, precio);
        this.numero = numero;
        //sillas = new ArrayList();
    }

    //Getters & Setters
    public double getNumero() {
        return numero;
    }
    
    public void setNumero(double numero){
        this.numero = numero;
    }
    
    //Calcular precio total de una lista de Sillas
    public double calcularPrecioTotal() {
        if (sillas == null || sillas.isEmpty()) {
            return 0.0; 
        }
        return sillas.stream()
                .mapToDouble(silla -> silla.getPrecio()).sum(); 
    }
    
    @Override
    public String toString() {
        return "Número: "+numero +",Tramo: "+super.getTramo()+",Precio: "+super.getPrecio()+"€";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return 41* hash + Objects.hashCode(this.numero);
    }

    @Override
    //Comprobamos si dos sillas tienen el mismo número
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
        final Silla other = (Silla) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }
    
    //Métodos de ordenamiento tanto ascendente como descendente de precio 
    public int compareTo(Tramo o) {
        return Double.compare(this.getPrecio(), o.getPrecio());
    }
    
    public int compareToDesc(Tramo o){
        return Double.compare(o.getPrecio(), this.getPrecio());
    }
    
}