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
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaListarVentas extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnEliminar;
	private JButton btnVerDetalle;
	private JButton btnAbrirArchivo;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtNumeroVenta;
	private JLabel lblBuscarPorNumero;
	private JButton btnListarTodas;
	private JButton btnSalir;
	private JLabel lblVentaSeleccionada;
	
	// Variable para almacenar la venta seleccionada
	private Venta ventaSeleccionada = null;

	public VentanaListarVentas() {
		setTitle("Listado de Ventas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 600);
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
		
		txtNumeroVenta = new JTextField();
		txtNumeroVenta.setBounds(180, 20, 120, 25);
		contentPane.add(txtNumeroVenta);
		txtNumeroVenta.setColumns(10);
		
		// ========== FILTRADO EN TIEMPO REAL ==========
		txtNumeroVenta.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				filtrarVentas();
			}
		});
		
		// ========== ETIQUETA DE VENTA SELECCIONADA ==========
		lblVentaSeleccionada = new JLabel("Ninguna venta seleccionada");
		lblVentaSeleccionada.setFont(new Font("Arial", Font.BOLD, 12));
		lblVentaSeleccionada.setForeground(new Color(100, 100, 100));
		lblVentaSeleccionada.setHorizontalAlignment(SwingConstants.LEFT);
		lblVentaSeleccionada.setBounds(20, 50, 850, 20);
		contentPane.add(lblVentaSeleccionada);
		
		// Tabla de ventas
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 75, 850, 380);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nº Venta");
		modelo.addColumn("Tipo Doc.");
		modelo.addColumn("RUC Cliente");
		modelo.addColumn("Cliente");
		modelo.addColumn("Fecha");
		modelo.addColumn("Total");
		table.setModel(modelo);
		
		// ========== LISTENER PARA SELECCIÓN EN LA TABLA ==========
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				seleccionarVentaDeTabla();
			}
		});
		
		// Botones
		btnVerDetalle = new JButton("Ver Detalle");
		btnVerDetalle.setFont(new Font("Arial", Font.BOLD, 12));
		btnVerDetalle.addActionListener(this);
		btnVerDetalle.setBounds(20, 466, 130, 30);
		contentPane.add(btnVerDetalle);
		
		btnAbrirArchivo = new JButton("Abrir Archivo");
		btnAbrirArchivo.setFont(new Font("Arial", Font.BOLD, 12));
		btnAbrirArchivo.addActionListener(this);
		btnAbrirArchivo.setBounds(160, 466, 130, 30);
		contentPane.add(btnAbrirArchivo);
		
		btnEliminar = new JButton("Anular Venta");
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(300, 466, 130, 30);
		contentPane.add(btnEliminar);
		
		btnListarTodas = new JButton("Listar Todas");
		btnListarTodas.setFont(new Font("Arial", Font.BOLD, 12));
		btnListarTodas.addActionListener(this);
		btnListarTodas.setBounds(440, 466, 130, 30);
		contentPane.add(btnListarTodas);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Arial", Font.BOLD, 12));
		btnSalir.addActionListener(this);
		btnSalir.setBounds(740, 466, 130, 30);
		contentPane.add(btnSalir);
		
		// Cargar todas las ventas al iniciar
		mostrarVentas();
	}
	
	ArregloVentas av = ArregloVentas.getInstancia();
	ArregloComida ac = ArregloComida.getInstancia();
	
	/**
	 * Captura la venta seleccionada en la tabla
	 */
	private void seleccionarVentaDeTabla() {
		int filaSeleccionada = table.getSelectedRow();
		
		if (filaSeleccionada != -1) {
			// Obtener el número de venta de la tabla
			int numeroVenta = (int) table.getValueAt(filaSeleccionada, 0);
			
			// Buscar la venta completa
			ventaSeleccionada = av.Buscar(numeroVenta);
			
			if (ventaSeleccionada != null) {
				// Actualizar etiqueta de selección
				String tipoDoc = ventaSeleccionada.getTipoDocumento();
				String cliente = ventaSeleccionada.getRazonSocial();
				
				lblVentaSeleccionada.setText("✓ Venta seleccionada: #" + numeroVenta + 
					" | " + tipoDoc + " | " + cliente + " | Total: S/. " + 
					String.format("%.2f", ventaSeleccionada.getTotal()));
				lblVentaSeleccionada.setForeground(new Color(0, 100, 0));
				
				// Resaltar visualmente
				table.setSelectionBackground(new Color(144, 238, 144));
				
				System.out.println("✓ Venta seleccionada: #" + numeroVenta);
			}
		}
	}
	
	/**
	 * Filtra ventas en tiempo real mientras el usuario escribe
	 */
	private void filtrarVentas() {
		String textoNumero = txtNumeroVenta.getText().trim();
		
		if (textoNumero.isEmpty()) {
			mostrarVentas();
			return;
		}
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		
		for (int i = 0; i < av.Tamaño(); i++) {
			Venta v = av.obtenerPorIndice(i);
			
			String numeroVenta = String.valueOf(v.getNumeroVenta());
			
			if (numeroVenta.contains(textoNumero)) {
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
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVerDetalle) {
			do_btnVerDetalle_actionPerformed(e);
		}
		if (e.getSource() == btnAbrirArchivo) {
			do_btnAbrirArchivo_actionPerformed(e);
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
		// Verificar si hay una venta seleccionada
		if (ventaSeleccionada == null) {
			JOptionPane.showMessageDialog(this, 
				"Debe seleccionar una venta de la tabla\n\n" +
				"Haga clic sobre la fila de la venta que desea ver", 
				"Ninguna venta seleccionada", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		StringBuilder detalle = new StringBuilder();
		detalle.append("=== DETALLE DE VENTA ===\n\n");
		detalle.append("Número: ").append(ventaSeleccionada.getNumeroVenta()).append("\n");
		detalle.append("Tipo: ").append(ventaSeleccionada.getTipoDocumento()).append("\n");
		
		if (ventaSeleccionada.getRucCliente() != null) {
			detalle.append("RUC Cliente: ").append(ventaSeleccionada.getRucCliente()).append("\n");
		}
		
		detalle.append("Cliente: ").append(ventaSeleccionada.getRazonSocial()).append("\n");
		
		if (ventaSeleccionada.getDomicilio() != null) {
			detalle.append("Domicilio: ").append(ventaSeleccionada.getDomicilio()).append("\n");
		}
		
		detalle.append("Fecha: ").append(ventaSeleccionada.getFecha()).append("\n\n");
		detalle.append("--- PRODUCTOS ---\n");
		
		// Obtener descripción desde ArregloComida
		for (DetalleVenta dv : ventaSeleccionada.getDetalles()) {
			Comida producto = ac.Buscar(dv.getCodigoProducto());
			String descripcion = producto != null ? producto.getDescripcion() : "Producto eliminado";
			
			detalle.append(String.format("• %s (Código: %d)\n", 
				descripcion, dv.getCodigoProducto()));
			detalle.append(String.format("  Cantidad: %d | Precio Unit.: S/. %.2f | Subtotal: S/. %.2f\n\n", 
				dv.getCantidad(), dv.getPrecioUnitario(), dv.getSubtotal()));
		}
		
		detalle.append("--- TOTALES ---\n");
		detalle.append(String.format("SubTotal: S/. %.2f\n", ventaSeleccionada.getSubTotal()));
		detalle.append(String.format("IGV (18%%): S/. %.2f\n", ventaSeleccionada.getIgv()));
		detalle.append(String.format("TOTAL: S/. %.2f\n", ventaSeleccionada.getTotal()));
		
		JOptionPane.showMessageDialog(this, detalle.toString(), 
			"Detalle de Venta", JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected void do_btnAbrirArchivo_actionPerformed(ActionEvent e) {
		// Verificar si hay una venta seleccionada
		if (ventaSeleccionada == null) {
			JOptionPane.showMessageDialog(this, 
				"Debe seleccionar una venta de la tabla\n\n" +
				"Haga clic sobre la fila de la venta cuyo archivo desea abrir", 
				"Ninguna venta seleccionada", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// Abrir archivo de la venta
		String rutaArchivo = ventaSeleccionada.getRutaArchivo();
		if (rutaArchivo != null && !rutaArchivo.isEmpty()) {
			GestorBoletas.abrirArchivo(rutaArchivo);
			JOptionPane.showMessageDialog(this, 
				"Abriendo archivo de la venta #" + ventaSeleccionada.getNumeroVenta() + "...", 
				"Archivo", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, 
				"No se encontró el archivo asociado a esta venta", 
				"Archivo no disponible", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		// Verificar si hay una venta seleccionada
		if (ventaSeleccionada == null) {
			JOptionPane.showMessageDialog(this, 
				"Debe seleccionar una venta de la tabla para anular\n\n" +
				"Haga clic sobre la fila de la venta que desea anular", 
				"Ninguna venta seleccionada", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// Usar eliminación segura
		if (EliminacionSegura.eliminarVentaSegura(ventaSeleccionada.getNumeroVenta())) {
			ventaSeleccionada = null;
			lblVentaSeleccionada.setText("Ninguna venta seleccionada");
			lblVentaSeleccionada.setForeground(new Color(100, 100, 100));
			txtNumeroVenta.setText("");
			mostrarVentas();
		}
	}
	
	protected void do_btnListarTodas_actionPerformed(ActionEvent e) {
		txtNumeroVenta.setText("");
		ventaSeleccionada = null;
		lblVentaSeleccionada.setText("Ninguna venta seleccionada");
		lblVentaSeleccionada.setForeground(new Color(100, 100, 100));
		mostrarVentas();
		JOptionPane.showMessageDialog(this, 
			"Mostrando todas las ventas (" + av.Tamaño() + ")", 
			"Lista actualizada", JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	public void mostrarVentas() {
		av.Listar(table);
	}
}