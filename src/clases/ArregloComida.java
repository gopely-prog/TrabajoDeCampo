package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ArregloComida {
	//Patrón singleton: Esto garantiza una sola instancia del arreglo
    private static ArregloComida instancia; 
    ArrayList<Comida> ListaComida;
    //Private para evitar instancia fuera de la clase
    private ArregloComida() {
        ListaComida = new ArrayList<Comida>();
        cargarDesdeBaseDeDatos(); 
    }
    //Obtiene la única instanca del arreglo.
    public static ArregloComida getInstancia() {
        if (instancia == null) {
            instancia = new ArregloComida();
        }
        return instancia;
    }
    //Carga todos los productos desde la base de datos de su tabla ´productos´ al iniciar el sistema
    private void cargarDesdeBaseDeDatos() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.getConexion();
            stmt = conn.createStatement();
            
            String sql = "SELECT codigo, descripcion, precio_unitario, costo_unitario, stock FROM productos";
            rs = stmt.executeQuery(sql);
            
            ListaComida.clear();
            
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String descripcion = rs.getString("descripcion");
                double precioUnitario = rs.getDouble("precio_unitario");
                double costoUnitario = rs.getDouble("costo_unitario");
                int stock = rs.getInt("stock");
                
                Comida producto = new Comida(codigo, descripcion, precioUnitario, costoUnitario);
                producto.setStock(stock);
                ListaComida.add(producto);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al cargar productos desde la base de datos:\n" + e.getMessage(),
                "Error BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Se inserta el producto en la base d datos y luego al array.
    public void Adicionar(Comida x) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            String sql = "INSERT INTO productos (codigo, descripcion, precio_unitario, costo_unitario, stock) " +
                        "VALUES (?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, x.getCodigo());
            pstmt.setString(2, x.getDescripcion());
            pstmt.setDouble(3, x.getpUnitario());
            pstmt.setDouble(4, x.getCostoUnitario());
            pstmt.setInt(5, x.getStock());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                ListaComida.add(x);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al agregar producto a la base de datos:\n" + e.getMessage(),
                "Error BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
//Elimina el producto de la BD y del array.
    public void Eliminar(Comida x){
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            String sql = "DELETE FROM productos WHERE codigo = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, x.getCodigo());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                ListaComida.remove(x);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al eliminar producto de la base de datos:\n" + e.getMessage(),
                "Error BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public int Tamaño() {
        return ListaComida.size();
    }
    
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
    //Actualiza los datos del productos en la BD y en el array
    public boolean Modificar(int codigo, String descripcion, double PUnit, double costoUnit) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            Comida enc = Buscar(codigo);
            if(enc == null) return false;
            
            conn = ConexionBD.getConexion();
            
            String sql = "UPDATE productos SET descripcion = ?, precio_unitario = ?, costo_unitario = ? " +
                        "WHERE codigo = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, descripcion);
            pstmt.setDouble(2, PUnit);
            pstmt.setDouble(3, costoUnit);
            pstmt.setInt(4, codigo);
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                enc.setDescripcion(descripcion);
                enc.setpUnitario(PUnit);
                enc.setCostoUnitario(costoUnit);
                return true;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al modificar producto en la base de datos:\n" + e.getMessage(),
                "Error BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return false;
    }
    //Mantiene actualizado el stock en BD y en memoria local.
    public void actualizarStock(int codigo, int nuevoStock) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            String sql = "UPDATE productos SET stock = ? WHERE codigo = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nuevoStock);
            pstmt.setInt(2, codigo);
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                Comida producto = Buscar(codigo);
                if (producto != null) {
                    producto.setStock(nuevoStock);
                }
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al actualizar stock en la base de datos:\n" + e.getMessage(),
                "Error BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Comida obtenerPorIndice(int indice) {
        if (indice >= 0 && indice < ListaComida.size()) {
            return ListaComida.get(indice);
        }
        return null;
    }
}