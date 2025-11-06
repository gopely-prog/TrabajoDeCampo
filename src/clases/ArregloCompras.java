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

public class ArregloCompras {
    private static ArregloCompras instancia;
    private ArrayList<Compra> listaCompras;
    private int contadorCompras;
    
    private ArregloCompras() {
        listaCompras = new ArrayList<>();
        cargarDesdeBaseDeDatos();
        obtenerUltimoNumeroCompra();
    }
    
    public static ArregloCompras getInstancia() {
        if (instancia == null) {
            instancia = new ArregloCompras();
        }
        return instancia;
    }
    
    /**
     * NUEVO: Obtiene el último número de compra de la BD para continuar la secuencia
     */
    private void obtenerUltimoNumeroCompra() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.getConexion();
            stmt = conn.createStatement();
            
            String sql = "SELECT MAX(numero_compra) as ultimo FROM compras";
            rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                int ultimo = rs.getInt("ultimo");
                contadorCompras = ultimo + 1;
            } else {
                contadorCompras = 1;
            }
            
            System.out.println("✓ Próximo número de compra: " + contadorCompras);
            
        } catch (SQLException e) {
            contadorCompras = 1;
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
     * NUEVO: Carga todas las compras desde la BD
     */
    private void cargarDesdeBaseDeDatos() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.getConexion();
            stmt = conn.createStatement();
            
            String sql = "SELECT numero_compra, tipo_documento, ruc_proveedor, nombre_proveedor, " +
                        "sub_total, igv, total, fecha FROM compras ORDER BY numero_compra";
            rs = stmt.executeQuery(sql);
            
            listaCompras.clear();
            
            while (rs.next()) {
                int numeroCompra = rs.getInt("numero_compra");
                String tipoDocumento = rs.getString("tipo_documento");
                String rucProveedor = rs.getString("ruc_proveedor");
                String nombreProveedor = rs.getString("nombre_proveedor");
                double subTotal = rs.getDouble("sub_total");
                double igv = rs.getDouble("igv");
                double total = rs.getDouble("total");
                String fecha = rs.getString("fecha");
                
                Compra compra = new Compra(numeroCompra, tipoDocumento, rucProveedor, nombreProveedor);
                compra.setFecha(fecha);
                
                // Cargar detalles de esta compra
                cargarDetallesCompra(compra);
                
                listaCompras.add(compra);
            }
            
            System.out.println("✓ Se cargaron " + listaCompras.size() + " compras desde la BD");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al cargar compras desde la base de datos:\n" + e.getMessage(),
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
     * NUEVO: Carga los detalles de una compra específica
     */
    private void cargarDetallesCompra(Compra compra) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            String sql = "SELECT codigo_producto, descripcion_producto, cantidad, costo_unitario " +
                        "FROM detalle_compras WHERE numero_compra = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, compra.getNumeroCompra());
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int codigoProducto = rs.getInt("codigo_producto");
                String descripcionProducto = rs.getString("descripcion_producto");
                int cantidad = rs.getInt("cantidad");
                double costoUnitario = rs.getDouble("costo_unitario");
                
                DetalleCompra detalle = new DetalleCompra(codigoProducto, descripcionProducto, 
                                                          cantidad, costoUnitario);
                compra.agregarDetalle(detalle);
            }
            
        } catch (SQLException e) {
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
    
    /**
     * MODIFICADO: Adicionar ahora guarda en la BD
     */
    public void Adicionar(Compra c) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionBD.getConexion();
            conn.setAutoCommit(false); // Iniciar transacción
            
            // 1. Insertar compra principal
            String sqlCompra = "INSERT INTO compras (numero_compra, tipo_documento, ruc_proveedor, " +
                              "nombre_proveedor, sub_total, igv, total, fecha) " +
                              "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sqlCompra);
            pstmt.setInt(1, c.getNumeroCompra());
            pstmt.setString(2, c.getTipoDocumento());
            pstmt.setString(3, c.getRucProveedor());
            pstmt.setString(4, c.getNombreProveedor());
            pstmt.setDouble(5, c.getSubTotal());
            pstmt.setDouble(6, c.getIgv());
            pstmt.setDouble(7, c.getTotal());
            pstmt.setString(8, c.getFecha());
            
            pstmt.executeUpdate();
            pstmt.close();
            
            // 2. Insertar detalles de la compra
            String sqlDetalle = "INSERT INTO detalle_compras (numero_compra, codigo_producto, " +
                               "descripcion_producto, cantidad, costo_unitario, subtotal) " +
                               "VALUES (?, ?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sqlDetalle);
            
            for (DetalleCompra detalle : c.getDetalles()) {
                pstmt.setInt(1, c.getNumeroCompra());
                pstmt.setInt(2, detalle.getCodigoProducto());
                pstmt.setString(3, detalle.getDescripcionProducto());
                pstmt.setInt(4, detalle.getCantidad());
                pstmt.setDouble(5, detalle.getCostoUnitario());
                pstmt.setDouble(6, detalle.getSubtotal());
                pstmt.executeUpdate();
            }
            
            conn.commit(); // Confirmar transacción
            listaCompras.add(c);
            
            System.out.println("✓ Compra #" + c.getNumeroCompra() + " guardada en la BD");
            
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback(); // Revertir si hay error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            JOptionPane.showMessageDialog(null, 
                "Error al guardar compra en la base de datos:\n" + e.getMessage(),
                "Error BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Compra Buscar(int numeroCompra) {
        for (Compra c : listaCompras) {
            if (c.getNumeroCompra() == numeroCompra) {
                return c;
            }
        }
        return null;
    }
    
    /**
     * MODIFICADO: Eliminar ahora borra de la BD
     */
    public void Eliminar(Compra c) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            // Al eliminar la compra, los detalles se eliminan automáticamente 
            // gracias al ON DELETE CASCADE en la BD
            String sql = "DELETE FROM compras WHERE numero_compra = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, c.getNumeroCompra());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                listaCompras.remove(c);
                System.out.println("✓ Compra #" + c.getNumeroCompra() + " eliminada de la BD");
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al eliminar compra de la base de datos:\n" + e.getMessage(),
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