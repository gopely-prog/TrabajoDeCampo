package clases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Compra {
    private int numeroCompra;
    private String tipoDocumento; // "Factura", "Boleta", "Nota de Compra"
    private String rucProveedor;
    private String nombreProveedor;
    private ArrayList<DetalleCompra> detalles;
    private double subTotal;
    private double igv;
    private double total;
    private String fecha;
    
    public Compra(int numeroCompra, String tipoDocumento, String rucProveedor, String nombreProveedor) {
        this.numeroCompra = numeroCompra;
        this.tipoDocumento = tipoDocumento;
        this.rucProveedor = rucProveedor;
        this.nombreProveedor = nombreProveedor;
        this.detalles = new ArrayList<>();
        this.subTotal = 0;
        this.igv = 0;
        this.total = 0;
        
        // Obtener fecha actual
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
    
    public String getRucProveedor() {
        return rucProveedor;
    }
    
    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }
    
    public String getNombreProveedor() {
        return nombreProveedor;
    }
    
    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
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