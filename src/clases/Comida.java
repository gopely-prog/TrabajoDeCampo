package clases;

public class Comida {
	private int cantidad, codigo, stock;
	private String descripcion;
	private double pUnitario;
	private double costoUnitario; 
	
	public Comida(int cantidad, double pUnitario) { 
		this.cantidad = cantidad;
		this.pUnitario = pUnitario;
	}
	
	public Comida(int codigo, String descripcion, double pUnitario, double costoUnitario) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.pUnitario = pUnitario;
		this.costoUnitario = costoUnitario;
		this.stock = 0; 
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public double getpUnitario() {
		return pUnitario;
	}
	
	public void setpUnitario(double pUnitario) {
		this.pUnitario = pUnitario;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getCostoUnitario() {
		return costoUnitario;
	}
	
	public void setCostoUnitario(double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}
	// Calcula el porcentaje de ganancia: ((precio - costo) / costo) * 100
	public double calcularPorcentajeGanancia() {
		if (costoUnitario == 0) {
			return 0;
		}
		return ((pUnitario - costoUnitario) / costoUnitario) * 100;
	}
	public double Total(int cantidad, double pUnitario, double IGV) {
		return cantidad * pUnitario * IGV;
	}
	public String toString() { //se va a utilizar para el cboBox
		return descripcion;
	}
}