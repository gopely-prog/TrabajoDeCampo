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

public class ArregloVentas {
    private static ArregloVentas instancia;
    private ArrayList<Venta> listaVentas;
    
    private ArregloVentas() {
        listaVentas = new ArrayList<>();
        cargarDesdeBaseDeDatos();
    }
    
    public static ArregloVentas getInstancia() {
        if (instancia == null) {
            instancia = new ArregloVentas();
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
            
            String sql = "SELECT numero_venta, tipo_documento, ruc_cliente, razon_social, " +
                        "domicilio, sub_total, igv, total, ruta_archivo, fecha " +
                        "FROM ventas ORDER BY numero_venta";
            rs = stmt.executeQuery(sql);
            
            listaVentas.clear();
            
            while (rs.next()) {
                int numeroVenta = rs.getInt("numero_venta");
                String tipoDocumento = rs.getString("tipo_documento");
                String rucCliente = rs.getString("ruc_cliente");
                String razonSocial = rs.getString("razon_social");
                String domicilio = rs.getString("domicilio");
                double subTotal = rs.getDouble("sub_total");
                double igv = rs.getDouble("igv");
                double total = rs.getDouble("total");
                String rutaArchivo = rs.getString("ruta_archivo");
                String fecha = rs.getString("fecha");
                
                Venta venta = new Venta(numeroVenta, tipoDocumento, rucCliente, 
                                       razonSocial, domicilio, rutaArchivo);
                venta.setFecha(fecha);
                
                cargarDetallesVenta(venta);
                
                listaVentas.add(venta);
            }
            
            System.out.println("✓ Se cargaron " + listaVentas.size() + " ventas desde la BD");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al cargar ventas desde la base de datos:\n" + e.getMessage(),
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
    
    private void cargarDetallesVenta(Venta venta) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            String sql = "SELECT codigo_producto, cantidad, precio_unitario " +
                        "FROM detalle_ventas WHERE numero_venta = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, venta.getNumeroVenta());
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int codigoProducto = rs.getInt("codigo_producto");
                int cantidad = rs.getInt("cantidad");
                double precioUnitario = rs.getDouble("precio_unitario");
                
                DetalleVenta detalle = new DetalleVenta(codigoProducto, cantidad, precioUnitario);
                venta.agregarDetalle(detalle);
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
    // Inserta la venta y sus detalles en una transacción
    public void Adicionar(Venta v) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionBD.getConexion();
            conn.setAutoCommit(false);
            
            String sqlVenta = "INSERT INTO ventas (numero_venta, tipo_documento, ruc_cliente, " +
                             "razon_social, domicilio, sub_total, igv, total, ruta_archivo, fecha) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sqlVenta);
            pstmt.setInt(1, v.getNumeroVenta());
            pstmt.setString(2, v.getTipoDocumento());
            pstmt.setString(3, v.getRucCliente());
            pstmt.setString(4, v.getRazonSocial());
            pstmt.setString(5, v.getDomicilio());
            pstmt.setDouble(6, v.getSubTotal());
            pstmt.setDouble(7, v.getIgv());
            pstmt.setDouble(8, v.getTotal());
            pstmt.setString(9, v.getRutaArchivo());
            pstmt.setString(10, v.getFecha());
            
            pstmt.executeUpdate();
            pstmt.close();
            
            String sqlDetalle = "INSERT INTO detalle_ventas (numero_venta, codigo_producto, " +
                               "cantidad, precio_unitario, subtotal) " +
                               "VALUES (?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sqlDetalle);
            
            for (DetalleVenta detalle : v.getDetalles()) {
                pstmt.setInt(1, v.getNumeroVenta());
                pstmt.setInt(2, detalle.getCodigoProducto());
                pstmt.setInt(3, detalle.getCantidad());
                pstmt.setDouble(4, detalle.getPrecioUnitario());
                pstmt.setDouble(5, detalle.getSubtotal());
                pstmt.executeUpdate();
            }
            
            conn.commit();
            listaVentas.add(v);
            
            System.out.println("✓ Venta #" + v.getNumeroVenta() + " guardada en la BD");
            
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            JOptionPane.showMessageDialog(null, 
                "Error al guardar venta en la base de datos:\n" + e.getMessage(),
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
    
    public Venta Buscar(int numeroVenta) {
        for (Venta v : listaVentas) {
            if (v.getNumeroVenta() == numeroVenta) {
                return v;
            }
        }
        return null;
    }
    
    public void Eliminar(Venta v) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            String sql = "DELETE FROM ventas WHERE numero_venta = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, v.getNumeroVenta());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                listaVentas.remove(v);
                System.out.println("✓ Venta #" + v.getNumeroVenta() + " eliminada de la BD");
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al eliminar venta de la base de datos:\n" + e.getMessage(),
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
        return listaVentas.size();
    }
    
    public void Listar(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        for (Venta v : listaVentas) {
            modelo.addRow(new Object[]{
                v.getNumeroVenta(),
                v.getTipoDocumento(),
                v.getRucCliente() != null ? v.getRucCliente() : "---",
                v.getRazonSocial(),
                v.getFecha(),
                String.format("S/. %.2f", v.getTotal())
            });
        }
    }
    
    public Venta obtenerPorIndice(int indice) {
        if (indice >= 0 && indice < listaVentas.size()) {
            return listaVentas.get(indice);
        }
        return null;
    }
}