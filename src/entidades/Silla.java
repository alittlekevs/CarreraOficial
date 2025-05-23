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
public class Silla implements Comparable<Silla>{
    
    //1.Eliminada herencia con padre Tramo
    //2.Añadido comparable para ordenamiento
    //3.Modificado el constructor respecto a la eliminación de la herencia
    
    //Atributos
    private int numero; //auto_incrementado
    private Reserva reserva;
    private Usuario usuario;
    private Tramo tramo;
    
    //Constructor
    public Silla(int numero, Tramo tramo, Usuario usuario, Reserva reserva) {
        this.numero = numero;
        this.tramo = tramo;
        this.usuario = usuario;
        this.reserva = reserva;
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
    
    public Tramo getTramo() {
        return tramo;
    }

    public void setTramo(Tramo tramo ) {
        this.tramo = tramo;
    }
    
    @Override
    public String toString() {
        return "Silla{" +
               "numero=" + numero +
               ", tramo='" + (tramo != null ? tramo.getTramo() : "null") + '\'' +
               ", precio=" + (tramo != null ? tramo.getPrecio() : "null") +
               ", usuario='" + (usuario != null ? usuario.getLogin() : "null") + '\'' +
               ", idReserva=" + (reserva != null ? reserva.getId() : "null") +
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
    //Ascendente
    public int compareTo(Silla otra) {
        return Double.compare(this.getTramo().getPrecio(), otra.getTramo().getPrecio());
    }

    //Descendente
    public int compareToDesc(Silla otra) {
        return Double.compare(otra.getTramo().getPrecio(), this.getTramo().getPrecio());
    }
    
    //Orden por número
    public int compareToNumero(Silla otra) {
        return Integer.compare(this.getNumero(), otra.getNumero());
    }
}