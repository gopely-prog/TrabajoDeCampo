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
}
