package interfacesGraficas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import clases.ArregloComida;
import clases.ArregloVentas;
import clases.Comida;
import clases.DetalleVenta;
import clases.GestorBoletas;
import clases.ManejadorContador;
import clases.Venta;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class VentanaVentas extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	TitledBorder bordeArticulo = new TitledBorder("Artículo");
	private JTextField txtRUC;
	private JTextField txtRazonSocial;
	private JTextField txtDomicilio;
	private JTextField txtSubTotal;
	private JTextField txtIGV;
	private JTextField txtTotal;
	private JButton btnAgregar;
	private JComboBox<Comida> cboProductos;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtCodigo;
	private JTextField txtPUnitario;
	private JTextField txtCantidad;
	private JTextField txtStock;
	private JButton btnNuevo;
	private JButton btnSalir;
	private JButton btnVENTA;
	
	// Variables para controlar totales
	private double subTotalGlobal = 0;
	private int numeroProductosAgregados = 0;

	public VentanaVentas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 466, 625);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 232, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tienda");
		lblNewLabel.setFont(new Font("MS PGothic", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 0, 143, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblnombreDelRestaurante = new JLabel("Polleria Excelencia");
		lblnombreDelRestaurante.setFont(new Font("MS PGothic", Font.PLAIN, 27));
		lblnombreDelRestaurante.setBounds(10, 27, 247, 39);
		contentPane.add(lblnombreDelRestaurante);
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		panel1.setBounds(281, 11, 149, 39);
		contentPane.add(panel1);
		
		JLabel lblNro = new JLabel("Nro: 000001");
		lblNro.setFont(new Font("MS PGothic", Font.PLAIN, 24));
		panel1.add(lblNro);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		panel.setBounds(10, 73, 420, 164);
		contentPane.add(panel);
		panel.setBorder(bordeArticulo);
		panel.setLayout(null);
		
		JLabel lblCdigo = new JLabel("Código:");
		lblCdigo.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCdigo.setBounds(10, 11, 102, 39);
		panel.add(lblCdigo);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblProducto.setBounds(10, 38, 102, 39);
		panel.add(lblProducto);
		
		JLabel lblPUnitario = new JLabel("P. Unitario:");
		lblPUnitario.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPUnitario.setBounds(10, 68, 102, 39);
		panel.add(lblPUnitario);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCantidad.setBounds(10, 99, 102, 39);
		panel.add(lblCantidad);
		
		// ComboBox de productos
		cboProductos = new JComboBox<>();
		cboProductos.addItemListener(this);
		cboProductos.setBounds(140, 50, 147, 20);
		panel.add(cboProductos);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblStock.setBounds(308, 11, 102, 39);
		panel.add(lblStock);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAgregar.setBackground(SystemColor.activeCaption);
		btnAgregar.setBounds(308, 99, 92, 39);
		panel.add(btnAgregar);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(140, 22, 147, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtPUnitario = new JTextField();
		txtPUnitario.setEditable(false);
		txtPUnitario.setColumns(10);
		txtPUnitario.setBounds(140, 79, 147, 20);
		panel.add(txtPUnitario);
		
		txtCantidad = new JTextField();
		txtCantidad.setEditable(true);
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(140, 110, 147, 20);
		panel.add(txtCantidad);
		
		txtStock = new JTextField();
		txtStock.setEditable(false);
		txtStock.setBounds(308, 49, 92, 20);
		panel.add(txtStock);
		txtStock.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
		panel_1.setBounds(10, 272, 420, 155);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, -21, 424, 169);
		panel_1.add(scrollPane);
		scrollPane.setEnabled(false);
		
		// Modelo de tabla
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Cantidad");
		modelo.addColumn("Descripción");
		modelo.addColumn("P. Unitario");  
		modelo.addColumn("P. Total");
		
		table = new JTable();
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNuevo.setBackground(SystemColor.activeCaption);
		btnNuevo.setBounds(10, 428, 89, 23);
		btnNuevo.addActionListener(this);
		contentPane.add(btnNuevo);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSalir.setBackground(SystemColor.activeCaption);
		btnSalir.setBounds(109, 428, 89, 23);
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);
		
		JLabel lblRucDni = new JLabel("R.U.C ");
		lblRucDni.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblRucDni.setBounds(10, 448, 54, 39);
		contentPane.add(lblRucDni);
		
		txtRUC = new JTextField();
		txtRUC.setColumns(10);
		txtRUC.setBounds(10, 477, 147, 20);
		contentPane.add(txtRUC);
		
		// ========== VALIDACIÓN EN TIEMPO REAL ==========
	    txtRUC.addKeyListener(new java.awt.event.KeyAdapter() {
	        @Override
	        public void keyTyped(java.awt.event.KeyEvent evt) {
	            validarRUCEnTiempoReal(evt);
	        }
	    });
	    
	    // También añadir validación visual con DocumentListener
	    txtRUC.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
	        @Override
	        public void insertUpdate(javax.swing.event.DocumentEvent e) {
	            validarLongitudRUC();
	        }
	        
	        @Override
	        public void removeUpdate(javax.swing.event.DocumentEvent e) {
	            validarLongitudRUC();
	        }
	        
	        @Override
	        public void changedUpdate(javax.swing.event.DocumentEvent e) {
	            validarLongitudRUC();
	        }
	    });
		
		JLabel lblRaznSocial = new JLabel("Razón Social");
		lblRaznSocial.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblRaznSocial.setBounds(10, 490, 101, 39);
		contentPane.add(lblRaznSocial);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setColumns(10);
		txtRazonSocial.setBounds(10, 518, 147, 20);
		contentPane.add(txtRazonSocial);
		
		JLabel lblRaznSocial_1 = new JLabel("Domicilio");
		lblRaznSocial_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblRaznSocial_1.setBounds(10, 530, 101, 39);
		contentPane.add(lblRaznSocial_1);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setColumns(10);
		txtDomicilio.setBounds(10, 560, 147, 20);
		contentPane.add(txtDomicilio);
		
		JLabel lblCantidad_1_2 = new JLabel("Sub Total.");
		lblCantidad_1_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCantidad_1_2.setBounds(226, 422, 102, 39);
		contentPane.add(lblCantidad_1_2);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setEditable(false);
		txtSubTotal.setColumns(10);
		txtSubTotal.setBounds(317, 431, 113, 20);
		contentPane.add(txtSubTotal);
		
		JLabel lblCantidad_1_2_1 = new JLabel("I.G.V.");
		lblCantidad_1_2_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCantidad_1_2_1.setBounds(226, 448, 102, 39);
		contentPane.add(lblCantidad_1_2_1);
		
		txtIGV = new JTextField();
		txtIGV.setEditable(false);
		txtIGV.setColumns(10);
		txtIGV.setBounds(317, 459, 113, 20);
		contentPane.add(txtIGV);
		
		JLabel lblCantidad_1_2_1_1 = new JLabel("Total.");
		lblCantidad_1_2_1_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCantidad_1_2_1_1.setBounds(226, 480, 102, 39);
		contentPane.add(lblCantidad_1_2_1_1);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(317, 490, 113, 20);
		contentPane.add(txtTotal);
		
		btnVENTA = new JButton("Realizar Venta");
		btnVENTA.addActionListener(this);
		btnVENTA.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnVENTA.setBackground(SystemColor.activeCaption);
		btnVENTA.setBounds(223, 530, 207, 23);
		contentPane.add(btnVENTA);
		
		JLabel lblDescrp_1 = new JLabel("Descripción");
		lblDescrp_1.setBounds(109, 234, 102, 39);
		contentPane.add(lblDescrp_1);
		lblDescrp_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		JLabel lblCantidad_1 = new JLabel("Cantidad");
		lblCantidad_1.setBounds(13, 235, 103, 37);
		contentPane.add(lblCantidad_1);
		lblCantidad_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		JLabel lblPTotal = new JLabel("P. Total");
		lblPTotal.setBounds(345, 234, 73, 39);
		contentPane.add(lblPTotal);
		lblPTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		JLabel lblPUnitario_1 = new JLabel("P. Unitario");
		lblPUnitario_1.setBounds(245, 234, 102, 39);
		contentPane.add(lblPUnitario_1);
		lblPUnitario_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		// Cargar productos al inicializar
		cargarProductos();
		
		// Inicializar contador de ventas
		ManejadorContador.inicializarContador();
	}
	
	ArregloComida ac = ArregloComida.getInstancia();
	
	/**
	 * Carga todos los productos de ArregloComida al ComboBox
	 */
	private void cargarProductos() {
		cboProductos.addItem(new Comida(0, "-- Seleccionar Producto --", 0, 0));
		
		// Agregar todos los productos del ArregloComida
		for (int i = 0; i < ac.Tamaño(); i++) {
			cboProductos.addItem(ac.obtenerPorIndice(i));
		}
	}
	
	/**
	 * Limpia los campos de entrada de producto
	 */
	private void limpiarCamposProducto() {
		txtCodigo.setText("");
		txtPUnitario.setText("");
		txtCantidad.setText("");
		txtStock.setText("");
		cboProductos.setSelectedIndex(0); // Vuelve a "-- Seleccionar Producto --"
	}
	
	/**
	 * Limpia la venta completa
	 */
	private void limpiarVenta() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		subTotalGlobal = 0;
		txtSubTotal.setText("S/. 0.00");
		txtIGV.setText("S/. 0.00");
		txtTotal.setText("S/. 0.00");
		txtRUC.setText("");
		txtRazonSocial.setText("");
		txtDomicilio.setText("");
		limpiarCamposProducto();
	}
	
	/**
	 * Actualiza los totales en la venta
	 */
	private void actualizarTotales() {
		double igv = subTotalGlobal * 0.18;
		double total = subTotalGlobal + igv;
		
		txtSubTotal.setText(String.format("S/. %.2f", subTotalGlobal));
		txtIGV.setText(String.format("S/. %.2f", igv));
		txtTotal.setText(String.format("S/. %.2f", total));
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboProductos && e.getStateChange() == ItemEvent.SELECTED) {
			Comida productoSeleccionado = (Comida) cboProductos.getSelectedItem();
			
			// Si selecciona la opción vacía o la opción inicial
			if (productoSeleccionado == null || productoSeleccionado.getCodigo() == 0) {
				limpiarCamposProducto();
			} else {
				// Llenar los campos con datos del producto seleccionado
				txtCodigo.setText(String.valueOf(productoSeleccionado.getCodigo()));
				txtPUnitario.setText(String.format("S/. %.2f", productoSeleccionado.getpUnitario()));
				txtStock.setText(String.valueOf(productoSeleccionado.getStock()));
				txtCantidad.setText(""); // Limpiar cantidad para que ingrese
				txtCantidad.requestFocus(); // Enfocar en cantidad
			}
		}
	}

	private void validarRUCEnTiempoReal(java.awt.event.KeyEvent evt) {
	    char caracter = evt.getKeyChar();
	    String textoActual = txtRUC.getText();
	    
	    // 1. Solo permitir números
	    if (!Character.isDigit(caracter) && caracter != java.awt.event.KeyEvent.VK_BACK_SPACE) {
	        evt.consume(); // Bloquear el carácter
	        
	        // Mostrar advertencia solo si intentó escribir letra
	        if (Character.isLetter(caracter)) {
	            javax.swing.JOptionPane.showMessageDialog(this, 
	                "⚠️ El RUC solo puede contener números", 
	                "Carácter inválido", 
	                javax.swing.JOptionPane.WARNING_MESSAGE);
	        }
	        return;
	    }
	    
	    // 2. Bloquear si ya tiene 11 dígitos (excepto backspace)
	    if (textoActual.length() >= 11 && caracter != java.awt.event.KeyEvent.VK_BACK_SPACE) {
	        evt.consume(); // Bloquear el carácter
	        
	        // Mostrar advertencia con sonido
	        java.awt.Toolkit.getDefaultToolkit().beep();
	        
	        javax.swing.JOptionPane.showMessageDialog(this, 
	            "⚠️ El RUC debe tener exactamente 11 dígitos.\n\n" +
	            "Ya has alcanzado el límite.", 
	            "Máximo 11 dígitos", 
	            javax.swing.JOptionPane.WARNING_MESSAGE);
	    }
	}

	/**
	 * Validación visual: cambia color según longitud
	 */
	private void validarLongitudRUC() {
	    String texto = txtRUC.getText().trim();
	    int longitud = texto.length();
	    
	    if (longitud == 0) {
	        // Campo vacío: color normal (blanco)
	        txtRUC.setBackground(java.awt.Color.WHITE);
	        
	    } else if (longitud < 11) {
	        // Menos de 11: amarillo (incompleto)
	        txtRUC.setBackground(new java.awt.Color(255, 255, 200)); // Amarillo claro
	        
	    } else if (longitud == 11) {
	        // Exactamente 11: verde (correcto)
	        txtRUC.setBackground(new java.awt.Color(200, 255, 200)); // Verde claro
	        
	    } else {
	        // Más de 11: rojo (error) - esto no debería pasar con keyTyped
	        txtRUC.setBackground(new java.awt.Color(255, 200, 200)); // Rojo claro
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			do_btnAgregar_actionPerformed(e);
		}
		if (e.getSource() == btnNuevo) {
			do_btnNuevo_actionPerformed(e);
		}
		if (e.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(e);
		}
		if (e.getSource() == btnVENTA) {
			do_btnVENTA_actionPerformed(e);
		}
	}
	
	protected void do_btnAgregar_actionPerformed(ActionEvent e) {
		try {
			Comida productoSeleccionado = (Comida) cboProductos.getSelectedItem();
			
			// Validar que seleccione un producto válido
			if (productoSeleccionado == null || productoSeleccionado.getCodigo() == 0) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar un producto", 
					"Producto no seleccionado", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// Validar que ingrese cantidad
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
			
			// Validar que cantidad sea positiva
			if (cantidad <= 0) {
				JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0", 
					"Cantidad inválida", JOptionPane.WARNING_MESSAGE);
				txtCantidad.setText("");
				txtCantidad.requestFocus();
				return;
			}
			
			// VALIDACIÓN PRINCIPAL: Verificar stock disponible
			if (cantidad > productoSeleccionado.getStock()) {
				JOptionPane.showMessageDialog(this, 
					"No hay suficiente stock disponible.\n" +
					"Stock disponible: " + productoSeleccionado.getStock() + "\n" +
					"Cantidad solicitada: " + cantidad, 
					"Falta de Stock", JOptionPane.ERROR_MESSAGE);
				txtCantidad.setText("");
				txtCantidad.requestFocus();
				return;
			}
			
			// Calcular total del producto
			double precioUnitario = productoSeleccionado.getpUnitario();
			double totalProducto = cantidad * precioUnitario;
			
			// Reducir stock del producto
			int nuevoStock = productoSeleccionado.getStock() - cantidad;
			productoSeleccionado.setStock(nuevoStock);
			ac.actualizarStock(productoSeleccionado.getCodigo(), nuevoStock);
			
			// Agregar fila a la tabla
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			modelo.addRow(new Object[]{
				cantidad,
				productoSeleccionado.getDescripcion(),
				String.format("S/. %.2f", precioUnitario),
				String.format("S/. %.2f", totalProducto)
			});
			
			// Actualizar subtotal global
			subTotalGlobal += totalProducto;
			actualizarTotales();
			
			// Limpiar campos para siguiente producto
			limpiarCamposProducto();
			
			JOptionPane.showMessageDialog(this, "Producto agregado a la venta", 
				"Éxito", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), 
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void do_btnNuevo_actionPerformed(ActionEvent e) {
		limpiarVenta();
		JOptionPane.showMessageDialog(this, "Venta nueva iniciada", "Nueva Venta", JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	protected void do_btnVENTA_actionPerformed(ActionEvent e) {
		try {
			// Validar que haya productos en la venta
			if (table.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "Debe agregar al menos un producto", 
					"Venta vacía", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// Leer datos del cliente
			String ruc = txtRUC.getText().trim();
			String razonSocial = txtRazonSocial.getText().trim();
			String domicilio = txtDomicilio.getText().trim();
			
			// Validar Razón Social (obligatoria en ambos casos)
			if (razonSocial.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Debe ingresar la Razón Social del cliente", 
					"Datos incompletos", JOptionPane.WARNING_MESSAGE);
				txtRazonSocial.requestFocus();
				return;
			}
			
			// Obtener totales de los campos
			String subTotalStr = txtSubTotal.getText().replace("S/. ", "");
			String igvStr = txtIGV.getText().replace("S/. ", "");
			String totalStr = txtTotal.getText().replace("S/. ", "");
			
			double subTotal = Double.parseDouble(subTotalStr);
			double igv = Double.parseDouble(igvStr);
			double total = Double.parseDouble(totalStr);
			
			// DETERMINAR SI ES BOLETA O FACTURA
			boolean esFactura = false;
			
			if (ruc.isEmpty()) {
				esFactura = false;
			} else {
				if (ruc.length() != 11 || !ruc.matches("\\d+")) {
					JOptionPane.showMessageDialog(this, 
						"RUC inválido. Debe contener exactamente 11 dígitos numéricos.\n" +
						"RUC ingresado: " + ruc + " (" + ruc.length() + " caracteres)", 
						"RUC Inválido", JOptionPane.ERROR_MESSAGE);
					txtRUC.setText("");
					txtRUC.requestFocus();
					return;
				}
				if (domicilio.isEmpty()) {
					JOptionPane.showMessageDialog(this, 
						"Para generar una FACTURA debe ingresar el domicilio del cliente", 
						"Domicilio requerido", JOptionPane.WARNING_MESSAGE);
					txtDomicilio.requestFocus();
					return;
				}
				
				esFactura = true;
			}
			
			// Preparar detalles de productos para GestorBoletas
			ArrayList<GestorBoletas.DetalleVenta> productos = new ArrayList<>();
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			
			for (int i = 0; i < modelo.getRowCount(); i++) {
				int cantidad = Integer.parseInt(modelo.getValueAt(i, 0).toString());
				String descripcion = modelo.getValueAt(i, 1).toString();
				String precioStr = modelo.getValueAt(i, 2).toString().replace("S/. ", "");
				double precioUnitario = Double.parseDouble(precioStr);
				
				productos.add(new GestorBoletas.DetalleVenta(cantidad, descripcion, precioUnitario));
			}
			
			// Obtener el número secuencial
			int numero = ManejadorContador.obtenerSiguienteNumero();
			
			String rutaArchivo = null;
			String tipoComprobante = "";
			
			if (esFactura) {
				// GENERAR FACTURA
				rutaArchivo = GestorBoletas.GenerarFactura(numero, ruc, razonSocial, domicilio, 
					productos, subTotal, igv, total);
				tipoComprobante = "Factura";
			} else {
				// GENERAR BOLETA
				rutaArchivo = GestorBoletas.GenerarBoleta(numero, razonSocial, 
					productos, subTotal, igv, total);
				tipoComprobante = "Boleta";
			}
			
			if (rutaArchivo == null) {
				JOptionPane.showMessageDialog(this, "Error al generar la " + tipoComprobante, 
					"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// ============ GUARDAR VENTA EN LA BASE DE DATOS ============
			
			// Crear objeto Venta para guardar en BD
			Venta nuevaVenta = new Venta(numero, tipoComprobante, 
                    esFactura ? ruc : null, 
                    razonSocial, 
                    esFactura ? domicilio : null, 
                    rutaArchivo);
			
			// Agregar detalles a la venta
			for (int i = 0; i < modelo.getRowCount(); i++) {
			    int cantidad = Integer.parseInt(modelo.getValueAt(i, 0).toString());
			    String descripcion = modelo.getValueAt(i, 1).toString();
			    String precioStr = modelo.getValueAt(i, 2).toString().replace("S/. ", "");
			    double precioUnitario = Double.parseDouble(precioStr);
			    
			    // Buscar código del producto por descripción
			    Comida producto = ac.BuscarPorDescripcion(descripcion);
			    if (producto != null) {
			        // ⚠️ Constructor actualizado: sin descripción
			        DetalleVenta detalle = new DetalleVenta(producto.getCodigo(), 
			                                                cantidad, 
			                                                precioUnitario);
			        nuevaVenta.agregarDetalle(detalle);
			    }
			}
			
			// Guardar venta en la BD
			ArregloVentas av = ArregloVentas.getInstancia();
			av.Adicionar(nuevaVenta);
			
			// ============================================================
			
			JOptionPane.showMessageDialog(this, 
				    tipoComprobante + " Nº " + String.format("%06d", numero) + " generada exitosamente!\n\n" +
				    "El archivo se ha guardado en:\n" + 
				    GestorBoletas.obtenerRutaDocumentos() + "\n\n" +
				    "La venta ha sido registrada en la base de datos.", 
				    "¡Venta Realizada!", JOptionPane.INFORMATION_MESSAGE);

				GestorBoletas.abrirArchivo(rutaArchivo);
				limpiarVenta();
			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Error al procesar los totales: " + ex.getMessage(), 
				"Error de formato", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), 
				"Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}
}