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
    
    private int numero;
    private Reserva reserva;
    private Usuario usuario;
    
    public Silla(int numero, String tramo, double precio) {
        super(tramo, precio);
        this.numero = numero;
    }

    //Getters & Setters
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public String toString() {
        return "Silla{" + 
               "numero=" + numero + 
               ", tramo='" + super.getTramo() + '\'' + 
               ", precio=" + super.getPrecio() + 
               ", reserva=" + (reserva != null ? reserva.toString() : "null") +
               ", usuario=" + (usuario != null ? usuario.toString() : "null") +
               '}';
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 31 * hash + numero;
        return hash;
    }
    
    public boolean equals(Object obj) {
        // Verificar referencia
        if (this == obj) return true;
        // Verificar que no sea null y que sea de la misma clase
        if (obj == null || getClass() != obj.getClass()) return false;
        // Comparar atributos únicos y heredados
        Silla other = (Silla) obj;
        return super.equals(other) && this.numero == other.numero;
    }
    
    //Métodos de ordenamiento tanto ascendente como descendente de precio 
    public int compareTo(Tramo o) {
        return Double.compare(this.getPrecio(), o.getPrecio());
    }
    
    public int compareToDesc(Tramo o){
        return Double.compare(o.getPrecio(), this.getPrecio());
    }
    
}