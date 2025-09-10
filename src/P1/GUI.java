package P1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
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
import java.util.List;

public class GUI extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	TitledBorder bordeArticulo = new TitledBorder("Artículo");
	private JTextField txtCodigo;
	private JTextField txtStock;
	private JTextField txtPUnitario;
	private JTextField txtCantidad;
	private JTextField txtRUC;
	private JTextField txtRazonSocial;
	private JTextField txtDomicilio;
	private JTextField txtSubTotal;
	private JTextField txtIGV;
	private JTextField txtTotal;
	private JButton btnAgregar;
	private JComboBox<Producto> cboProductos;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 466, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tienda");
		lblNewLabel.setFont(new Font("MS PGothic", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 0, 143, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblnombreDelRestaurante = new JLabel("(Nombre del restaurante)");
		lblnombreDelRestaurante.setFont(new Font("MS PGothic", Font.PLAIN, 24));
		lblnombreDelRestaurante.setBounds(10, 37, 247, 39);
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
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false); // Solo lectura
		txtCodigo.setBounds(140, 22, 147, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		// ComboBox de productos
		cboProductos = new JComboBox<>();
		cboProductos.addItemListener(this);
		cboProductos.setBounds(140, 50, 147, 20);
		panel.add(cboProductos);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblStock.setBounds(308, 11, 102, 39);
		panel.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setEditable(false); // Solo lectura
		txtStock.setColumns(10);
		txtStock.setBounds(306, 49, 92, 20);
		panel.add(txtStock);
		
		txtPUnitario = new JTextField();
		txtPUnitario.setEditable(false); // Solo lectura
		txtPUnitario.setColumns(10);
		txtPUnitario.setBounds(140, 79, 147, 20);
		panel.add(txtPUnitario);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(140, 110, 147, 20);
		panel.add(txtCantidad);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAgregar.setBackground(SystemColor.activeCaption);
		btnAgregar.setBounds(308, 99, 102, 22);
		panel.add(btnAgregar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
		panel_1.setBounds(10, 272, 420, 155);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, -21, 424, 169);
		panel_1.add(scrollPane);
		scrollPane.setEnabled(false);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 10));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cantidad", "Descripci\u00F3n", "P. Unitario", "P. Total"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(52);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(59);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(52);
		scrollPane.setViewportView(table);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNuevo.setBackground(SystemColor.activeCaption);
		btnNuevo.setBounds(10, 428, 89, 23);
		contentPane.add(btnNuevo);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSalir.setBackground(SystemColor.activeCaption);
		btnSalir.setBounds(109, 428, 89, 23);
		contentPane.add(btnSalir);
		
		JLabel lblRucDni = new JLabel("R.U.C ");
		lblRucDni.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblRucDni.setBounds(10, 448, 54, 39);
		contentPane.add(lblRucDni);
		
		txtRUC = new JTextField();
		txtRUC.setColumns(10);
		txtRUC.setBounds(10, 477, 147, 20);
		contentPane.add(txtRUC);
		
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
		
		JButton btnVENTA = new JButton("Realizar Venta");
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
	}
	
	// Método para cargar productos en el ComboBox
	private void cargarProductos() {
		cboProductos.removeAllItems();
		List<Producto> productos = ConexionBD.obtenerProductos();
		for (var p : productos) {
			cboProductos.addItem(p);
		}
	}
	
	// Listener para el ComboBox (autocompletado)
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboProductos && e.getStateChange() == ItemEvent.SELECTED) {
			Producto productoSeleccionado = (Producto) cboProductos.getSelectedItem();
			if (productoSeleccionado != null) {
				txtCodigo.setText(productoSeleccionado.getCodigo());
				txtPUnitario.setText(String.valueOf(productoSeleccionado.getpUnitario()));
				txtStock.setText(String.valueOf(productoSeleccionado.getStock()));
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			do_btnAgregar_actionPerformed(e);
		}
	}
	
	protected void do_btnAgregar_actionPerformed(ActionEvent e) {
		// Tu código actual del botón agregar
		String desc = cboProductos.getSelectedItem().toString();
		double PUnit = Double.parseDouble(txtPUnitario.getText());
		int cantidad = Integer.parseInt(txtCantidad.getText());
		int stock = Integer.parseInt(txtStock.getText());
		
		Producto p = new Producto(cantidad, PUnit);
		double total = p.Total(cantidad, PUnit);
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.addRow(new Object[] {cantidad, desc, PUnit, total});
        // Limpiar campos
        txtCantidad.setText("");
	}
}