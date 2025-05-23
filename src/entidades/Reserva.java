/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Kevin
 */
public class Reserva {
    
    //Atributos
    private int id; //auto_incrementado
    private LocalDate fechaReserva;
    private Double precioTotal;
    private Usuario usuario;
    private List<Silla> sillasReservadas;
    
    //Constructores
    public Reserva(LocalDate fechaReserva, Usuario usuario, List<Silla> sillasReservadas){
        this.fechaReserva = fechaReserva;
        this.usuario = usuario;
        this.sillasReservadas = sillasReservadas;
        //this.precioTotal = calcularPrecioTotal();
        
    }
    
    public Reserva(int id){
        this.id = id;
    }
    //Fin constructores
    
    //Getters & Setters
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public LocalDate getFechaReserva(){
        return fechaReserva;
    }
    
    public void setFechaReserva(LocalDate fechaReserva){
        this.fechaReserva = fechaReserva;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public void añadirSilla(Silla silla){
        sillasReservadas.add(silla);
    }
    
    public List<Silla> getSillas(){
        return new ArrayList<>(sillasReservadas);
    }
    
    //Calculamos el precio total a partir de todas las sillas que haya reservado
    public double getPrecioTotal(){
        precioTotal = sillasReservadas.stream()
                .mapToDouble(silla -> silla.getTramo().getPrecio()).sum();
        return precioTotal; 
    }
    
    //Creación de Fichero .txt
    public void generarReserva() {
        String rutaArchivo = System.getProperty("user.home") + "/Desktop/reserva_" + this.id + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write("Detalle de Sillas Reservadas:\n");
            for (Silla silla : sillasReservadas) {
                writer.write(
                    "Silla: " +
                    silla.getNumero() +
                    " | Tramo: " + silla.getTramo().getTramo() +
                    " | Precio: " + silla.getTramo().getPrecio() +
                    " | Usuario: " + (silla.getUsuario() != null ? silla.getUsuario().getLogin() : "N/A") +
                    " | ID Reserva: " + this.id +
                    "\n"
                );
            }
            writer.write("\nPrecio total: " + getPrecioTotal() + " €");
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de la reserva: " + e.getMessage());
        }
    }

    
    public String toString() {
        return String.format("Reserva ID: %d, Fecha: %s, Usuario: %s, Total: %.2f€", 
                id, fechaReserva, usuario != null ? usuario.getLogin() : "N/A", getPrecioTotal());
    }
    
    //TODO hashcode y equals
}
