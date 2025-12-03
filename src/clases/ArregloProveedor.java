package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ArregloProveedor {
    private static ArregloProveedor instancia;
    private ArrayList<Proveedor> listaProveedores;
    
    private ArregloProveedor() {
        listaProveedores = new ArrayList<Proveedor>();
        cargarDesdeBaseDeDatos();
    }
    
    public static ArregloProveedor getInstancia() {
        if (instancia == null) {
            instancia = new ArregloProveedor();
        }
        return instancia;
    }
    private void cargarDesdeBaseDeDatos() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.getConexion();
            stmt = conn.createStatement();
            
            String sql = "SELECT id, ruc, nombre FROM proveedores";
            rs = stmt.executeQuery(sql);
            
            listaProveedores.clear();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String ruc = rs.getString("ruc");
                String nombre = rs.getString("nombre");
                
                Proveedor proveedor = new Proveedor(id, ruc, nombre);
                listaProveedores.add(proveedor);
            }
            
            System.out.println("✓ Se cargaron " + listaProveedores.size() + " proveedores desde la BD");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al cargar proveedores desde la base de datos:\n" + e.getMessage(),
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
    // Inserta el proveedor y captura el ID auto-generado por MySQL
    public void Adicionar(Proveedor p) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            String sql = "INSERT INTO proveedores (ruc, nombre) VALUES (?, ?)";
            
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, p.getRuc());
            pstmt.setString(2, p.getNombre());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    p.setId(idGenerado);
                }
                
                listaProveedores.add(p);
                System.out.println("✓ Proveedor agregado a la BD: " + p.getNombre() + " (ID: " + p.getId() + ")");
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al agregar proveedor a la base de datos:\n" + e.getMessage(),
                "Error BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Proveedor BuscarPorId(int id) {
        for (Proveedor p : listaProveedores) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    public Proveedor BuscarPorRuc(String ruc) {
        for (Proveedor p : listaProveedores) {
            if (p.getRuc().equals(ruc)) {
                return p;
            }
        }
        return null;
    }
    
    public int Tamaño() {
        return listaProveedores.size();
    }
    public Proveedor obtenerPorIndice(int indice) {
        if (indice >= 0 && indice < listaProveedores.size()) {
            return listaProveedores.get(indice);
        }
        return null;
    }
}