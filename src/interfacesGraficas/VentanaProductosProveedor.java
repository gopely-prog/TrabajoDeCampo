package interfacesGraficas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import clases.Proveedor;
import clases.ConsultaProductosPorProveedor;
import clases.ConsultaProductosPorProveedor.ProductoProveedor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;

public class VentanaProductosProveedor extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JButton btnCerrar;
    private JLabel lblTitulo;
    private JLabel lblProveedor;
    private JLabel lblEstadisticas;
    
    private Proveedor proveedor;
    private ArrayList<ProductoProveedor> listaProductos;

    public VentanaProductosProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
        
        setTitle("Productos del Proveedor - " + proveedor.getNombre());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(205, 232, 254));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Título
        lblTitulo = new JLabel("PRODUCTOS DEL PROVEEDOR");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(20, 11, 400, 30);
        contentPane.add(lblTitulo);
        
        // Información del proveedor
        JPanel panelProveedor = new JPanel();
        panelProveedor.setBorder(new TitledBorder(null, "Información del Proveedor", 
            TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        panelProveedor.setBounds(20, 52, 850, 80);
        contentPane.add(panelProveedor);
        panelProveedor.setLayout(null);
        
        lblProveedor = new JLabel();
        lblProveedor.setFont(new Font("Arial", Font.PLAIN, 13));
        lblProveedor.setBounds(10, 25, 830, 45);
        panelProveedor.add(lblProveedor);
        
        // Cargar información del proveedor
        cargarInfoProveedor();
        
        // Tabla de productos
        JPanel panelTabla = new JPanel();
        panelTabla.setBorder(new TitledBorder(null, "Historial de Productos Distribuidos", 
            TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        panelTabla.setBounds(20, 143, 850, 320);
        contentPane.add(panelTabla);
        panelTabla.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 25, 830, 284);
        panelTabla.add(scrollPane);
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Producto");
        modelo.addColumn("Código");
        modelo.addColumn("Tipo Doc.");
        modelo.addColumn("Nº Compra");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Costo Unit.");
        modelo.addColumn("Subtotal");
        
        table = new JTable(modelo);
        scrollPane.setViewportView(table);
        
        // Ajustar anchos de columnas
        table.getColumnModel().getColumn(0).setPreferredWidth(150); // Producto
        table.getColumnModel().getColumn(1).setPreferredWidth(70);  // Código
        table.getColumnModel().getColumn(2).setPreferredWidth(80);  // Tipo Doc
        table.getColumnModel().getColumn(3).setPreferredWidth(80);  // Nº Compra
        table.getColumnModel().getColumn(4).setPreferredWidth(120); // Fecha
        table.getColumnModel().getColumn(5).setPreferredWidth(70);  // Cantidad
        table.getColumnModel().getColumn(6).setPreferredWidth(90);  // Costo Unit.
        table.getColumnModel().getColumn(7).setPreferredWidth(90);  // Subtotal
        
        // Panel de estadísticas
        lblEstadisticas = new JLabel();
        lblEstadisticas.setFont(new Font("Arial", Font.BOLD, 13));
        lblEstadisticas.setBounds(20, 474, 650, 25);
        contentPane.add(lblEstadisticas);
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCerrar.addActionListener(this);
        btnCerrar.setBounds(770, 510, 100, 30);
        contentPane.add(btnCerrar);
        
        // Cargar datos
        cargarProductos();
    }
    
    private void cargarInfoProveedor() {
        String info = String.format(
            "<html>ID: %d  |  RUC: %s  |  Razón Social: %s</html>",
            proveedor.getId(),
            proveedor.getRuc(),
            proveedor.getNombre()
        );
        lblProveedor.setText(info);
    }
    
    private void cargarProductos() {
        // Obtener lista de productos del proveedor
        listaProductos = ConsultaProductosPorProveedor.obtenerProductosDeProveedor(
            proveedor.getId()
        );
        
        if (listaProductos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "ℹ️ PROVEEDOR HISTÓRICO\n\n" +
                "Este proveedor no tiene compras registradas actualmente.\n\n" +
                "📋 Razones posibles:\n" +
                "  • Las compras fueron eliminadas del sistema\n" +
                "  • Proveedor nuevo sin compras aún\n" +
                "  • Proveedor inactivo (histórico)\n\n" +
                "✅ El registro se mantiene para:\n" +
                "  • Historial de la empresa\n" +
                "  • Futuras compras\n" +
                "  • Auditoría contable\n\n" +
                "Puede realizar nuevas compras a este proveedor\n" +
                "desde el módulo 'Fac. Compras'.",
                "Información del Proveedor",
                JOptionPane.INFORMATION_MESSAGE);
            
            lblEstadisticas.setText("📊 Proveedor sin compras actuales (Histórico - Mantener para auditoría)");
            return;
        }
        
        // Llenar tabla
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        
        int totalUnidades = 0;
        double totalFacturado = 0;
        
        for (ProductoProveedor pp : listaProductos) {
            modelo.addRow(new Object[]{
                pp.getDescripcionProducto(),
                pp.getCodigoProducto(),
                pp.getTipoDocumento(),
                pp.getNumeroCompra(),
                pp.getFechaCompra(),
                pp.getCantidadComprada(),
                String.format("S/. %.2f", pp.getCostoUnitario()),
                String.format("S/. %.2f", pp.getSubtotal())
            });
            
            totalUnidades += pp.getCantidadComprada();
            totalFacturado += pp.getSubtotal();
        }
        
        // Mostrar estadísticas
        double costoPromedio = totalUnidades > 0 ? totalFacturado / totalUnidades : 0;
        
        lblEstadisticas.setText(String.format(
            "📊 Total compras: %d  |  Unidades vendidas: %d  |  Total facturado: S/. %.2f  |  Costo Promedio: S/. %.2f",
            listaProductos.size(),
            totalUnidades,
            totalFacturado,
            costoPromedio
        ));
        
        System.out.println("✓ Se cargaron " + listaProductos.size() + " compras del proveedor");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCerrar) {
            do_btnCerrar_actionPerformed(e);
        }
    }
    
    protected void do_btnCerrar_actionPerformed(ActionEvent e) {
        this.dispose();
    }
}