package clases;

public class Proveedor {
    private int id; 
    private String ruc;
    private String nombre;
    // Constructor sin ID (para nuevos proveedores antes de insertar en BD)
    public Proveedor(String ruc, String nombre) {
        this.ruc = ruc;
        this.nombre = nombre;
    }
    //Constructor con ID (para cargar de la BD)
    public Proveedor(int id, String ruc, String nombre) {
        this.id = id;
        this.ruc = ruc;
        this.nombre = nombre;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
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
    
    public String toString() {
        return nombre;
    }
}