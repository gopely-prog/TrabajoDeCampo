package clases;

public class DetalleCompra {
    private int codigoProducto;
    private String descripcionProducto;
    private int cantidad;
    private double costoUnitario;
    
    public DetalleCompra(int codigoProducto, String descripcionProducto, int cantidad, double costoUnitario) {
        this.codigoProducto = codigoProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
    }
    
    public int getCodigoProducto() {
        return codigoProducto;
    }
    
    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    
    public String getDescripcionProducto() {
        return descripcionProducto;
    }
    
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getCostoUnitario() {
        return costoUnitario;
    }
    
    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
    
    public double getSubtotal() {
        return cantidad * costoUnitario;
    }
}