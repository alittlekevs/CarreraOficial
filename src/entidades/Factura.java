/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Kevin
 */
public class Factura {
    private int codigo;
    private LocalDate fechaEmision;
    private int idReserva;
    
    public Factura(int codigo, LocalDate fechaEmision){
        this.codigo = codigo;
        this.fechaEmision = fechaEmision;
    }
    
    //Getters & Setters
    public int getCodigo(){
        return codigo;
    }
    
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    
    public LocalDate getFechaEmision(){
        return fechaEmision;
    }
    
    public void setFechaEmision(LocalDate fechaEmision){
        this.fechaEmision = fechaEmision;
    }
    
    public int getIdReserva(){
        return idReserva;
    }
     
    public void setIdReserva(Integer idReserva){
        this.idReserva = idReserva;
    }
    
    //Métodos
    public String generarFicheroFactura(String loginUsuario, List<String> listaSillas, double precioTotal) throws IOException {
        // Construir el contenido de la factura
        StringBuilder factura = new StringBuilder();
        factura.append("Factura de Reserva\n");
        factura.append("===================\n");
        factura.append("Código Factura: ").append(codigo).append("\n");
        factura.append("Fecha de Emisión: ").append(fechaEmision).append("\n");
        factura.append("Usuario: ").append(loginUsuario).append("\n\n");
        factura.append("Sillas Reservadas:\n");
        for (String detalle : listaSillas) {
            factura.append("- ").append(detalle).append("\n");
        }
        factura.append("\nPrecio Total: ").append(String.format("%.2f €", precioTotal)).append("\n");

        // Generar el fichero ASCII
        String fileName = "factura_reserva_" + loginUsuario + "_" + codigo + ".txt";
        String filePath = System.getProperty("user.home") + "/" + fileName; // Guardar en la carpeta del usuario
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(factura.toString());
        }

        return filePath;
    }
}
