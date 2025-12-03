package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConsultaProveedoresPorProducto {
    
    public static class ProveedorProducto {
        private int idProveedor;
        private String rucProveedor;
        private String nombreProveedor;
        private int numeroCompra;
        private String fechaCompra;
        private int cantidadComprada;
        private double costoUnitario;
        private String tipoDocumento;
        
        public ProveedorProducto(int idProveedor, String rucProveedor, String nombreProveedor,
                                int numeroCompra, String fechaCompra, int cantidadComprada,
                                double costoUnitario, String tipoDocumento) {
            this.idProveedor = idProveedor;
            this.rucProveedor = rucProveedor;
            this.nombreProveedor = nombreProveedor;
            this.numeroCompra = numeroCompra;
            this.fechaCompra = fechaCompra;
            this.cantidadComprada = cantidadComprada;
            this.costoUnitario = costoUnitario;
            this.tipoDocumento = tipoDocumento;
        }
        
        // Getters
        public int getIdProveedor() { return idProveedor; }
        public String getRucProveedor() { return rucProveedor; }
        public String getNombreProveedor() { return nombreProveedor; }
        public int getNumeroCompra() { return numeroCompra; }
        public String getFechaCompra() { return fechaCompra; }
        public int getCantidadComprada() { return cantidadComprada; }
        public double getCostoUnitario() { return costoUnitario; }
        public String getTipoDocumento() { return tipoDocumento; }
        public double getSubtotal() { return cantidadComprada * costoUnitario; }
    }
    
    public static ArrayList<ProveedorProducto> obtenerProveedoresDeProducto(int codigoProducto) {
        ArrayList<ProveedorProducto> proveedores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.getConexion();
            
            String sql = "SELECT " +
                        "    p.id AS id_proveedor, " +
                        "    p.ruc AS ruc_proveedor, " +
                        "    p.nombre AS nombre_proveedor, " +
                        "    c.numero_compra, " +
                        "    c.fecha AS fecha_compra, " +
                        "    c.tipo_documento, " +
                        "    dc.cantidad AS cantidad_comprada, " +
                        "    dc.costo_unitario " +
                        "FROM detalle_compras dc " +
                        "INNER JOIN compras c ON dc.numero_compra = c.numero_compra " +
                        "INNER JOIN proveedores p ON c.id_proveedor = p.id " +
                        "WHERE dc.codigo_producto = ? " +
                        "ORDER BY c.fecha DESC";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoProducto);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ProveedorProducto pp = new ProveedorProducto(
                    rs.getInt("id_proveedor"),
                    rs.getString("ruc_proveedor"),
                    rs.getString("nombre_proveedor"),
                    rs.getInt("numero_compra"),
                    rs.getString("fecha_compra"),
                    rs.getInt("cantidad_comprada"),
                    rs.getDouble("costo_unitario"),
                    rs.getString("tipo_documento")
                );
                proveedores.add(pp);
            }
            
            System.out.println("✓ Se encontraron " + proveedores.size() + 
                             " compra(s) del producto código " + codigoProducto);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error al consultar proveedores del producto:\n" + e.getMessage(),
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
        
        return proveedores;
    }
    
    public static String obtenerResumenProveedores(int codigoProducto) {
        ArrayList<ProveedorProducto> lista = obtenerProveedoresDeProducto(codigoProducto);
        
        if (lista.isEmpty()) {
            return "⚠️ Este producto no tiene compras registradas.\n\n" +
                   "No hay proveedores asociados aún.";
        }
        
        Comida producto = ArregloComida.getInstancia().Buscar(codigoProducto);
        String nombreProducto = producto != null ? producto.getDescripcion() : "Producto #" + codigoProducto;
        
        StringBuilder resumen = new StringBuilder();
        resumen.append("╔══════════════════════════════════════════════╗\n");
        resumen.append("  PROVEEDORES DEL PRODUCTO\n");
        resumen.append("╚══════════════════════════════════════════════╝\n\n");
        resumen.append("Producto: ").append(nombreProducto).append("\n");
        resumen.append("Código: ").append(codigoProducto).append("\n");
        resumen.append("Total de compras: ").append(lista.size()).append("\n\n");
        
        resumen.append("═══════════════════════════════════════════════\n");
        resumen.append("HISTORIAL DE COMPRAS\n");
        resumen.append("═══════════════════════════════════════════════\n\n");
        
        int contador = 1;
        for (ProveedorProducto pp : lista) {
            resumen.append("───────────────────────────────────────────────\n");
            resumen.append("Compra #").append(contador++).append("\n");
            resumen.append("───────────────────────────────────────────────\n");
            resumen.append(String.format("Proveedor: %s\n", pp.getNombreProveedor()));
            resumen.append(String.format("RUC: %s\n", pp.getRucProveedor()));
            resumen.append(String.format("Tipo Doc: %s\n", pp.getTipoDocumento()));
            resumen.append(String.format("Fecha: %s\n", pp.getFechaCompra()));
            resumen.append(String.format("Cantidad: %d unidades\n", pp.getCantidadComprada()));
            resumen.append(String.format("Costo Unit.: S/. %.2f\n", pp.getCostoUnitario()));
            resumen.append(String.format("Subtotal: S/. %.2f\n", pp.getSubtotal()));
            resumen.append(String.format("Nº Compra: %d\n\n", pp.getNumeroCompra()));
        }
        
        int totalUnidadesCompradas = 0;
        double totalGastado = 0;
        double costoPromedio = 0;
        
        for (ProveedorProducto pp : lista) {
            totalUnidadesCompradas += pp.getCantidadComprada();
            totalGastado += pp.getSubtotal();
        }
        
        if (!lista.isEmpty()) {
            costoPromedio = totalGastado / totalUnidadesCompradas;
        }
        
        resumen.append("═══════════════════════════════════════════════\n");
        resumen.append("ESTADÍSTICAS\n");
        resumen.append("═══════════════════════════════════════════════\n");
        resumen.append(String.format("Total de unidades compradas: %d\n", totalUnidadesCompradas));
        resumen.append(String.format("Inversión total: S/. %.2f\n", totalGastado));
        resumen.append(String.format("Costo promedio: S/. %.2f\n", costoPromedio));
        
        return resumen.toString();
    }
}