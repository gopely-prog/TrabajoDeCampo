package clases;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ArregloCompras {
    private static ArregloCompras instancia;
    private ArrayList<Compra> listaCompras;
    private int contadorCompras;
    
    private ArregloCompras() {
        listaCompras = new ArrayList<>();
        contadorCompras = 1;
    }
    
    public static ArregloCompras getInstancia() {
        if (instancia == null) {
            instancia = new ArregloCompras();
        }
        return instancia;
    }
    
    public void Adicionar(Compra c) {
        listaCompras.add(c);
    }
    
    public Compra Buscar(int numeroCompra) {
        for (Compra c : listaCompras) {
            if (c.getNumeroCompra() == numeroCompra) {
                return c;
            }
        }
        return null;
    }
    
    public void Eliminar(Compra c) {
        listaCompras.remove(c);
    }
    
    public int Tamaño() {
        return listaCompras.size();
    }
    
    public int obtenerSiguienteNumero() {
        return contadorCompras++;
    }
    
    public void Listar(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        for (Compra c : listaCompras) {
            modelo.addRow(new Object[]{
                c.getNumeroCompra(),
                c.getTipoDocumento(),
                c.getRucProveedor(),
                c.getNombreProveedor(),
                c.getFecha(),
                String.format("S/. %.2f", c.getTotal())
            });
        }
    }
    
    public Compra obtenerPorIndice(int indice) {
        if (indice >= 0 && indice < listaCompras.size()) {
            return listaCompras.get(indice);
        }
        return null;
    }
}