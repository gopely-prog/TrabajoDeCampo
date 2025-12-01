package clases;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Clase para manejar eliminaciones seguras respetando FK
 */
public class EliminacionSegura {
    
    /**
     * Verifica si un producto puede ser eliminado
     * @return true si puede eliminarse, false si tiene restricciones
     */
    public static boolean puedeEliminarProducto(int codigoProducto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            // Verificar si está en compras
            String sqlCompras = "SELECT COUNT(*) FROM detalle_compras WHERE codigo_producto = ?";
            pstmt = conn.prepareStatement(sqlCompras);
            pstmt.setInt(1, codigoProducto);
            rs = pstmt.executeQuery();
            
            int enCompras = 0;
            if (rs.next()) {
                enCompras = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            
            // Verificar si está en ventas
            String sqlVentas = "SELECT COUNT(*) FROM detalle_ventas WHERE codigo_producto = ?";
            pstmt = conn.prepareStatement(sqlVentas);
            pstmt.setInt(1, codigoProducto);
            rs = pstmt.executeQuery();
            
            int enVentas = 0;
            if (rs.next()) {
                enVentas = rs.getInt(1);
            }
            
            if (enCompras > 0 || enVentas > 0) {
                JOptionPane.showMessageDialog(null,
                    "⚠️ No se puede eliminar este producto\n\n" +
                    "El producto está siendo usado en:\n" +
                    "• " + enCompras + " registro(s) de compras\n" +
                    "• " + enVentas + " registro(s) de ventas\n\n" +
                    "❌ Restricción de Integridad Referencial (FK)\n\n" +
                    "Para eliminarlo, primero debes eliminar todas las\n" +
                    "compras y ventas que lo referencian.",
                    "Restricción de Clave Foránea",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            return true;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error al verificar producto:\n" + e.getMessage(),
                "Error BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
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
     * Verifica si un proveedor puede ser eliminado
     */
    public static boolean puedeEliminarProveedor(int idProveedor) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            String sql = "SELECT COUNT(*) FROM compras WHERE id_proveedor = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idProveedor);
            rs = pstmt.executeQuery();
            
            int comprasAsociadas = 0;
            if (rs.next()) {
                comprasAsociadas = rs.getInt(1);
            }
            
            if (comprasAsociadas > 0) {
                JOptionPane.showMessageDialog(null,
                    "⚠️ No se puede eliminar este proveedor\n\n" +
                    "El proveedor tiene " + comprasAsociadas + " compra(s) asociada(s).\n\n" +
                    "❌ Restricción de Integridad Referencial (FK)\n\n" +
                    "Para eliminarlo, primero debes eliminar todas\n" +
                    "las compras de este proveedor.",
                    "Restricción de Clave Foránea",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            return true;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error al verificar proveedor:\n" + e.getMessage(),
                "Error BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
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
     * Obtiene información detallada de uso de un producto
     */
    public static String obtenerInfoUsoProducto(int codigoProducto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder info = new StringBuilder();
        
        try {
            conn = ConexionBD.getConexion();
            
            // Info de producto
            Comida producto = ArregloComida.getInstancia().Buscar(codigoProducto);
            if (producto != null) {
                info.append("📦 PRODUCTO: ").append(producto.getDescripcion()).append("\n");
                info.append("   Código: ").append(codigoProducto).append("\n");
                info.append("   Stock actual: ").append(producto.getStock()).append("\n\n");
            }
            
            // Compras
            String sqlCompras = "SELECT COUNT(*) as total, SUM(cantidad) as cantidad_total " +
                               "FROM detalle_compras WHERE codigo_producto = ?";
            pstmt = conn.prepareStatement(sqlCompras);
            pstmt.setInt(1, codigoProducto);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int totalCompras = rs.getInt("total");
                int cantidadComprada = rs.getInt("cantidad_total");
                info.append("🛒 COMPRAS:\n");
                info.append("   • Aparece en ").append(totalCompras).append(" compra(s)\n");
                info.append("   • Cantidad total comprada: ").append(cantidadComprada).append("\n\n");
            }
            rs.close();
            pstmt.close();
            
            // Ventas
            String sqlVentas = "SELECT COUNT(*) as total, SUM(cantidad) as cantidad_total " +
                              "FROM detalle_ventas WHERE codigo_producto = ?";
            pstmt = conn.prepareStatement(sqlVentas);
            pstmt.setInt(1, codigoProducto);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int totalVentas = rs.getInt("total");
                int cantidadVendida = rs.getInt("cantidad_total");
                info.append("💰 VENTAS:\n");
                info.append("   • Aparece en ").append(totalVentas).append(" venta(s)\n");
                info.append("   • Cantidad total vendida: ").append(cantidadVendida).append("\n");
            }
            
            return info.toString();
            
        } catch (SQLException e) {
            return "Error al obtener información: " + e.getMessage();
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
     * Elimina un producto de forma segura (validando primero)
     */
    public static boolean eliminarProductoSeguro(int codigoProducto) {
        if (!puedeEliminarProducto(codigoProducto)) {
            return false;
        }
        
        // Si puede eliminarse, proceder
        Comida producto = ArregloComida.getInstancia().Buscar(codigoProducto);
        if (producto != null) {
            int confirmacion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar este producto?\n\n" +
                "Código: " + producto.getCodigo() + "\n" +
                "Descripción: " + producto.getDescripcion() + "\n" +
                "Stock actual: " + producto.getStock() + "\n\n" +
                "⚠️ Esta acción no se puede deshacer.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                ArregloComida.getInstancia().Eliminar(producto);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Elimina una compra (CASCADA eliminará los detalles automáticamente)
     */
    public static boolean eliminarCompraSegura(int numeroCompra) {
        Compra compra = ArregloCompras.getInstancia().Buscar(numeroCompra);
        
        if (compra == null) {
            JOptionPane.showMessageDialog(null,
                "No se encontró la compra #" + numeroCompra,
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Mostrar información detallada
        ArregloProveedor ap = ArregloProveedor.getInstancia();
        Proveedor proveedor = ap.BuscarPorId(compra.getIdProveedor());
        String nombreProveedor = proveedor != null ? proveedor.getNombre() : "Desconocido";
        
        int detallesCount = compra.getDetalles().size();
        
        int confirmacion = JOptionPane.showConfirmDialog(null,
            "¿Está seguro de eliminar esta compra?\n\n" +
            "Número: " + compra.getNumeroCompra() + "\n" +
            "Proveedor: " + nombreProveedor + "\n" +
            "Total: S/. " + String.format("%.2f", compra.getTotal()) + "\n" +
            "Productos: " + detallesCount + "\n\n" +
            "⚠️ Se eliminarán automáticamente:\n" +
            "   • " + detallesCount + " registro(s) de detalle_compras\n" +
            "   • Se devolverá el stock a los productos\n\n" +
            "Esta acción no se puede deshacer.",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Devolver stock (tu código actual ya hace esto)
            ArregloComida ac = ArregloComida.getInstancia();
            for (DetalleCompra dc : compra.getDetalles()) {
                Comida producto = ac.Buscar(dc.getCodigoProducto());
                if (producto != null) {
                    int nuevoStock = producto.getStock() - dc.getCantidad();
                    if (nuevoStock < 0) nuevoStock = 0;
                    producto.setStock(nuevoStock);
                    ac.actualizarStock(producto.getCodigo(), nuevoStock);
                }
            }
            
            // Eliminar compra (CASCADA eliminará detalles automáticamente)
            ArregloCompras.getInstancia().Eliminar(compra);
            
            JOptionPane.showMessageDialog(null,
                "✅ Compra eliminada exitosamente\n\n" +
                "Se eliminaron:\n" +
                "• 1 registro de compras\n" +
                "• " + detallesCount + " registros de detalle_compras\n" +
                "• Stock actualizado en productos",
                "Eliminación Exitosa",
                JOptionPane.INFORMATION_MESSAGE);
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Elimina una venta (CASCADA eliminará los detalles automáticamente)
     */
    public static boolean eliminarVentaSegura(int numeroVenta) {
        Venta venta = ArregloVentas.getInstancia().Buscar(numeroVenta);
        
        if (venta == null) {
            JOptionPane.showMessageDialog(null,
                "No se encontró la venta #" + numeroVenta,
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        int detallesCount = venta.getDetalles().size();
        
        int confirmacion = JOptionPane.showConfirmDialog(null,
            "¿Está seguro de ANULAR esta venta?\n\n" +
            "Número: " + venta.getNumeroVenta() + "\n" +
            "Cliente: " + venta.getRazonSocial() + "\n" +
            "Total: S/. " + String.format("%.2f", venta.getTotal()) + "\n" +
            "Productos: " + detallesCount + "\n\n" +
            "⚠️ Se eliminarán automáticamente:\n" +
            "   • " + detallesCount + " registro(s) de detalle_ventas\n" +
            "   • Se devolverá el stock a los productos\n\n" +
            "Esta acción no se puede deshacer.",
            "Confirmar anulación de venta",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Devolver stock
            ArregloComida ac = ArregloComida.getInstancia();
            for (DetalleVenta dv : venta.getDetalles()) {
                Comida producto = ac.Buscar(dv.getCodigoProducto());
                if (producto != null) {
                    int nuevoStock = producto.getStock() + dv.getCantidad();
                    producto.setStock(nuevoStock);
                    ac.actualizarStock(producto.getCodigo(), nuevoStock);
                }
            }
            
            // Eliminar venta (CASCADA eliminará detalles)
            ArregloVentas.getInstancia().Eliminar(venta);
            
            JOptionPane.showMessageDialog(null,
                "✅ Venta anulada exitosamente\n\n" +
                "Se eliminaron:\n" +
                "• 1 registro de ventas\n" +
                "• " + detallesCount + " registros de detalle_ventas\n" +
                "• Stock devuelto a productos",
                "Anulación Exitosa",
                JOptionPane.INFORMATION_MESSAGE);
            
            return true;
        }
        
        return false;
    }
}