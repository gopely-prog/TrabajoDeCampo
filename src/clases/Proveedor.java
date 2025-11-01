package clases;

public class Proveedor {
    private String ruc;
    private String nombre;
    
    public Proveedor(String ruc, String nombre) {
        this.ruc = ruc;
        this.nombre = nombre;
    }
    
    public String getRuc() {
        return ruc;
    }
    
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}