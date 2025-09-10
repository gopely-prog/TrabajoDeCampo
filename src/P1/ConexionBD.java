package P1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ConexionBD {
    
    private static final String URL = "jdbc:mysql://localhost:3306/restaurante_bd";
    private static final String USER = "root"; // Cambia por tu usuario
    private static final String PASSWORD = ""; // Cambia por tu contraseña
    
    // Método para obtener la conexión
    public static void main(String[] args) {
    	
    }
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " +e.getMessage() );
            return null;
        }
    }
    
    // Método para obtener todos los productos
    public static List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT codigo, descripcion, precio_unitario, stock FROM productos";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String descripcion = rs.getString("descripcion");
                double precioUnitario = rs.getDouble("precio_unitario");
                int stock = rs.getInt("stock");
                
                // Crear producto con todos los datos
                Producto producto = new Producto(descripcion, codigo,precioUnitario, stock);
                productos.add(producto);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
        }
        
        return productos;
    }
    
    // Método para obtener un producto por código
    public static Producto obtenerProductoPorCodigo(String codigo) {
        String sql = "SELECT codigo, descripcion, precio_unitario, stock FROM productos WHERE codigo = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String descripcion = rs.getString("descripcion");
                double precioUnitario = rs.getDouble("precio_unitario");
                int stock = rs.getInt("stock");
                
                Producto producto = new Producto(descripcion, codigo, precioUnitario, stock);
                return producto;
            }
            
        } catch (SQLException e) {
            System.out.println("Error al obtener producto: " + e.getMessage());
        }
        
        return null;
    }
}