package clases;

public class Proveedor {
    private int id; // ← NUEVO CAMPO
    private String ruc;
    private String nombre;
    
    // Constructor SIN id (para insertar nuevos proveedores)
    public Proveedor(String ruc, String nombre) {
        this.ruc = ruc;
        this.nombre = nombre;
    }
    
    // Constructor CON id (para cargar desde BD)
    public Proveedor(int id, String ruc, String nombre) {
        this.id = id;
        this.ruc = ruc;
        this.nombre = nombre;
    }
    
    // Getters y Setters
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
    
    @Override
    public String toString() {
        return nombre;
    }
}