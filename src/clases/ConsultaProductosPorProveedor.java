package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConsultaProductosPorProveedor {
    
    public static class ProductoProveedor {
        private int codigoProducto;
        private String descripcionProducto;
        private int numeroCompra;
        private String fechaCompra;
        private int cantidadComprada;
        private double costoUnitario;
        private String tipoDocumento;
     // Clase interna para almacenar datos de producto con detalles de compra
        public ProductoProveedor(int codigoProducto, String descripcionProducto,
                                int numeroCompra, String fechaCompra, int cantidadComprada,
                                double costoUnitario, String tipoDocumento) {
            this.codigoProducto = codigoProducto;
            this.descripcionProducto = descripcionProducto;
            this.numeroCompra = numeroCompra;
            this.fechaCompra = fechaCompra;
            this.cantidadComprada = cantidadComprada;
            this.costoUnitario = costoUnitario;
            this.tipoDocumento = tipoDocumento;
        }
        
        // Getters
        public int getCodigoProducto() { return codigoProducto; }
        public String getDescripcionProducto() { return descripcionProducto; }
        public int getNumeroCompra() { return numeroCompra; }
        public String getFechaCompra() { return fechaCompra; }
        public int getCantidadComprada() { return cantidadComprada; }
        public double getCostoUnitario() { return costoUnitario; }
        public String getTipoDocumento() { return tipoDocumento; }
        public double getSubtotal() { return cantidadComprada * costoUnitario; }
    }
    //Consulta mediante un INNER JOIN todos los productos distribuidos por un proveedor
    //genera un arreglo con esta información
    public static ArrayList<ProductoProveedor> obtenerProductosDeProveedor(int idProveedor) {
        ArrayList<ProductoProveedor> productos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            String sql = "SELECT " +
                        "    p.codigo AS codigo_producto, " +
                        "    p.descripcion AS descripcion_producto, " +
                        "    c.numero_compra, " +
                        "    c.fecha AS fecha_compra, " +
                        "    c.tipo_documento, " +
                        "    dc.cantidad AS cantidad_comprada, " +
                        "    dc.costo_unitario " +
                        "FROM compras c " +
                        "INNER JOIN detalle_compras dc ON c.numero_compra = dc.numero_compra " +
                        "INNER JOIN productos p ON dc.codigo_producto = p.codigo " +
                        "WHERE c.id_proveedor = ? " +
                        "ORDER BY c.fecha DESC";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idProveedor);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ProductoProveedor pp = new ProductoProveedor(
                    rs.getInt("codigo_producto"),
                    rs.getString("descripcion_producto"),
                    rs.getInt("numero_compra"),
                    rs.getString("fecha_compra"),
                    rs.getInt("cantidad_comprada"),
                    rs.getDouble("costo_unitario"),
                    rs.getString("tipo_documento")
                );
                productos.add(pp);
            }
            
            System.out.println("✓ Se encontraron " + productos.size() + 
                             " compra(s) del proveedor ID " + idProveedor);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error al consultar productos del proveedor:\n" + e.getMessage(),
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
        
        return productos;
    }
    // Genera un reporte formateado con estadísticas del proveedor
    public static String obtenerResumenProductos(int idProveedor) {
        ArrayList<ProductoProveedor> lista = obtenerProductosDeProveedor(idProveedor);
        
        if (lista.isEmpty()) {
            return "⚠️ Este proveedor no tiene compras registradas.\n\n" +
                   "No hay productos asociados aún.";
        }
        
        Proveedor proveedor = ArregloProveedor.getInstancia().BuscarPorId(idProveedor);
        String nombreProveedor = proveedor != null ? proveedor.getNombre() : "Proveedor #" + idProveedor;
        String rucProveedor = proveedor != null ? proveedor.getRuc() : "---";
        
        StringBuilder resumen = new StringBuilder();
        resumen.append("╔══════════════════════════════════════════════╗\n");
        resumen.append("  PRODUCTOS DEL PROVEEDOR\n");
        resumen.append("╚══════════════════════════════════════════════╝\n\n");
        resumen.append("Proveedor: ").append(nombreProveedor).append("\n");
        resumen.append("RUC: ").append(rucProveedor).append("\n");
        resumen.append("Total de compras: ").append(lista.size()).append("\n\n");
        
        resumen.append("═══════════════════════════════════════════════\n");
        resumen.append("HISTORIAL DE COMPRAS\n");
        resumen.append("═══════════════════════════════════════════════\n\n");
        
        int contador = 1;
        for (ProductoProveedor pp : lista) {
            resumen.append("───────────────────────────────────────────────\n");
            resumen.append("Compra #").append(contador++).append("\n");
            resumen.append("───────────────────────────────────────────────\n");
            resumen.append(String.format("Producto: %s\n", pp.getDescripcionProducto()));
            resumen.append(String.format("Código: %d\n", pp.getCodigoProducto()));
            resumen.append(String.format("Tipo Doc: %s\n", pp.getTipoDocumento()));
            resumen.append(String.format("Fecha: %s\n", pp.getFechaCompra()));
            resumen.append(String.format("Cantidad: %d unidades\n", pp.getCantidadComprada()));
            resumen.append(String.format("Costo Unit.: S/. %.2f\n", pp.getCostoUnitario()));
            resumen.append(String.format("Subtotal: S/. %.2f\n", pp.getSubtotal()));
            resumen.append(String.format("Nº Compra: %d\n\n", pp.getNumeroCompra()));
        }
        
        int totalUnidadesVendidas = 0;
        double totalFacturado = 0;
        double costoPromedio = 0;
        
        for (ProductoProveedor pp : lista) {
            totalUnidadesVendidas += pp.getCantidadComprada();
            totalFacturado += pp.getSubtotal();
        }
        
        if (!lista.isEmpty()) {
            costoPromedio = totalFacturado / totalUnidadesVendidas;
        }
        
        resumen.append("═══════════════════════════════════════════════\n");
        resumen.append("ESTADÍSTICAS\n");
        resumen.append("═══════════════════════════════════════════════\n");
        resumen.append(String.format("Total de unidades vendidas: %d\n", totalUnidadesVendidas));
        resumen.append(String.format("Total facturado: S/. %.2f\n", totalFacturado));
        resumen.append(String.format("Costo promedio: S/. %.2f\n", costoPromedio));
        
        return resumen.toString();
    }
}