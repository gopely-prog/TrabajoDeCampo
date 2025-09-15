package clases;

import java.util.ArrayList;

import javax.swing.JOptionPane;
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
    public void buscar(int codigo) {
    	for(int i = 0; i < ListaComida.size(); i++){
            if(ListaComida.get(i).getCodigo() == codigo) {
JOptionPane.showMessageDialog(null, "El producto que estas buscando si se encuentra en la lista","informacion",JOptionPane.INFORMATION_MESSAGE);
            }
            else JOptionPane.showMessageDialog(null, "El producto no se encontro en la lista","informacion",JOptionPane.INFORMATION_MESSAGE);
    }
    }
    public void Eliminar(Comida x){
        ListaComida.remove(x);
    }
    
    public void Listar(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0); 
        for (var p : ListaComida){
            modelo.addRow(new Object[]{p.getCodigo(), p.getDescripcion(), p.getStock(), p.getpUnitario()});
        }
    }
    public void Modificar(int codigo,int modificar) {
    	for(int i = 0; i < ListaComida.size(); i++){
            if(ListaComida.get(i).getCodigo() == codigo) {
            	 ListaComida.get(i).setCodigo(modificar);
                 System.out.println("Se modifico correctamente.");
            }else System.out.println("No se pudo modificar.");
        }
        
    	
    }
}
