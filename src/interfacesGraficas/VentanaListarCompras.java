package interfacesGraficas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import clases.*;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VentanaListarCompras extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnEliminar;
	private JButton btnVerDetalle;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtNumeroCompra;
	private JLabel lblBuscarPorNumero;
	private JButton btnListarTodas;
	private JButton btnSalir;
	private JLabel lblCompraSeleccionada;
	
	// Variable para almacenar la compra seleccionada
	private Compra compraSeleccionada = null;

	public VentanaListarCompras() {
		setTitle("Listado de Compras");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Búsqueda
		lblBuscarPorNumero = new JLabel("Buscar por Número:");
		lblBuscarPorNumero.setFont(new Font("Arial", Font.BOLD, 13));
		lblBuscarPorNumero.setBounds(20, 20, 150, 25);
		contentPane.add(lblBuscarPorNumero);
		
		txtNumeroCompra = new JTextField();
		txtNumeroCompra.setBounds(180, 20, 120, 25);
		contentPane.add(txtNumeroCompra);
		txtNumeroCompra.setColumns(10);
		
		// ========== FILTRADO EN TIEMPO REAL ==========
		txtNumeroCompra.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				filtrarCompras();
			}
		});
		
		// ========== ETIQUETA DE COMPRA SELECCIONADA ==========
		lblCompraSeleccionada = new JLabel("Ninguna compra seleccionada");
		lblCompraSeleccionada.setFont(new Font("Arial", Font.BOLD, 12));
		lblCompraSeleccionada.setForeground(new Color(100, 100, 100));
		lblCompraSeleccionada.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompraSeleccionada.setBounds(20, 50, 800, 20);
		contentPane.add(lblCompraSeleccionada);
		
		// Tabla de compras
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 75, 800, 380);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nº Compra");
		modelo.addColumn("Tipo Doc.");
		modelo.addColumn("RUC Proveedor");
		modelo.addColumn("Nombre Proveedor");
		modelo.addColumn("Fecha");
		modelo.addColumn("Total");
		table.setModel(modelo);
		
		// ========== LISTENER PARA SELECCIÓN EN LA TABLA ==========
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				seleccionarCompraDeTabla();
			}
		});
		
		// Botones
		btnVerDetalle = new JButton("Ver Detalle");
		btnVerDetalle.setFont(new Font("Arial", Font.BOLD, 12));
		btnVerDetalle.addActionListener(this);
		btnVerDetalle.setBounds(20, 466, 130, 30);
		contentPane.add(btnVerDetalle);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(160, 466, 130, 30);
		contentPane.add(btnEliminar);
		
		btnListarTodas = new JButton("Listar Todas");
		btnListarTodas.setFont(new Font("Arial", Font.BOLD, 12));
		btnListarTodas.addActionListener(this);
		btnListarTodas.setBounds(300, 466, 130, 30);
		contentPane.add(btnListarTodas);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Arial", Font.BOLD, 12));
		btnSalir.addActionListener(this);
		btnSalir.setBounds(690, 466, 130, 30);
		contentPane.add(btnSalir);
		
		// Cargar todas las compras al iniciar
		mostrarCompras();
	}
	
	ArregloCompras acompras = ArregloCompras.getInstancia();
	ArregloComida ac = ArregloComida.getInstancia();
	
	/**
	 * Captura la compra seleccionada en la tabla
	 */
	private void seleccionarCompraDeTabla() {
		int filaSeleccionada = table.getSelectedRow();
		
		if (filaSeleccionada != -1) {
			// Obtener el número de compra de la tabla
			int numeroCompra = (int) table.getValueAt(filaSeleccionada, 0);
			
			// Buscar la compra completa
			compraSeleccionada = acompras.Buscar(numeroCompra);
			
			if (compraSeleccionada != null) {
				// Actualizar etiqueta de selección
				ArregloProveedor ap = ArregloProveedor.getInstancia();
				Proveedor proveedor = ap.BuscarPorId(compraSeleccionada.getIdProveedor());
				String nombreProveedor = proveedor != null ? proveedor.getNombre() : "Desconocido";
				
				lblCompraSeleccionada.setText("✓ Compra seleccionada: #" + numeroCompra + 
					" | " + nombreProveedor + " | Total: S/. " + 
					String.format("%.2f", compraSeleccionada.getTotal()));
				lblCompraSeleccionada.setForeground(new Color(0, 100, 0));
				
				// Resaltar visualmente
				table.setSelectionBackground(new Color(144, 238, 144));
				
				System.out.println("✓ Compra seleccionada: #" + numeroCompra);
			}
		}
	}
	
	/**
	 * Filtra compras en tiempo real mientras el usuario escribe
	 */
	private void filtrarCompras() {
		String textoNumero = txtNumeroCompra.getText().trim();
		
		if (textoNumero.isEmpty()) {
			mostrarCompras();
			return;
		}
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		
		ArregloProveedor ap = ArregloProveedor.getInstancia();
		
		for (int i = 0; i < acompras.Tamaño(); i++) {
			Compra c = acompras.obtenerPorIndice(i);
			
			String numeroCompra = String.valueOf(c.getNumeroCompra());
			
			if (numeroCompra.contains(textoNumero)) {
				Proveedor proveedor = ap.BuscarPorId(c.getIdProveedor());
				String nombreProveedor = proveedor != null ? proveedor.getNombre() : "Desconocido";
				String rucProveedor = proveedor != null ? proveedor.getRuc() : "---";
				
				modelo.addRow(new Object[]{
					c.getNumeroCompra(),
					c.getTipoDocumento(),
					rucProveedor,
					nombreProveedor,
					c.getFecha(),
					String.format("S/. %.2f", c.getTotal())
				});
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVerDetalle) {
			do_btnVerDetalle_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnListarTodas) {
			do_btnListarTodas_actionPerformed(e);
		}
		if (e.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(e);
		}
	}
	
	protected void do_btnVerDetalle_actionPerformed(ActionEvent e) {
		// Verificar si hay una compra seleccionada
		if (compraSeleccionada == null) {
			JOptionPane.showMessageDialog(this, 
				"Debe seleccionar una compra de la tabla\n\n" +
				"Haga clic sobre la fila de la compra que desea ver", 
				"Ninguna compra seleccionada", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// Obtener proveedor por ID
		ArregloProveedor ap = ArregloProveedor.getInstancia();
		Proveedor proveedor = ap.BuscarPorId(compraSeleccionada.getIdProveedor());
		
		String nombreProveedor = proveedor != null ? proveedor.getNombre() : "Desconocido";
		String rucProveedor = proveedor != null ? proveedor.getRuc() : "---";
		
		StringBuilder detalle = new StringBuilder();
		detalle.append("=== DETALLE DE COMPRA ===\n\n");
		detalle.append("Número: ").append(compraSeleccionada.getNumeroCompra()).append("\n");
		detalle.append("Tipo: ").append(compraSeleccionada.getTipoDocumento()).append("\n");
		detalle.append("RUC Proveedor: ").append(rucProveedor).append("\n");
		detalle.append("Nombre: ").append(nombreProveedor).append("\n");
		detalle.append("Fecha: ").append(compraSeleccionada.getFecha()).append("\n\n");
		detalle.append("--- PRODUCTOS ---\n");
		
		// Obtener descripción desde ArregloComida
		for (DetalleCompra dc : compraSeleccionada.getDetalles()) {
			Comida producto = ac.Buscar(dc.getCodigoProducto());
			String descripcion = producto != null ? producto.getDescripcion() : "Producto eliminado";
			
			detalle.append(String.format("• %s (Código: %d)\n", 
				descripcion, dc.getCodigoProducto()));
			detalle.append(String.format("  Cantidad: %d | Costo Unit.: S/. %.2f | Subtotal: S/. %.2f\n\n", 
				dc.getCantidad(), dc.getCostoUnitario(), dc.getSubtotal()));
		}
		
		detalle.append("--- TOTALES ---\n");
		detalle.append(String.format("SubTotal: S/. %.2f\n", compraSeleccionada.getSubTotal()));
		detalle.append(String.format("IGV (18%%): S/. %.2f\n", compraSeleccionada.getIgv()));
		detalle.append(String.format("TOTAL: S/. %.2f\n", compraSeleccionada.getTotal()));
		
		JOptionPane.showMessageDialog(this, detalle.toString(), 
			"Detalle de Compra", JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		// Verificar si hay una compra seleccionada
		if (compraSeleccionada == null) {
			JOptionPane.showMessageDialog(this, 
				"Debe seleccionar una compra de la tabla para eliminar\n\n" +
				"Haga clic sobre la fila de la compra que desea eliminar", 
				"Ninguna compra seleccionada", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// Usar eliminación segura
		if (EliminacionSegura.eliminarCompraSegura(compraSeleccionada.getNumeroCompra())) {
			compraSeleccionada = null;
			lblCompraSeleccionada.setText("Ninguna compra seleccionada");
			lblCompraSeleccionada.setForeground(new Color(100, 100, 100));
			txtNumeroCompra.setText("");
			mostrarCompras();
		}
	}
	
	protected void do_btnListarTodas_actionPerformed(ActionEvent e) {
		txtNumeroCompra.setText("");
		compraSeleccionada = null;
		lblCompraSeleccionada.setText("Ninguna compra seleccionada");
		lblCompraSeleccionada.setForeground(new Color(100, 100, 100));
		mostrarCompras();
		JOptionPane.showMessageDialog(this, 
			"Mostrando todas las compras (" + acompras.Tamaño() + ")", 
			"Lista actualizada", JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	public void mostrarCompras() {
		acompras.Listar(table);
	}
}