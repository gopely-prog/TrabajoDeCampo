
package clases;

public class Comida {
	private int cantidad,codigo, stock;
	private String  descripcion;
	private double pUnitario;
	//Constructor para cálculos
	public Comida(int cantidad, double pUnitario) {
		this.cantidad = cantidad;
		this.pUnitario = pUnitario;
	}
	
	//Constructor completo con todos los parámetros
	public Comida(int codigo, String descripcion, double pUnitario, int stock) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.pUnitario = pUnitario;
		this.stock = stock;
	}
	
	//Getters And Setters
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
	//Total de la compra
	public double Total(int cantidad, double pUnitario) {
		return cantidad * pUnitario;
	}
	//Total de la compra con impuestos
	public double Total(int cantidad, double pUnitario, double IGV) {
		return cantidad*pUnitario*IGV;
	}
	// Método toString para mostrar en el ComboBox
	public String toString() {
		return descripcion;
	}
}
