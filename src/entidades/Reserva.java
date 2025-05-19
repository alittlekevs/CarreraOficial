/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin
 */
public class Reserva {
    private int id;
    private LocalDate fechaReserva;
    private double precioTotal;
    private Usuario usuario;
    private List<Silla> sillas;
    private Factura factura;
    
    public Reserva(LocalDate fechaReserva, double precioTotal, Usuario usuario){
        this.fechaReserva = fechaReserva;
        this.precioTotal = precioTotal;
        this.usuario = usuario;
        sillas = new ArrayList();
        int codigoFactura = factura.getCodigo();                              
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
    
    //Calculamos el precio total a partir de todas las sillas que haya reservado
    public double getPrecioTotal(){
        for (Silla s : sillas) {
            precioTotal += s.getPrecio();
        }
        return precioTotal;
    }
    
    private Usuario getUsuario(){
        return usuario;
    }
    
    private void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public void añadirSilla(Silla silla){
        sillas.add(silla);
    }
    
    public List<Silla> getSillas(){
        return sillas;
    }
    
}
