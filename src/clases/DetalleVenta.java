package clases;

public class DetalleVenta {
    private int codigoProducto;
    private int cantidad;
    private double precioUnitario;
    
    public DetalleVenta(int codigoProducto, int cantidad, double precioUnitario) {
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    
    public int getCodigoProducto() {
        return codigoProducto;
    }
    
    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    public double getSubtotal() {
        return cantidad * precioUnitario;
    }
}