package interfacesGraficas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import clases.ArregloProveedor;
import clases.ConsultaProductosPorProveedor;
import clases.Proveedor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class VentanaProveedores extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JButton btnVerProductos;
    private JButton btnCerrar;
    private JLabel lblTitulo;
    private JTextField txtBuscarRUC;
    private JTextField txtBuscarNombre;
    private JButton btnListarTodos;
    private JCheckBox chkSoloActivos;
    
    // Variable para almacenar el proveedor seleccionado
    private Proveedor proveedorSeleccionado = null;

    public VentanaProveedores() {
        setTitle("Gestión de Proveedores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 550);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(205, 232, 254));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Título
        lblTitulo = new JLabel("PROVEEDORES REGISTRADOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(20, 11, 400, 30);
        contentPane.add(lblTitulo);
        
        // Búsqueda por RUC
        JLabel lblBuscarRUC = new JLabel("Buscar por RUC:");
        lblBuscarRUC.setFont(new Font("Arial", Font.BOLD, 13));
        lblBuscarRUC.setBounds(20, 52, 120, 25);
        contentPane.add(lblBuscarRUC);
        
        txtBuscarRUC = new JTextField();
        txtBuscarRUC.setBounds(20, 80, 150, 25);
        contentPane.add(txtBuscarRUC);
        txtBuscarRUC.setColumns(10);
        
        // Filtrado en tiempo real - RUC
        txtBuscarRUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarProveedores();
            }
        });
        
        // Búsqueda por Nombre
        JLabel lblBuscarNombre = new JLabel("Buscar por Nombre:");
        lblBuscarNombre.setFont(new Font("Arial", Font.BOLD, 13));
        lblBuscarNombre.setBounds(200, 52, 150, 25);
        contentPane.add(lblBuscarNombre);
        
        txtBuscarNombre = new JTextField();
        txtBuscarNombre.setColumns(10);
        txtBuscarNombre.setBounds(200, 80, 250, 25);
        contentPane.add(txtBuscarNombre);
        
        // Filtrado en tiempo real - Nombre
        txtBuscarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarProveedores();
            }
        });
        
        // Checkbox para filtrar solo activos
        chkSoloActivos = new JCheckBox("Solo proveedores activos");
        chkSoloActivos.setFont(new Font("Arial", Font.BOLD, 12));
        chkSoloActivos.setBackground(new Color(205, 232, 254));
        chkSoloActivos.setBounds(480, 80, 200, 25);
        chkSoloActivos.addActionListener(this);
        contentPane.add(chkSoloActivos);
        
        // Leyenda de colores
        JLabel lblLeyenda = new JLabel("🟢 Activo  |  🟡 Inactivo (histórico)");
        lblLeyenda.setFont(new Font("Arial", Font.ITALIC, 11));
        lblLeyenda.setBounds(20, 108, 300, 20);
        contentPane.add(lblLeyenda);
        
        // Tabla de proveedores
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 130, 750, 280);
        contentPane.add(scrollPane);
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("RUC");
        modelo.addColumn("Nombre / Razón Social");
        modelo.addColumn("Estado");
        
        table = new JTable(modelo);
        scrollPane.setViewportView(table);
        
        // Ajustar anchos de columnas
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(120); // RUC
        table.getColumnModel().getColumn(2).setPreferredWidth(350); // Nombre
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Estado
        
        // Aplicar colores según estado
        aplicarColoresEstado();
        
        // Listener para selección en la tabla
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarProveedorDeTabla();
            }
        });
        
        // Botones
        btnVerProductos = new JButton("Ver Productos");
        btnVerProductos.setFont(new Font("Arial", Font.BOLD, 12));
        btnVerProductos.addActionListener(this);
        btnVerProductos.setBounds(20, 421, 150, 30);
        btnVerProductos.setToolTipText("Ver todos los productos que distribuye este proveedor");
        contentPane.add(btnVerProductos);
        
        btnListarTodos = new JButton("Listar Todos");
        btnListarTodos.setFont(new Font("Arial", Font.BOLD, 12));
        btnListarTodos.addActionListener(this);
        btnListarTodos.setBounds(180, 421, 130, 30);
        contentPane.add(btnListarTodos);
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCerrar.addActionListener(this);
        btnCerrar.setBounds(670, 421, 100, 30);
        contentPane.add(btnCerrar);
        
        // Cargar proveedores
        cargarProveedores();
    }
    
    ArregloProveedor ap = ArregloProveedor.getInstancia();
    
    /**
     * Aplica colores a las filas según el estado del proveedor
     */
    private void aplicarColoresEstado() {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    // Obtener el estado de la columna 3
                    String estado = (String) table.getValueAt(row, 3);
                    
                    if (estado != null && estado.contains("ACTIVO")) {
                        c.setBackground(new Color(220, 255, 220)); // Verde claro
                    } else {
                        c.setBackground(new Color(255, 250, 220)); // Amarillo claro
                    }
                } else {
                    c.setBackground(new Color(144, 238, 144)); // Verde selección
                }
                
                return c;
            }
        });
    }
    
    /**
     * Captura el proveedor seleccionado en la tabla
     */
    private void seleccionarProveedorDeTabla() {
        int filaSeleccionada = table.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            // Obtener el ID del proveedor de la tabla
            int id = (int) table.getValueAt(filaSeleccionada, 0);
            
            // Buscar el proveedor completo
            proveedorSeleccionado = ap.BuscarPorId(id);
            
            if (proveedorSeleccionado != null) {
                System.out.println("✓ Proveedor seleccionado: " + proveedorSeleccionado.getNombre());
            }
        }
    }
    
    /**
     * Filtra proveedores en tiempo real mientras el usuario escribe
     */
    private void filtrarProveedores() {
        String textoRUC = txtBuscarRUC.getText().trim().toLowerCase();
        String textoNombre = txtBuscarNombre.getText().trim().toLowerCase();
        boolean soloActivos = chkSoloActivos.isSelected();
        
        // Si todo está vacío y no hay filtro de activos, mostrar todos
        if (textoRUC.isEmpty() && textoNombre.isEmpty() && !soloActivos) {
            cargarProveedores();
            return;
        }
        
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        
        // Recorrer todos los proveedores
        for (int i = 0; i < ap.Tamaño(); i++) {
            Proveedor proveedor = ap.obtenerPorIndice(i);
            
            if (proveedor != null) {
                String ruc = proveedor.getRuc().toLowerCase();
                String nombre = proveedor.getNombre().toLowerCase();
                
                // Verificar estado
                boolean tieneCompras = ConsultaProductosPorProveedor
                    .obtenerProductosDeProveedor(proveedor.getId())
                    .size() > 0;
                
                // Si solo queremos activos y no tiene compras, saltar
                if (soloActivos && !tieneCompras) continue;
                
                // Verificar filtros de texto
                boolean cumpleRUC = textoRUC.isEmpty() || ruc.contains(textoRUC);
                boolean cumpleNombre = textoNombre.isEmpty() || nombre.contains(textoNombre);
                
                // Si cumple todos los filtros, agregar a la tabla
                if (cumpleRUC && cumpleNombre) {
                    String estado = tieneCompras ? "✅ ACTIVO" : "⚠️ INACTIVO";
                    
                    modelo.addRow(new Object[]{
                        proveedor.getId(),
                        proveedor.getRuc(),
                        proveedor.getNombre(),
                        estado
                    });
                }
            }
        }
        
        // Mostrar cuántos resultados se encontraron
        int resultados = modelo.getRowCount();
        System.out.println(resultados == 0 ? 
            "⚠ No se encontraron proveedores con ese filtro" : 
            "✓ Se encontraron " + resultados + " proveedor(es)");
    }
    
    /**
     * Carga todos los proveedores en la tabla con su estado
     */
    private void cargarProveedores() {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        
        for (int i = 0; i < ap.Tamaño(); i++) {
            Proveedor proveedor = ap.obtenerPorIndice(i);
            
            if (proveedor != null) {
                // Verificar si tiene compras
                boolean tieneCompras = ConsultaProductosPorProveedor
                    .obtenerProductosDeProveedor(proveedor.getId())
                    .size() > 0;
                
                String estado = tieneCompras ? "✅ ACTIVO" : "⚠️ INACTIVO";
                
                modelo.addRow(new Object[]{
                    proveedor.getId(),
                    proveedor.getRuc(),
                    proveedor.getNombre(),
                    estado
                });
            }
        }
        
        System.out.println("✓ Se cargaron " + ap.Tamaño() + " proveedor(es)");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVerProductos) {
            do_btnVerProductos_actionPerformed(e);
        }
        if (e.getSource() == btnListarTodos) {
            do_btnListarTodos_actionPerformed(e);
        }
        if (e.getSource() == btnCerrar) {
            do_btnCerrar_actionPerformed(e);
        }
        if (e.getSource() == chkSoloActivos) {
            filtrarProveedores();
        }
    }
    
    protected void do_btnVerProductos_actionPerformed(ActionEvent e) {
        // Verificar si hay un proveedor seleccionado
        if (proveedorSeleccionado == null) {
            JOptionPane.showMessageDialog(this, 
                "Debe seleccionar un proveedor de la tabla\n\n" +
                "Haga clic sobre la fila del proveedor cuyos productos desea ver", 
                "Ningún proveedor seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Abrir ventana de productos del proveedor
        VentanaProductosProveedor ventana = new VentanaProductosProveedor(proveedorSeleccionado);
        ventana.setVisible(true);
        
        System.out.println("✓ Abriendo ventana de productos para: " + 
                           proveedorSeleccionado.getNombre());
    }
    
    protected void do_btnListarTodos_actionPerformed(ActionEvent e) {
        txtBuscarRUC.setText("");
        txtBuscarNombre.setText("");
        chkSoloActivos.setSelected(false);
        proveedorSeleccionado = null;
        cargarProveedores();
        JOptionPane.showMessageDialog(this, 
            "Mostrando todos los proveedores (" + ap.Tamaño() + ")", 
            "Lista actualizada", JOptionPane.INFORMATION_MESSAGE);
    }
    
    protected void do_btnCerrar_actionPerformed(ActionEvent e) {
        this.dispose();
    }
}