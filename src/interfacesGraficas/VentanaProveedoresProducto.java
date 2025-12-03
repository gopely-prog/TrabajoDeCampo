package interfacesGraficas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import clases.ArregloComida;
import clases.Comida;
import clases.ConsultaProveedoresPorProducto;
import clases.ConsultaProveedoresPorProducto.ProveedorProducto;
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
import javax.swing.JTextArea;
import java.util.ArrayList;

public class VentanaProveedoresProducto extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JButton btnCerrar;
    private JLabel lblTitulo;
    private JLabel lblProducto;
    private JLabel lblEstadisticas;
    
    private Comida producto;
    private ArrayList<ProveedorProducto> listaProveedores;

    public VentanaProveedoresProducto(Comida producto) {
        this.producto = producto;
        
        setTitle("Proveedores del Producto - " + producto.getDescripcion());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(205, 232, 254));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblTitulo = new JLabel("PROVEEDORES DEL PRODUCTO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(20, 11, 400, 30);
        contentPane.add(lblTitulo);
        
        JPanel panelProducto = new JPanel();
        panelProducto.setBorder(new TitledBorder(null, "Información del Producto", 
            TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        panelProducto.setBounds(20, 52, 850, 80);
        contentPane.add(panelProducto);
        panelProducto.setLayout(null);
        
        lblProducto = new JLabel();
        lblProducto.setFont(new Font("Arial", Font.PLAIN, 13));
        lblProducto.setBounds(10, 25, 830, 45);
        panelProducto.add(lblProducto);
        
        cargarInfoProducto();
        
        JPanel panelTabla = new JPanel();
        panelTabla.setBorder(new TitledBorder(null, "Historial de Compras a Proveedores", 
            TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        panelTabla.setBounds(20, 143, 850, 320);
        contentPane.add(panelTabla);
        panelTabla.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 25, 830, 284);
        panelTabla.add(scrollPane);
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Proveedor");
        modelo.addColumn("RUC");
        modelo.addColumn("Tipo Doc.");
        modelo.addColumn("Nº Compra");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Costo Unit.");
        modelo.addColumn("Subtotal");
        
        table = new JTable(modelo);
        scrollPane.setViewportView(table);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(150); // Proveedor
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // RUC
        table.getColumnModel().getColumn(2).setPreferredWidth(80);  // Tipo Doc
        table.getColumnModel().getColumn(3).setPreferredWidth(80);  // Nº Compra
        table.getColumnModel().getColumn(4).setPreferredWidth(120); // Fecha
        table.getColumnModel().getColumn(5).setPreferredWidth(70);  // Cantidad
        table.getColumnModel().getColumn(6).setPreferredWidth(90);  // Costo Unit.
        table.getColumnModel().getColumn(7).setPreferredWidth(90);  // Subtotal
        
        lblEstadisticas = new JLabel();
        lblEstadisticas.setFont(new Font("Arial", Font.BOLD, 13));
        lblEstadisticas.setBounds(20, 474, 600, 25);
        contentPane.add(lblEstadisticas);
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCerrar.addActionListener(this);
        btnCerrar.setBounds(770, 510, 100, 30);
        contentPane.add(btnCerrar);
        
        cargarProveedores();
    }
    
    private void cargarInfoProducto() {
        String info = String.format(
            "<html>Código: %d  |  Descripción: %s  |  Precio Venta: S/. %.2f  |  " +
            "Costo Actual: S/. %.2f  |  Stock: %d unidades</html>",
            producto.getCodigo(),
            producto.getDescripcion(),
            producto.getpUnitario(),
            producto.getCostoUnitario(),
            producto.getStock()
        );
        lblProducto.setText(info);
    }
    
    private void cargarProveedores() {
        listaProveedores = ConsultaProveedoresPorProducto.obtenerProveedoresDeProducto(
            producto.getCodigo()
        );
        
        if (listaProveedores.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "⚠️ Este producto no tiene compras registradas.\n\n" +
                "No hay proveedores asociados todavía.\n\n" +
                "Agregue compras desde el módulo 'Fac. Compras' para que\n" +
                "aparezcan proveedores aquí.",
                "Sin Compras Registradas",
                JOptionPane.INFORMATION_MESSAGE);
            
            lblEstadisticas.setText("📊 Sin compras registradas");
            return;
        }
       
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        
        int totalUnidades = 0;
        double totalGastado = 0;
        
        for (ProveedorProducto pp : listaProveedores) {
            modelo.addRow(new Object[]{
                pp.getNombreProveedor(),
                pp.getRucProveedor(),
                pp.getTipoDocumento(),
                pp.getNumeroCompra(),
                pp.getFechaCompra(),
                pp.getCantidadComprada(),
                String.format("S/. %.2f", pp.getCostoUnitario()),
                String.format("S/. %.2f", pp.getSubtotal())
            });
            
            totalUnidades += pp.getCantidadComprada();
            totalGastado += pp.getSubtotal();
        }
        
        double costoPromedio = totalUnidades > 0 ? totalGastado / totalUnidades : 0;
        
        lblEstadisticas.setText(String.format(
            "Total compras: %d  |  Unidades: %d  |  Inversión: S/. %.2f  |  Costo Promedio: S/. %.2f",
            listaProveedores.size(),
            totalUnidades,
            totalGastado,
            costoPromedio
        ));
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCerrar) {
            do_btnCerrar_actionPerformed(e);
        }
    }
    
    protected void do_btnCerrar_actionPerformed(ActionEvent e) {
        this.dispose();
    }
}