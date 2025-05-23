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
    private int id;
    private LocalDate fechaReserva;
    private Double precioTotal;
    private Usuario usuario;
    private List<Silla> sillasReservadas;
    
    public Reserva(LocalDate fechaReserva, Double precioTotal, String loginUsuario){
        this.fechaReserva = fechaReserva;
        this.precioTotal = precioTotal;
    }
    
    public Reserva(LocalDate fechaReserva, Double precioTotal, String loginUsuario, List<Silla> sillasReservadas){
        this.fechaReserva = fechaReserva;
        this.precioTotal = precioTotal;
        //this.loginUsuario = loginUsuario; //preguntar
        this.sillasReservadas = sillasReservadas; //Para generar el documento factura y asignar los update
    }
    
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
                .mapToDouble(silla -> silla.getPrecio()).sum();
        return precioTotal; 
    }
    
    //TODO fichero
//    public String generarFicheroFactura(String loginUsuario, List<String> listaSillas, double precioTotal) throws IOException {
//        
//        // Generar el fichero ASCII
//        String fileName = "factura_reserva_" + loginUsuario + "_" + codigo + ".txt";
//        String filePath = System.getProperty("user.home") + "/" + fileName; // Guardar en la carpeta del usuario
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//            writer.write(factura.toString());
//        }
//
//        return filePath;
//    }
    
    
    public String toString() {
        return String.format("Reserva ID: %d, Fecha: %s, Usuario: %s, Total: %.2f€", 
                id, fechaReserva, usuario.getLogin(), precioTotal);
    }
    
    //TODO hashcode y equals
}
