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
    
    /**
     * NUEVO: Carga todos los proveedores desde la BD
     */
    private void cargarDesdeBaseDeDatos() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.getConexion();
            stmt = conn.createStatement();
            
            String sql = "SELECT ruc, nombre FROM proveedores";
            rs = stmt.executeQuery(sql);
            
            listaProveedores.clear();
            
            while (rs.next()) {
                String ruc = rs.getString("ruc");
                String nombre = rs.getString("nombre");
                
                Proveedor proveedor = new Proveedor(ruc, nombre);
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
    
    /**
     * MODIFICADO: Adicionar ahora guarda en la BD
     */
    public void Adicionar(Proveedor p) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            String sql = "INSERT INTO proveedores (ruc, nombre) VALUES (?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, p.getRuc());
            pstmt.setString(2, p.getNombre());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                listaProveedores.add(p);
                System.out.println("✓ Proveedor agregado a la BD: " + p.getNombre());
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al agregar proveedor a la base de datos:\n" + e.getMessage(),
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
}