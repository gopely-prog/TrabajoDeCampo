package clases;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ArregloComida {
    private static ArregloComida instancia;
    ArrayList<Comida> ListaComida;
    
    private ArregloComida() {
        ListaComida = new ArrayList<Comida>();
    }
    
    public static ArregloComida getInstancia() {
        if (instancia == null) {
            instancia = new ArregloComida();
        }
        return instancia;
    }
    
    public void Adicionar(Comida x) {
        ListaComida.add(x);
    }
    
    public Comida Buscar(int Codigo) {
        for(int i = 0; i < ListaComida.size(); i++){
            if(ListaComida.get(i).getCodigo() == Codigo) return ListaComida.get(i);
        }
        return null;
    }
    
    public Comida BuscarPorDescripcion(String descripcion) {
        for(int i = 0; i < ListaComida.size(); i++){
            if(ListaComida.get(i).getDescripcion().equalsIgnoreCase(descripcion)) {
                return ListaComida.get(i);
            }
        }
        return null;
    }
    
    public void Eliminar(Comida x){
        ListaComida.remove(x);
    }
    
    public int Tamaño() {
    	return ListaComida.size();
    }
    
    // Listar con todas las columnas incluyendo Costo Unitario y % Ganancia
    public void Listar(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0); 
        for (var p : ListaComida){
            modelo.addRow(new Object[]{
                p.getCodigo(), 
                p.getDescripcion(), 
                String.format("S/. %.2f", p.getpUnitario()),
                String.format("S/. %.2f", p.getCostoUnitario()),
                String.format("%.2f%%", p.calcularPorcentajeGanancia()),
                p.getStock()
            });
        }
    }
    
    // Modificar ahora incluye costoUnitario (el stock se modifica desde Compras)
    public boolean Modificar(int codigo, String descripcion, double PUnit, double costoUnit) {	
    	Comida enc = Buscar(codigo);
		if(enc != null) {
			enc.setDescripcion(descripcion);
			enc.setpUnitario(PUnit);
			enc.setCostoUnitario(costoUnit);
			return true;
		}
		else return false; 
    }
    
    public Comida obtenerPorIndice(int indice) {
        if (indice >= 0 && indice < ListaComida.size()) {
            return ListaComida.get(indice);
        }
        return null;
    }
}