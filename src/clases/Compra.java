package clases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Compra {
    private int numeroCompra;
    private String tipoDocumento;
    private int idProveedor; // ← CAMBIO: era rucProveedor String
    // ❌ ELIMINADO: nombreProveedor (se obtiene de la tabla proveedores)
    private ArrayList<DetalleCompra> detalles;
    private double subTotal;
    private double igv;
    private double total;
    private String fecha;
    
    public Compra(int numeroCompra, String tipoDocumento, int idProveedor) {
        this.numeroCompra = numeroCompra;
        this.tipoDocumento = tipoDocumento;
        this.idProveedor = idProveedor;
        this.detalles = new ArrayList<>();
        this.subTotal = 0;
        this.igv = 0;
        this.total = 0;
        
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.fecha = ahora.format(formato);
    }
    
    public void agregarDetalle(DetalleCompra detalle) {
        detalles.add(detalle);
        calcularTotales();
    }
    
    public void calcularTotales() {
        subTotal = 0;
        for (DetalleCompra detalle : detalles) {
            subTotal += detalle.getSubtotal();
        }
        igv = subTotal * 0.18;
        total = subTotal + igv;
    }
    
    // Getters y Setters
    public int getNumeroCompra() {
        return numeroCompra;
    }
    
    public void setNumeroCompra(int numeroCompra) {
        this.numeroCompra = numeroCompra;
    }
    
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public int getIdProveedor() {
        return idProveedor;
    }
    
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    public ArrayList<DetalleCompra> getDetalles() {
        return detalles;
    }
    
    public void setDetalles(ArrayList<DetalleCompra> detalles) {
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
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}