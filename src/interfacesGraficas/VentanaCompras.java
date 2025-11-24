package interfacesGraficas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import clases.*;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class VentanaCompras extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRUC;
	private JTextField txtNombreProveedor;
	private JTextField txtSubTotal;
	private JTextField txtIGV;
	private JTextField txtTotal;
	private JButton btnAgregarProducto;
	private JComboBox<String> cboTipoDocumento;
	private JComboBox<Comida> cboProductos;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtCodigo;
	private JTextField txtCostoUnitario;
	private JTextField txtCantidad;
	private JTextField txtStock;
	private JButton btnNuevo;
	private JButton btnSalir;
	private JButton btnRealizarCompra;
	private JButton btnListarCompras;
	
	private double subTotalGlobal = 0;

	public VentanaCompras() {
		setTitle("Facturación de Compras");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Facturación de Compras");
		lblTitulo.setFont(new Font("MS PGothic", Font.BOLD, 24));
		lblTitulo.setBounds(10, 11, 350, 39);
		contentPane.add(lblTitulo);
		
		// Panel de datos de compra
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
			new Color(255, 255, 255), new Color(160, 160, 160)), 
			"Datos de Compra", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panelDatos.setBounds(10, 61, 510, 130);
		contentPane.add(panelDatos);
		panelDatos.setLayout(null);
		
		JLabel lblTipoDoc = new JLabel("Tipo Documento:");
		lblTipoDoc.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblTipoDoc.setBounds(10, 25, 120, 20);
		panelDatos.add(lblTipoDoc);
		
		cboTipoDocumento = new JComboBox<>();
		cboTipoDocumento.addItem("Factura");
		cboTipoDocumento.addItem("Boleta");
		cboTipoDocumento.addItem("Nota de Compra");
		cboTipoDocumento.setBounds(140, 25, 150, 20);
		cboTipoDocumento.addItemListener(this); // ← LISTENER PARA CAMBIOS
		panelDatos.add(cboTipoDocumento);
		
		JLabel lblRUC = new JLabel("RUC Proveedor:");
		lblRUC.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblRUC.setBounds(10, 55, 120, 20);
		panelDatos.add(lblRUC);
		
		txtRUC = new JTextField();
		txtRUC.setBounds(140, 55, 150, 20);
		panelDatos.add(txtRUC);
		txtRUC.setColumns(10);
		
		// ========== FILTRO PARA SOLO NÚMEROS Y MÁXIMO 11 ==========
		aplicarFiltroRUC();
		
		// Listener para autocompletar nombre
		txtRUC.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				autocompletarProveedor();
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre/R.S.:");
		lblNombre.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNombre.setBounds(10, 90, 120, 20);
		panelDatos.add(lblNombre);
		
		txtNombreProveedor = new JTextField();
		txtNombreProveedor.setColumns(10);
		txtNombreProveedor.setBounds(140, 90, 350, 20);
		panelDatos.add(txtNombreProveedor);
		
		// Panel de productos
		JPanel panelProductos = new JPanel();
		panelProductos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
			new Color(255, 255, 255), new Color(160, 160, 160)), 
			"Agregar Productos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panelProductos.setBounds(10, 202, 510, 180);
		contentPane.add(panelProductos);
		panelProductos.setLayout(null);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblProducto.setBounds(10, 25, 80, 20);
		panelProductos.add(lblProducto);
		
		cboProductos = new JComboBox<>();
		cboProductos.addItemListener(this);
		cboProductos.setBounds(100, 25, 200, 20);
		panelProductos.add(cboProductos);
		
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCodigo.setBounds(10, 55, 80, 20);
		panelProductos.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(100, 55, 100, 20);
		panelProductos.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo Unit.:");
		lblCosto.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCosto.setBounds(10, 85, 80, 20);
		panelProductos.add(lblCosto);
		
		txtCostoUnitario = new JTextField();
		txtCostoUnitario.setEditable(false);
		txtCostoUnitario.setColumns(10);
		txtCostoUnitario.setBounds(100, 85, 100, 20);
		panelProductos.add(txtCostoUnitario);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCantidad.setBounds(10, 115, 80, 20);
		panelProductos.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(100, 115, 100, 20);
		panelProductos.add(txtCantidad);
		
		JLabel lblStock = new JLabel("Stock Actual:");
		lblStock.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblStock.setBounds(310, 55, 90, 20);
		panelProductos.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setEditable(false);
		txtStock.setBounds(310, 85, 100, 20);
		panelProductos.add(txtStock);
		txtStock.setColumns(10);
		
		btnAgregarProducto = new JButton("Agregar");
		btnAgregarProducto.addActionListener(this);
		btnAgregarProducto.setFont(new Font("Arial", Font.BOLD, 13));
		btnAgregarProducto.setBounds(310, 115, 120, 40);
		panelProductos.add(btnAgregarProducto);
		
		// Tabla de productos
		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new TitledBorder(null, "Detalle de Compra", 
			TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panelTabla.setBounds(10, 393, 510, 180);
		contentPane.add(panelTabla);
		panelTabla.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 490, 144);
		panelTabla.add(scrollPane);
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Descripción");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Costo Unit.");  
		modelo.addColumn("Subtotal");
		
		table = new JTable();
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		// Totales
		JLabel lblSubTotal = new JLabel("Sub Total:");
		lblSubTotal.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblSubTotal.setBounds(270, 584, 80, 20);
		contentPane.add(lblSubTotal);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setEditable(false);
		txtSubTotal.setColumns(10);
		txtSubTotal.setBounds(360, 584, 160, 20);
		contentPane.add(txtSubTotal);
		
		JLabel lblIGV = new JLabel("I.G.V. (18%):");
		lblIGV.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblIGV.setBounds(270, 610, 90, 20);
		contentPane.add(lblIGV);
		
		txtIGV = new JTextField();
		txtIGV.setEditable(false);
		txtIGV.setColumns(10);
		txtIGV.setBounds(360, 610, 160, 20);
		contentPane.add(txtIGV);
		
		JLabel lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTotal.setBounds(270, 636, 80, 20);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(360, 636, 160, 20);
		contentPane.add(txtTotal);
		
		// Botones
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setFont(new Font("Arial", Font.BOLD, 12));
		btnNuevo.setBounds(10, 584, 100, 30);
		btnNuevo.addActionListener(this);
		contentPane.add(btnNuevo);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Arial", Font.BOLD, 12));
		btnSalir.setBounds(10, 625, 100, 30);
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);
		
		btnListarCompras = new JButton("Ver Compras");
		btnListarCompras.setFont(new Font("Arial", Font.BOLD, 12));
		btnListarCompras.setBounds(120, 584, 130, 30);
		btnListarCompras.addActionListener(this);
		contentPane.add(btnListarCompras);
		
		btnRealizarCompra = new JButton("Comprar");
		btnRealizarCompra.addActionListener(this);
		btnRealizarCompra.setFont(new Font("Arial", Font.BOLD, 14));
		btnRealizarCompra.setBounds(120, 625, 130, 30);
		contentPane.add(btnRealizarCompra);
		
		cargarProductos();
		inicializarTotales();
	}
	
	ArregloComida ac = ArregloComida.getInstancia();
	ArregloProveedor ap = ArregloProveedor.getInstancia();
	ArregloCompras acompras = ArregloCompras.getInstancia();
	
	private void aplicarFiltroRUC() {
		AbstractDocument doc = (AbstractDocument) txtRUC.getDocument();
		doc.setDocumentFilter(new DocumentFilter() {
			
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) 
					throws BadLocationException {
				if (string == null) return;
				
				if (string.matches("\\d+")) {
					if ((fb.getDocument().getLength() + string.length()) <= 11) {
						super.insertString(fb, offset, string, attr);
					}
				}
			}

			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) 
					throws BadLocationException {
				if (text == null) return;

				if (text.matches("\\d+")) {
					int newLength = fb.getDocument().getLength() - length + text.length();
					if (newLength <= 11) {
						super.replace(fb, offset, length, text, attrs);
					}
				}
			}
		});
	}

	private void controlarCamposSegunTipoDocumento() {
		int tipoSeleccionado = cboTipoDocumento.getSelectedIndex();
		
		switch (tipoSeleccionado) {
			case 0: 
				txtRUC.setEnabled(true);
				txtRUC.setBackground(Color.WHITE);
				
				txtNombreProveedor.setEnabled(true);
				txtNombreProveedor.setBackground(Color.WHITE);
				
				txtRUC.requestFocus();
				System.out.println("✓ Factura: RUC y Nombre habilitados");
				break;
				
			case 1: 
				txtRUC.setEnabled(false);
				txtRUC.setText("");
				txtRUC.setBackground(Color.LIGHT_GRAY);
				
				txtNombreProveedor.setEnabled(true);
				txtNombreProveedor.setBackground(Color.WHITE);
				txtNombreProveedor.requestFocus();
				break;
				
			case 2:
				txtRUC.setEnabled(false);
				txtRUC.setText("");
				txtRUC.setBackground(Color.LIGHT_GRAY);
				
				txtNombreProveedor.setEnabled(false);
				txtNombreProveedor.setText("");
				txtNombreProveedor.setBackground(Color.LIGHT_GRAY);
				
				break;
				
			default:
				break;
		}
	}
	private void cargarProductos() {
		cboProductos.addItem(new Comida(0, "-- Seleccionar Producto --", 0, 0));
		for (int i = 0; i < ac.Tamaño(); i++) {
			cboProductos.addItem(ac.obtenerPorIndice(i));
		}
	}
	
	private void autocompletarProveedor() {
		String ruc = txtRUC.getText().trim();
		if (!ruc.isEmpty()) {
			Proveedor prov = ap.BuscarPorRuc(ruc);
			if (prov != null) {
				txtNombreProveedor.setText(prov.getNombre());
			}
		}
	}
	
	private void limpiarCamposProducto() {
		txtCodigo.setText("");
		txtCostoUnitario.setText("");
		txtCantidad.setText("");
		txtStock.setText("");
		cboProductos.setSelectedIndex(0);
	}
	
	private void limpiarCompra() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		subTotalGlobal = 0;
		inicializarTotales();
		txtRUC.setText("");
		txtNombreProveedor.setText("");
		cboTipoDocumento.setSelectedIndex(0);
		limpiarCamposProducto();
		controlarCamposSegunTipoDocumento(); // ← Resetear controles
	}
	
	private void inicializarTotales() {
		txtSubTotal.setText("S/. 0.00");
		txtIGV.setText("S/. 0.00");
		txtTotal.setText("S/. 0.00");
	}
	
	private void actualizarTotales() {
		double igv = subTotalGlobal * 0.18;
		double total = subTotalGlobal + igv;
		
		txtSubTotal.setText(String.format("S/. %.2f", subTotalGlobal));
		txtIGV.setText(String.format("S/. %.2f", igv));
		txtTotal.setText(String.format("S/. %.2f", total));
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// Control de tipo de documento
		if (e.getSource() == cboTipoDocumento && e.getStateChange() == ItemEvent.SELECTED) {
			controlarCamposSegunTipoDocumento();
		}
		
		// Control de productos
		if (e.getSource() == cboProductos && e.getStateChange() == ItemEvent.SELECTED) {
			Comida productoSeleccionado = (Comida) cboProductos.getSelectedItem();
			
			if (productoSeleccionado == null || productoSeleccionado.getCodigo() == 0) {
				limpiarCamposProducto();
			} else {
				txtCodigo.setText(String.valueOf(productoSeleccionado.getCodigo()));
				txtCostoUnitario.setText(String.format("S/. %.2f", productoSeleccionado.getCostoUnitario()));
				txtStock.setText(String.valueOf(productoSeleccionado.getStock()));
				txtCantidad.setText("");
				txtCantidad.requestFocus();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregarProducto) {
			do_btnAgregarProducto_actionPerformed(e);
		}
		if (e.getSource() == btnNuevo) {
			do_btnNuevo_actionPerformed(e);
		}
		if (e.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(e);
		}
		if (e.getSource() == btnRealizarCompra) {
			do_btnRealizarCompra_actionPerformed(e);
		}
		if (e.getSource() == btnListarCompras) {
			do_btnListarCompras_actionPerformed(e);
		}
	}
	
	protected void do_btnAgregarProducto_actionPerformed(ActionEvent e) {
		try {
			Comida productoSeleccionado = (Comida) cboProductos.getSelectedItem();
			
			if (productoSeleccionado == null || productoSeleccionado.getCodigo() == 0) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar un producto", 
					"Producto no seleccionado", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String cantidadStr = txtCantidad.getText().trim();
			if (cantidadStr.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad", 
					"Cantidad vacía", JOptionPane.WARNING_MESSAGE);
				txtCantidad.requestFocus();
				return;
			}
			
			int cantidad = 0;
			try {
				cantidad = Integer.parseInt(cantidadStr);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero", 
					"Formato inválido", JOptionPane.ERROR_MESSAGE);
				txtCantidad.setText("");
				txtCantidad.requestFocus();
				return;
			}
			
			if (cantidad <= 0) {
				JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0", 
					"Cantidad inválida", JOptionPane.WARNING_MESSAGE);
				txtCantidad.setText("");
				txtCantidad.requestFocus();
				return;
			}
			
			double costoUnitario = productoSeleccionado.getCostoUnitario();
			double subtotalProducto = cantidad * costoUnitario;
			
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			modelo.addRow(new Object[]{
				productoSeleccionado.getCodigo(),
				productoSeleccionado.getDescripcion(),
				cantidad,
				String.format("S/. %.2f", costoUnitario),
				String.format("S/. %.2f", subtotalProducto)
			});
			
			subTotalGlobal += subtotalProducto;
			actualizarTotales();
			
			limpiarCamposProducto();
			
			JOptionPane.showMessageDialog(this, "Producto agregado al detalle de compra", 
				"Éxito", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), 
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void do_btnNuevo_actionPerformed(ActionEvent e) {
		limpiarCompra();
		JOptionPane.showMessageDialog(this, "Nueva compra iniciada", 
			"Nueva Compra", JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	protected void do_btnListarCompras_actionPerformed(ActionEvent e) {
		VentanaListarCompras ventana = new VentanaListarCompras();
		ventana.setVisible(true);
	}
	
	protected void do_btnRealizarCompra_actionPerformed(ActionEvent e) {
		try {
			if (table.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "Debe agregar al menos un producto", 
					"Compra vacía", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String tipoDocumento = (String) cboTipoDocumento.getSelectedItem();
			String ruc = txtRUC.getText().trim();
			String nombre = txtNombreProveedor.getText().trim();
			
			// Validación según tipo de documento
			if (tipoDocumento.equals("Factura")) {
				// FACTURA: RUC obligatorio
				if (ruc.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Debe ingresar el RUC del proveedor", 
						"RUC requerido", JOptionPane.WARNING_MESSAGE);
					txtRUC.requestFocus();
					return;
				}
				
				if (ruc.length() != 11) {
					JOptionPane.showMessageDialog(this, 
						"RUC inválido. Debe contener exactamente 11 dígitos.\n" +
						"RUC ingresado: " + ruc + " (" + ruc.length() + " dígitos)", 
						"RUC Inválido", JOptionPane.ERROR_MESSAGE);
					txtRUC.requestFocus();
					return;
				}
				
				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del proveedor", 
						"Nombre requerido", JOptionPane.WARNING_MESSAGE);
					txtNombreProveedor.requestFocus();
					return;
				}
				
			} else if (tipoDocumento.equals("Boleta")) {
				// BOLETA: Solo nombre obligatorio
				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del proveedor", 
						"Nombre requerido", JOptionPane.WARNING_MESSAGE);
					txtNombreProveedor.requestFocus();
					return;
				}
				ruc = "999999"; // RUC genérico para boleta
				
			} else if (tipoDocumento.equals("Nota de Compra")) {
				// NOTA DE COMPRA: Datos genéricos
				ruc = "999999";
				nombre = "COMPRA INTERNA";
			}
			
			// Guardar o actualizar proveedor (solo si tiene RUC real)
			if (!ruc.equals("999999")) {
				Proveedor prov = ap.BuscarPorRuc(ruc);
				if (prov == null) {
					prov = new Proveedor(ruc, nombre);
					ap.Adicionar(prov);
				}
			}
			
			// Crear compra
			int numeroCompra = acompras.obtenerSiguienteNumero();
			Compra compra = new Compra(numeroCompra, tipoDocumento, ruc, nombre);
			
			// Agregar detalles y actualizar stock
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			for (int i = 0; i < modelo.getRowCount(); i++) {
				int codigoProducto = Integer.parseInt(modelo.getValueAt(i, 0).toString());
				String descripcion = modelo.getValueAt(i, 1).toString();
				int cantidad = Integer.parseInt(modelo.getValueAt(i, 2).toString());
				String costoStr = modelo.getValueAt(i, 3).toString().replace("S/. ", "");
				double costoUnitario = Double.parseDouble(costoStr);
				
				DetalleCompra detalle = new DetalleCompra(codigoProducto, descripcion, cantidad, costoUnitario);
				compra.agregarDetalle(detalle);
				
				// Actualizar stock
				Comida producto = ac.Buscar(codigoProducto);
				if (producto != null) {
					int nuevoStock = producto.getStock() + cantidad;
					producto.setStock(nuevoStock);
					ac.actualizarStock(producto.getCodigo(), nuevoStock);
				}
			}
			
			acompras.Adicionar(compra);
			
			JOptionPane.showMessageDialog(this, 
				"¡Compra realizada exitosamente!\n\n" +
				"Tipo: " + tipoDocumento + "\n" +
				"Número: " + String.format("%06d", numeroCompra) + "\n" +
				"Proveedor: " + nombre + "\n" +
				"Total: S/. " + String.format("%.2f", compra.getTotal()), 
				"Compra Registrada", JOptionPane.INFORMATION_MESSAGE);
			
			limpiarCompra();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), 
				"Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}
}