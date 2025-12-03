package clases;

public class DetalleCompra {
    private int codigoProducto;
    private int cantidad;
    private double costoUnitario;
    
    public DetalleCompra(int codigoProducto, int cantidad, double costoUnitario) {
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
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