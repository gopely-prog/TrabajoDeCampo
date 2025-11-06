package clases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Clase Venta - Similar a Compra pero para ventas
 */
public class Venta {
    private int numeroVenta;
    private String tipoDocumento; // "Boleta" o "Factura"
    private String rucCliente;
    private String razonSocial;
    private String domicilio;
    private ArrayList<DetalleVenta> detalles;
    private double subTotal;
    private double igv;
    private double total;
    private String rutaArchivo;
    private String fecha;
    
    public Venta(int numeroVenta, String tipoDocumento, String rucCliente, 
                 String razonSocial, String domicilio, String rutaArchivo) {
        this.numeroVenta = numeroVenta;
        this.tipoDocumento = tipoDocumento;
        this.rucCliente = rucCliente;
        this.razonSocial = razonSocial;
        this.domicilio = domicilio;
        this.rutaArchivo = rutaArchivo;
        this.detalles = new ArrayList<>();
        this.subTotal = 0;
        this.igv = 0;
        this.total = 0;
        
        // Obtener fecha actual
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.fecha = ahora.format(formato);
    }
    
    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
        calcularTotales();
    }
    
    public void calcularTotales() {
        subTotal = 0;
        for (DetalleVenta detalle : detalles) {
            subTotal += detalle.getSubtotal();
        }
        igv = subTotal * 0.18;
        total = subTotal + igv;
    }
    
    // Getters y Setters
    public int getNumeroVenta() {
        return numeroVenta;
    }
    
    public void setNumeroVenta(int numeroVenta) {
        this.numeroVenta = numeroVenta;
    }
    
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public String getRucCliente() {
        return rucCliente;
    }
    
    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }
    
    public String getRazonSocial() {
        return razonSocial;
    }
    
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    
    public String getDomicilio() {
        return domicilio;
    }
    
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    public ArrayList<DetalleVenta> getDetalles() {
        return detalles;
    }
    
    public void setDetalles(ArrayList<DetalleVenta> detalles) {
        this.detalles = detalles;
        calcularTotales();
    }
    
    public double getSubTotal() {
        return subTotal;
    }
    
    public double getIgv() {
        return igv;
    }
    
    public double getTotal() {
        return total;
    }
    
    public String getRutaArchivo() {
        return rutaArchivo;
    }
    
    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

